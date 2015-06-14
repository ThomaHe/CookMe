/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sylvain
 */

@ManagedBean 
@RequestScoped 
public class RecipeListModelBean {

    private List<RecipeModel> recipeList;

    public RecipeListModelBean() {
        recipeList = new ArrayList<RecipeModel>();
    }

    public void addRecipeList(RecipeModel recipe) {
        this.recipeList.add(recipe);
    }

    public List<RecipeModel> getRecipeList() {
        return recipeList;
    }
}
