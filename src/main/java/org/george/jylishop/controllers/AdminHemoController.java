package org.george.jylishop.controllers;


        import org.george.jylishop.db.DataBase;
        import org.george.jylishop.domain.Hemostatic;
        import org.george.jylishop.domain.OpalescenseGel;
        import org.george.jylishop.domain.Product;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Yulya on 21.05.2016.
 */
@Controller
public class AdminHemoController {
    @Autowired
    DataBase base;

    @RequestMapping(value = "/admin/hemos/add", method = RequestMethod.GET)
    public ModelAndView getForm() {
        ModelAndView view = new ModelAndView("admin-add-hemo");// Stop here
        return view;
     }

    @RequestMapping(value = "/admin/hemos", method = RequestMethod.POST)//Stop here
    public ModelAndView postForm(@RequestParam String title,
                                 @RequestParam Double volume,
                                 @RequestParam Double price,
                                 @RequestParam String substance,
                                 @RequestParam String description,
                                 @RequestParam Integer id) {
        ModelAndView post = new ModelAndView("admin-total");//Stop here
        Hemostatic newcomer = new Hemostatic();
        post.addObject("catalogue", base.getCatalogue());
        newcomer.setTitle(title);
        newcomer.setDescription(description);
        newcomer.setVolume(volume);
        newcomer.setHemostaticSubstance(substance);
        newcomer.setPrice(price);
        newcomer.setId(id);
        List<Product> list = base.getCatalogue();
        list.add(newcomer);
        return post;

    }

//    @RequestMapping(value = "/admin/hemos/{id}/delete", method = RequestMethod.GET)//Stop here
//    public ModelAndView deleteForm(@PathVariable int id) {
//        ModelAndView delete = new ModelAndView("admin-hemos-list");
//        List<Hemostatic> list = base.getHemolist();
//        list.remove(id);
//        delete.addObject("hemolist", base.getHemolist());//Stop here
//        return delete;
//    }
//
//    @RequestMapping({"/admin/hemo-list"})
//    public ModelAndView productList() {
//        ModelAndView list = new ModelAndView("admin-hemos-list");
//        list.addObject("hemolist", base.getHemolist());
//        return list;
//    }
//
//    @RequestMapping(value = "/admin/hemos/{id}/update", method = RequestMethod.GET)//Stop here
//    public ModelAndView getFilledForm(@PathVariable int id) {
//        ModelAndView update = new ModelAndView("admin-update-hemo");//Stop here
//        ArrayList<Hemostatic> recall = base.getHemolist();
//        update.addObject("recall", recall.get(id));
//        return update;
//
//
//    }
//
//    @RequestMapping(value = "/admin/hemos/{id}/update", method = RequestMethod.POST)
//    public ModelAndView editForm(@PathVariable int id,
//                                 @RequestParam String title,
//                                 @RequestParam Double volume,
//                                 @RequestParam Double price,
//                                 @RequestParam String hemostaticSubstance,
//                                 @RequestParam String description) {
//        ModelAndView post = new ModelAndView("admin-hemos-list");
//        ArrayList<Hemostatic> hemolist = base.getHemolist();
//        Hemostatic updated = hemolist.get(id);
//        updated.setTitle(title);
//        updated.setDescription(description);
//        updated.setVolume(volume);
//        updated.setHemostaticSubstance(hemostaticSubstance);
//        updated.setPrice(price);
//        post.addObject("hemolist", base.getHemolist());
//        return post;
//
//
//    }
//

}



