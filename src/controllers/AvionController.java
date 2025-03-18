package controllers;

import java.sql.Connection;
import java.util.List;

import DAO.DB;
import connect.Dbconn;
import model.Avion;
import model.Ville;
import model.Vol;
import outils.Controller;
import outils.Get;
import outils.ModelView;
import outils.Url;

@Controller
public class AvionController {
    
    
    @Get    
    @Url(path ="/list")
    public ModelView accueil() throws Exception {
        Connection conn = Dbconn.getConnection();
        List<Object> Lvilles = new Ville().selectAll(conn);
        List<Object> Lavions = new Avion().selectAll(conn);
        List<Object> Lvols = new Vol().selectAll(conn);
        ModelView mv = new ModelView();
        mv.setUrl("accueil.jsp");
        mv.add("Lavions",Lavions);
        mv.add("Lvilles",Lvilles);
        mv.add("Lvols",Lvols);
        conn.close();
        return mv;
    }

    
}
