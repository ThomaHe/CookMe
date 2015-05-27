/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Sylvain
 */
@ManagedBean 
@RequestScoped 
//Durée de vue uniquement lors d'une requète 
//même propriétés que UserModelBean mais portée différente 
public class UserSubmissionModelBean extends UserModelBean{
    private String pwd2;
    
    public UserSubmissionModelBean() {
    }
    
    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd) {
        this.pwd2 = pwd;
    }
}
