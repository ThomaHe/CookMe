/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import dao.instance.RecipesDao;
import dao.fabric.DaoFabric;
import model.RecipeListModelBean;
import model.RecipeModel;


/**
 *
 * @author Sylvain
 */
@ManagedBean
@ApplicationScoped

public class RecipeControlerBean {
    private RecipesDao recipeDao;
    public RecipeControlerBean() {
        this.recipeDao=DaoFabric.getInstance().createRecipesDao();
    }
    
    public void loadAllRecipe(){
        ArrayList<RecipeModel> list = this.recipeDao.getAllRecipes();
        RecipeListModelBean recipeList = new RecipeListModelBean();
        for(RecipeModel recipe:list){
            recipeList.addRecipeList(recipe);
        }
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        //place la liste de recette dans l'espace de mémoire de JSF
        sessionMap.put("recipeList", recipeList);
    }
}
