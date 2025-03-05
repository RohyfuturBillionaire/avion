package controllers;

import java.sql.Connection;

import connect.Dbconn;
import model.Utilisateur;
import outils.Controller;
import outils.Get;
import outils.ModelView;
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
    @Url(path ="/inscription")
    public ModelView inscriptionPost( Utilisateur user) {
       Connection conn=Dbconn.getConnection();
        try {
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
