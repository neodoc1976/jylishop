package org.george.jylishop.controllers;

import org.george.jylishop.dao.*;
import org.george.jylishop.domain.*;
import org.george.jylishop.services.PictureService;
import org.george.jylishop.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * Created by Yulya on 20.05.2016.
 */
@Controller
public class ProductController {
    @Autowired
    PictureService pictureService;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    ManufacturerDao manufacturerDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BasketDao basketDao;
    @Autowired
    CommentDao commentDao;

    @RequestMapping({"/", "/total"})
    public ModelAndView totalList(@RequestParam(required = false) String sort) {
        ModelAndView total = new ModelAndView("total");
        total.addObject("catalogue", productDao.getCatalogue());

        if (SecurityUtils.getCurrentUsername() != null) {
            List<Product> purchases = basketDao.getUserBasket(SecurityUtils.getCurrentUsername()).getPurchases();
            PurchaseTransaction transaction = new PurchaseTransaction();
            Map<Product, Integer> map = transaction.getGroupedProduct(purchases);
            Set<Product> keySet = map.keySet();
            List<Product> list = new ArrayList<>(keySet);
            total.addObject("list", list);
            total.addObject("basket", map);
        }

        if (sort != null && sort.equals("priceasc")) {

            List<Product> sorted = productDao.getCatalogueOrderByPriceAsc();
            total.addObject("catalogue", sorted);
        }

        if (sort != null && sort.equals("pricedesc")) {

            List<Product> sorted = productDao.getCatalogueOrderByPriceDesc();
            total.addObject("catalogue", sorted);
        }
        if (sort != null && sort.equals("title")) {
            List<Product> sorted = productDao.getCatalogueOrderByTitleByAlphabet();
            total.addObject("catalogue", sorted);
        }
        if (sort != null && sort.equals("title_reverse")) {
            List<Product> sorted = productDao.getCatalogueOrderByTitleReverse();
            total.addObject("catalogue", sorted);
        }
        if (sort != null && sort.equals("by_name")) {
            List<Product> sorted = productDao.getCatalogueOrderByManufacturerByAlphabet();
            total.addObject("catalogue", sorted);
        }
        if (sort != null && sort.equals("reverse_by_name")) {
            List<Product> sorted = productDao.getCatalogueOrderByManufacturerReverse();
            total.addObject("catalogue", sorted);
        }
        String username = SecurityUtils.getCurrentUsername();
      if (username!=null){
          List<UserRole> userRoles = userDao.getUserRolesAsList(username);
          total.addObject("userRoles",userRoles);
     }
        total.addObject("user_name", username);

        return total;
    }

    @RequestMapping({"/products/{id}"})
    public ModelAndView getProduct(@PathVariable int id) {
        Product selectedProduct = productDao.getProductById(id);

        List<Comment> comments = commentDao.getCommentByProduct(selectedProduct);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("gel-product");
            view.addObject("opalescenseInfo", selectedProduct);
            view.addObject("comments", comments);
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("hemo-product");
            view.addObject("hemoInfo", selectedProduct);
            view.addObject("comments", comments);
            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", " SORRY,PRODUCT IS NOT FOUND ");
        return view;
    }

    @RequestMapping(value = "/product/{id}/comment", method = RequestMethod.POST)
    public ModelAndView addComment(@PathVariable int id,
                                   @RequestParam String message/*,
                                   @RequestParam String userName */
    ) {
        if (message.isEmpty()){

            return new ModelAndView ("redirect:/products/{id}");
        }
        Comment comment = new Comment();
        Product selectedProduct = productDao.getProductById(id);
        comment.setProduct(selectedProduct);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy ");
        Date date = new Date();
        comment.setDate(dateFormat.format(date));
        comment.setMessage(message);
        if(SecurityUtils.getCurrentUsername()!=null){
        comment.setUserName(SecurityUtils.getCurrentUsername());
        }else {
            comment.setUserName("Anonymous user");
        }
        commentDao.addComment(comment);
        return new ModelAndView("redirect:/products/{id}");
    }

    @RequestMapping({"/manufacturer/{id}"})
    public ModelAndView variesManufacturer(@PathVariable int id) {
        Manufacturer manufacturer = manufacturerDao.getManufacturerById(id);
        ModelAndView view = new ModelAndView("man_description");
        view.addObject("manufacturer", manufacturer);
        view.addObject("count", productDao.getProductsCountForManufacturer(id));
        return view;
    }

    @RequestMapping({"/manufacturer/{id}/its_products"})
    public ModelAndView productsOfManufacturer(@PathVariable int id) {
        List<Product> list = productDao.getProductListByManufacturer(id);
        ModelAndView view = new ModelAndView("total");
        view.addObject("catalogue", list);
        return view;

    }

    @RequestMapping({"/products/only_gels"})
    public ModelAndView onlyGels() {
        List<OpalescenseGel> list = productDao.getOnlyGels();
        ModelAndView view = new ModelAndView("total");
        view.addObject("catalogue", list);
        return view;
    }

    @RequestMapping({"/products/only_hemos"})
    public ModelAndView onlyHemos() {
        List<Hemostatic> list = productDao.getOnlyHemos();
        ModelAndView view = new ModelAndView("total");
        view.addObject("catalogue", list);
        return view;

    }


}