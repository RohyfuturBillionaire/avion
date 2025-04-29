package controllers;

import java.sql.Connection;
import java.util.List;

import DAO.DB;
import connect.Dbconn;
import model.Avion;
import model.Ville;
import model.Vol;
import outils.Auth;
import outils.Controller;
import outils.DButils;
import outils.ErrorUrl;
import outils.Get;
import outils.ModelView;
import outils.Post;
import outils.Url;

@Controller
@Auth(role = "user")
public class AvionController {
    
    
    @Get    
    @Url(path ="/list")
    public ModelView accueil() throws Exception {
        Connection conn = Dbconn.getConnection();
        List<DB> Lvilles = new Ville().selectAll(conn);
        List<DB> Lavions = new Avion().selectAll(conn);
        List<DB> Lvols = new Vol().selectAll(conn);
        ModelView mv = new ModelView();
                DB [] res=Lvols.toArray(new DB[0]);
        String filename="C:\\Users\\rohyr\\Documents\\agep\\agep\\src\\main\\resources\\static\\pdf_exported\\volFarany.csv";
        String [] entete= new String[6];
        entete[0]="id";
        entete[1]="dtDebut";
        entete[2]="dtFin";
        entete[3]="idVilleDepart";
        entete[4]="idVilleArrivee";
        entete[5]="idAvion";
        new DButils().ToCSV(res, filename,",", entete);
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
            List<DB> vols= vol.rechercheMultiCritaire(conn,dateDepart,dateArrive,""+villeDepart,""+villeArrive,""+avion);

                List<DB> Lvilles = new Ville().selectAll(conn);
                List<DB> Lavions = new Avion().selectAll(conn);
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
