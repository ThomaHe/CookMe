/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.RecipeModel;

/**
 *
 * @author Sylvain
 */
public class RecipesDao {

    //TODO

    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public RecipesDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    public void addRecipe(RecipeModel recipe) {
        // Création de la requête 
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String query = " insert into recette (title, duration, expertise, nbpeople, type, description) values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, recipe.getTitle());
            preparedStmt.setInt(2, recipe.getDuration());
            preparedStmt.setInt(3, recipe.getExpertise());
            preparedStmt.setInt(4, recipe.getNbpeople());
            preparedStmt.setString(5, recipe.getType());
            preparedStmt.setString(6, recipe.getDescription());

            // execute the preparedstatement
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RecipeModel> getAllRecipes() {
        //return value
        ArrayList<RecipeModel> RecipeModelList = new ArrayList<RecipeModel>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // create connection 
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM recette");

            while (rs.next()) {
                
                RecipeModel recep = new RecipeModel();
                recep.setDescription(rs.getString("description"));
                recep.setDuration(rs.getInt("duration"));
                recep.setExpertise(rs.getInt(rs.getInt("expertise")));
                recep.setNbpeople(rs.getInt("nbpeople"));
                recep.setTitle(rs.getString("title"));
                recep.setType(rs.getString("type"));                
                RecipeModelList.add(recep);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return RecipeModelList;
    }
    
    
    public ArrayList<RecipeModel> Search(ArrayList<String> keyword) {
        //return value
        ArrayList<RecipeModel> RecipeModelList = new ArrayList<RecipeModel>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // create connection 
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            stmt = connection.createStatement();
            
            StringBuilder query = new StringBuilder("SELECT * FROM recette where 1=1 ");
            for(String key : keyword){
                query.append(" and description LIKE %" + key +"%");
            }
            
            rs = stmt.executeQuery(query.toString());

            while (rs.next()) {
                
                RecipeModel recep = new RecipeModel();
                recep.setDescription(rs.getString("description"));
                recep.setDuration(rs.getInt("duration"));
                recep.setExpertise(rs.getInt(rs.getInt("expertise")));
                recep.setNbpeople(rs.getInt("nbpeople"));
                recep.setTitle(rs.getString("title"));
                recep.setType(rs.getString("type"));                
                RecipeModelList.add(recep);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return RecipeModelList;
    }
}
