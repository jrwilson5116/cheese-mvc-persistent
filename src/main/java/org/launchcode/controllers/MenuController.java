package org.launchcode.controllers;

import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import static java.awt.SystemColor.menu;


@Controller
@RequestMapping(value = "menu")
public class MenuController {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private CheeseDao cheeseDao;


    @RequestMapping(value="")
    public String index(Model model){

        model.addAttribute("menus",menuDao.findAll());
        model.addAttribute("title","menus");

        return "menu/index";
    }

    @RequestMapping(value="add",method = RequestMethod.GET)
    public String add(Model model){

        model.addAttribute("title","add menus");
        model.addAttribute( new Menu());

        return "menu/add";
    }

    @RequestMapping(value="add",method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("title","Add Menu");
            return "menu/add";
        }
        menuDao.save(menu);
        return "redirect:view/"+menu.getId();
    }

    @RequestMapping(value="view/{menuId}",method=RequestMethod.GET)
    public String viewMenu(Model model, @PathVariable int menuId){

        model.addAttribute("menu",menuDao.findOne(menuId));
        return "menu/view";
    }

    @RequestMapping(value="add-item/{menuId}",method = RequestMethod.GET)
    public String addItem(Model model,@PathVariable int menuId){

        Menu menu = menuDao.findOne(menuId);
        AddMenuItemForm daForm = new AddMenuItemForm(menu,cheeseDao.findAll());

        model.addAttribute("form",daForm);
        model.addAttribute("title","Add item to menu"+menu.getName());

        return "menu/add-item";
    }

    @RequestMapping(value="add-item",method = RequestMethod.POST)
    public String addItem(Model model,AddMenuItemForm addMform,Errors errors){

        if (errors.hasErrors()){
            return "menu/add-item";
        }

        Menu menu = menuDao.findOne(addMform.getMenuId());
        menu.addItem(cheeseDao.findOne(addMform.getCheeseId()));
        menuDao.save(menu);

        return "redirect:../view/"+menu.getId();

    }
}
