package controllers;

import java.sql.Connection;

import connect.Dbconn;
import model.Utilisateur;
import outils.Controller;
import outils.Get;
import outils.ModelView;
import outils.ObjectParam;
import outils.Url;
import outils.Post;

@Controller
public class InscriptionController {
    
    @Get
    @Url(path ="/login")
    public ModelView login() {
        ModelView mv = new ModelView();
        mv.setUrl("login.jsp");
        return mv;
    }

    @Get
    @Url(path ="/inscription")
    public ModelView inscription() {
        
        ModelView mv = new ModelView();
        mv.setUrl("inscription.jsp");
        return mv;
    }

    @Post
    @Url(path ="/inscriptionPost")
    public ModelView inscriptionPost( @ObjectParam(name = "user") Utilisateur user) {
        System.out.println("user name="+user.getNomUtilisateur());
        System.out.println("user info"+user.getId());
    
        try {
            Connection conn=Dbconn.getConnection();
            user.inserer(conn);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        ModelView mv = new ModelView();
        mv.setUrl("inscription.jsp");
        return mv;
    }

}
