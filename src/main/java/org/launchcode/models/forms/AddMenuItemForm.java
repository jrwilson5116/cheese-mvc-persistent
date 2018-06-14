package org.launchcode.models.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {
    private Menu menu;
    private Iterable<Cheese> cheeses;
    @NotNull
    private int menuId;
    @NotNull
    private int cheeseId;


    public Menu getMenu(){
        return menu;
    }

    public void setMenu(){
        this.menu=menu;
    }

    public Iterable getCheeses(){
        return cheeses;
    }

    public void setCheeses(){
        this.cheeses=cheeses;
    }

    public int getMenuId(){
        return menuId;
    }

    public void setMenuId(){
        this.menuId=menuId;
    }

    public int getCheeseId(){
        return cheeseId;
    }

    public void setCheeseId(){
        this.cheeseId=cheeseId;
    }

    public AddMenuItemForm(){
    }

    public AddMenuItemForm(Menu menu, Iterable cheeses){
        this.menu=menu;
        this.cheeses=cheeses;
    }
}
