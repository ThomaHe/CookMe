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
import model.UserModelBean;
import model.UserSubmissionModelBean;

/**
 *
 * @author Sylvain
 */
public class UserDao {

    private Connection connection;
    private String dB_HOST;
    private String dB_PORT;
    private String dB_NAME;
    private String dB_USER;
    private String dB_PWD;

    public UserDao(String DB_HOST, String DB_PORT, String DB_NAME, String DB_USER, String DB_PWD) {
        dB_HOST = DB_HOST;
        dB_PORT = DB_PORT;
        dB_NAME = DB_NAME;
        dB_USER = DB_USER;
        dB_PWD = DB_PWD;
    }

    
    public void addUser(UserModelBean user) {
// Création de la requête 
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String query = " insert into users (firstname, lastname, age, login, pwd, email) values (?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getFirstname());
            preparedStmt.setString(2, user.getLastname());
            preparedStmt.setInt(3, user.getAge());
            preparedStmt.setString(4, user.getLogin());
            preparedStmt.setString(5, user.getPwd());
            preparedStmt.setString(6, user.getEmail());

            // execute the preparedstatement
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    //Update users SET lastconnection = ? WHERE login = ?"
    public void updateUser(UserModelBean user) {
    // Création de la requête 
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String query = " update  users set firstname=?, lastname=?, age=?, login=?, pwd=?, email=? where login = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getFirstname());
            preparedStmt.setString(2, user.getLastname());
            preparedStmt.setInt(3, user.getAge());
            preparedStmt.setString(4, user.getLogin());
            preparedStmt.setString(5, user.getPwd());
            preparedStmt.setString(6, user.getEmail());
            preparedStmt.setString(7, user.getLogin());

            // execute the preparedstatement
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void deleteUser(UserModelBean user) {
// Création de la requête 
        try {
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String query = " delete from users where login = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, user.getLogin());
           

            // execute the preparedstatement
            preparedStmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public UserModelBean GetUser(String login) {
        //return value
        UserModelBean user = new UserModelBean();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // create connection 
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users where login = '" + login + "'");

            user.setAge(rs.getInt("age"));
            user.setLastname(rs.getString("lastname"));
            user.setLogin(rs.getString("login"));
            user.setPwd(rs.getString("pwd"));
            user.setFirstname(rs.getString("firstname"));
            user.setEmail(rs.getString("email"));
            user.setLastconnection(rs.getString("lastconnection"));

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    
    public ArrayList<UserModelBean> getAllUser() { //
        //return value
        ArrayList<UserModelBean> userList = new ArrayList<UserModelBean>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // create connection 
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {

                UserModelBean user = new UserSubmissionModelBean();
                user.setAge(rs.getInt("age"));
                user.setLastname(rs.getString("lastname"));
                user.setLogin(rs.getString("login"));
                user.setPwd(rs.getString("pwd"));
                user.setFirstname(rs.getString("firstname"));
                user.setEmail(rs.getString("email"));
                user.setLastconnection(rs.getString("lastconnection"));

                userList.add(user);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    
    public ArrayList<String> getUsersLogin() { //
        //return value
        ArrayList<String> loginList = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // create connection 
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT login FROM users");

            while (rs.next()) {
                loginList.add(rs.getString("login"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginList;
    }

    
    public void setLastConnection(UserModelBean user, String date) {
        

        try {
            connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + dB_HOST + ":" + dB_PORT + "/" + dB_NAME, dB_USER, dB_PWD);
            String query = " Update users SET lastconnection = ? WHERE login = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString(1, date);
            preparedStmt.setString(2, user.getLogin());


            // execute the preparedstatement
            preparedStmt.execute();
            connection.close();
            
            user.setLastconnection(date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
