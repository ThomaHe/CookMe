/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processing;

import dao.fabric.DaoFabric;
import model.UserModelBean;

/**
 *
 * @author Angèle
 */
public class CommentaireControlerBean {
    private CommentaireDao commentaireDao;
    public CommentaireControlerBean() {
        this.commentaireDao=DaoFabric.getInstance().createRecipesDao();
    }
    
     
    public void userAddCommentaire (UserModelBean user, String commentaire, int note){
        commentaireDao.addCommentaire(user, commentaire, note);
    }
    
    public void userUpdateCommentaire (UserModelBean user, String commentaire, int note){
        commentaireDao.updateCommentaire(user, commentaire, note);
    }
    
    public void userDeleteCommentaire (UserModelBean user, String commentaire, int note){
        commentaireDao.deleteCommentaire(user, commentaire, note);
    }
}
