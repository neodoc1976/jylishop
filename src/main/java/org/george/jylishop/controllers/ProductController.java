package org.george.jylishop.controllers;

import org.george.jylishop.dao.ManufacturerDao;
import org.george.jylishop.dao.ProductDao;
import org.george.jylishop.dao.UserDao;
import org.george.jylishop.domain.Hemostatic;
import org.george.jylishop.domain.Manufacturer;
import org.george.jylishop.domain.OpalescenseGel;
import org.george.jylishop.domain.Product;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


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

    @RequestMapping({"/", "/total"})
    public ModelAndView totalList(@RequestParam(required = false) String sort) {
        ModelAndView total = new ModelAndView("total");
        total.addObject("catalogue", productDao.getCatalogue());

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
            String username = SecurityUtils.getCurrentUsername() ;
            total.addObject("user_name", username);


        return total;
    }

    @RequestMapping({"/products/{id}"})
    public ModelAndView getProduct(@PathVariable int id) {
        Product selectedProduct = productDao.getProductById(id);

        if (selectedProduct instanceof OpalescenseGel) {
            ModelAndView view = new ModelAndView("gel-product");
            view.addObject("opalescenseInfo", selectedProduct);
            return view;
        }

        if (selectedProduct instanceof Hemostatic) {
            ModelAndView view = new ModelAndView("hemo-product");
            view.addObject("hemoInfo", selectedProduct);
            return view;
        }

        ModelAndView view = new ModelAndView("error");
        view.addObject("message", " SORRY,PRODUCT IS NOT FOUND ");
        return view;
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