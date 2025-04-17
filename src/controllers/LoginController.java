package controllers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DAO.DB;
import connect.Dbconn;
import model.Utilisateur;
import outils.Controller;
import outils.Get;
import outils.ModelView;
import outils.MySession;
import outils.Post;
import outils.Url;
import outils.User;

@Controller
public class LoginController {
    
    
    @Post    
    @Url(path ="/loginBack")
    public ModelView accueil(String nomUtilisateur,String pwdUtilisateur,MySession session) throws Exception {
        Connection connection= Dbconn.getConnection();
        ModelView model= new ModelView();
        List<DB> users=(new Utilisateur()).rechercheMultiCritaire(connection,nomUtilisateur,pwdUtilisateur);
        System.out.println("user size "+users.size());
        if (users.size()>0) {
            model.setUrl("list");
            User user = new User();
            user.setRole("user");
            user.addInfo("id",((Utilisateur)users.get(0)).getId());
            user.setUsername(nomUtilisateur);
            session.set("user", user);
        }
        else{
            model.setUrl("login");
        }
        connection.close();
        return model;    
    }

}
