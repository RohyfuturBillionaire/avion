package controllers;

import java.sql.Connection;
import java.util.List;

import DAO.DB;
import connect.Dbconn;
import model.Avion;
import model.Ville;
import model.Vol;
import outils.Controller;
import outils.ErrorUrl;
import outils.Get;
import outils.ModelView;
import outils.Post;
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

    @Post
    @Url(path = "/listWcritaire")
    @ErrorUrl(url = "/list")
    public ModelView rechercheMultiCritaire(int avion,String dateDepart,String dateArrive,int villeDepart,int villeArrive) throws Exception
        {   Vol vol= new Vol();
           
            Connection conn = Dbconn.getConnection();
            System.out.println("ville depart= "+villeDepart);
            List<Object> vols= vol.rechercheMultiCritaire(conn,dateDepart,dateArrive,""+villeDepart,""+villeArrive,""+avion);

                List<Object> Lvilles = new Ville().selectAll(conn);
                List<Object> Lavions = new Avion().selectAll(conn);
                // List<Object> Lvols = new Vol().selectAll(conn);
                ModelView mv = new ModelView();
                mv.setUrl("accueil.jsp");
                mv.add("Lavions",Lavions);
                mv.add("Lvilles",Lvilles);
                mv.add("Lvols",vols);
                conn.close();
                return mv;

        }
}
