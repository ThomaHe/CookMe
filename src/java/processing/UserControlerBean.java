/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import model.UserSubmissionModelBean;
import dao.instance.UserDao;
import dao.fabric.DaoFabric;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import model.LoginBean;
import model.UserModelBean;

/**
 *
 * @author Sylvain
 */
@ManagedBean
@ApplicationScoped // Utilisation de application scope afin d'offrir un point d'entrée unique à l'ensemble des clients

public class UserControlerBean {

    private UserDao userDao;

    public UserControlerBean() {
        this.userDao = DaoFabric.getInstance().createUserDao();
    }

    public String checkUser(LoginBean loginBean) {
        UserModelBean user = userDao.GetUser(loginBean.getLogin());

        if (user != null) {
            if (user.getPwd().equals(loginBean.getPwd())) {
                //récupère l'espace de mémoire de JSF
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                //place l'utilisateur dans l'espace de mémoire de JSF
                sessionMap.put("loggedUser", user);
                //redirect the current page
                return "userdisplay.xhtml"; // TODO à modifier par bon nom
            } else {
                // Message : Mot de passe mauvais
                // TODO
                return "userLogin.xhtml";   // TODO à modifier par bon nom
            }
        } else {
            // Message : login inexistant
            // TODO
            //redirect the current page
            return "userLogin.xhtml";   // TODO à modifier par bon nom
        }
    }

    public String checkAndAddUser(UserSubmissionModelBean userSubmitted) {
        //Vérifier les propriétés de l'utilisateur
        if (userSubmitted.getPwd().equals(userSubmitted.getPwd2())) {
            int i = 0;

            for (String login : userDao.getUsersLogin()) {
                if (userSubmitted.getLogin().equals(login)) {
                    i = 1;
                }
            }
            if (i == 1) {
                // Message : login déjà pris // Pop-up compte non créé
                // TODO
                //redirect the count creation page
                return "userCountCreation.xhtml"; // TODO
            } else {
                //UserSubmissionModelBean
                //ajout de l'utilisateur à la base de données
                this.userDao.addUser(userSubmitted);
                //récupère l'espace de mémoire de JSF
                ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                Map<String, Object> sessionMap = externalContext.getSessionMap();
                //place l'utilisateur dans l'espace de mémoire de JSF
                sessionMap.put("loggedUser", userSubmitted);
                //pop-up compte créé TODO
                return "userCountCreated.xhtml"; // TODO
            }

            /*while ((!userSubmitted.getLogin().equals(userDao.getUsersLogin().get(i))) || (i == userDao.getUsersLogin().size)) {
             i++;
             }
             if (userSubmitted.getLogin() != userDao.getUsersLogin().get(i)) {            
             }*/
        } else {
            // Message : 2 mdp donnés différents // Pop-up compte non créé
            // TODO
            //redirect the count creation page
            return "userCountCreation.xhtml";
        }
    }

    public String disconnectUser() {
        //récupère l'espace de mémoire de JSF
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();

        UserModelBean user = (UserModelBean) sessionMap.get("loggedUser");

        if (user != null) {
            // Enregistre l'heure de la dernière connexion
            // the string representation of date (month/day/year)
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss");
            // Get the date today using Calendar object.
            Date today = Calendar.getInstance().getTime();        
            // Using DateFormat format method we can create a string 
            String reportDate = df.format(today);      
            userDao.setLastConnection(user, reportDate);
            //place l'utilisateur dans l'espace de mémoire de JSF
            sessionMap.remove("loggedUser", null);
            //redirect the current page // Pop-up
            return "userNotConnected.xhtml"; // TODO à modifier par bon nom
        } 
        else {
            // Déconnexion impossible car pas connecté
            return null;
        }
    }
    
    public void adminDeleteUser(UserModelBean user) {
        userDao.deleteUser(user);
    }
    
    public void adminUpdateUser(UserModelBean user){
        userDao.updateUser(user);
    }
}
