package controllers;

import java.sql.Timestamp;
import java.sql.Connection;
import java.util.List;

import javax.sound.midi.MidiEvent;

import DAO.DB;
import connect.Dbconn;
import model.Avion;
import model.ParamVol;
import model.Ville;
import model.Vol;
import outils.Auth;
import outils.Controller;
import outils.ErrorUrl;
import outils.Get;
import outils.ModelView;
import outils.ObjectParam;
import outils.Post;
import outils.Url;

@Controller
public class VolController {
    @Get
    @Url(path ="/insertionVol")
    public ModelView insertion() throws Exception { 
        Connection conn = Dbconn.getConnection();
        ModelView mv = new ModelView();
        List<DB> Lvilles = new Ville().selectAll(conn);
        List<DB> Lavions = new Avion().selectAll(conn);
        List<DB> Lvols = new Vol().selectAll(conn);
        mv.setUrl("insertionVole.jsp");
        mv.add("Lavions",Lavions);
        mv.add("Lvilles",Lvilles);
        mv.add("Lvols",Lvols);
        conn.close();
        return mv;
    }

    @Post
    @Url(path = "/deleteVol")
    @ErrorUrl(url = "/list")
    public ModelView delete(int idVol) throws Exception
        {
            Connection conn= Dbconn.getConnection();
            Vol vol= (Vol)new Vol().getById(conn, idVol);
            vol.deleteById(conn, idVol);
            ModelView view= new ModelView();
            view.setUrl("list");
            return view;
        }

    @Post
    @Url(path = "/insertvol")
    @ErrorUrl(url = "/insertionVol")
    @Auth(role = "admin")
    public ModelView insertionVole(int avion,String dtDebut,String dtFin,int villeDepart,int villeArrive,double resaheurelimit,double resacanheurelimit,double promotion) throws Exception {   
            ModelView view= new ModelView();
            view.setUrl("insertionVole.jsp");
            
            Vol vol =new Vol();
            ParamVol paramvol= new ParamVol();
            vol.setAvion(avion);
            vol.setDtDebut(dtDebut);
            vol.setDtFin(dtFin);
            vol.setVilleDepart(villeDepart);
            vol.setVilleArrivee(villeArrive);

            paramvol.setCancel_heure_reserv(resacanheurelimit);
            paramvol.setNb_heure_avant(resaheurelimit);
            paramvol.setPromotion(promotion);
           
             System.out.println(vol.getDtDebut()+"ity aho");
            Connection connection=Dbconn.getConnection();
            List<DB> Lvilles = new Ville().selectAll(connection);
            List<DB> Lavions = new Avion().selectAll(connection);
            List<DB> Lvols = new Vol().selectAll(connection);
            view.setUrl("insertionVole.jsp");
            view.add("Lavions",Lavions);
            view.add("Lvilles",Lvilles);
            view.add("Lvols",Lvols);
            Vol vol2=(Vol)vol.inserer(connection);
            System.out.println("id vol inserere"+ vol2.getId());
            paramvol.setIdvol(vol2.getId());
            paramvol.inserer(connection);
            connection.close();
            return view;
        }

        @Post
    @Url(path = "/updateVolCall")
    @ErrorUrl(url = "/list")
    public ModelView UdapteCallVole(int idVol,int avion,String dtDebut,String dtFin,int villeDepart,int villeArrive) throws Exception {   
            ModelView view= new ModelView();
            view.setUrl("list");
            
            Vol vol =new Vol();
            vol.setAvion(avion);
            vol.setDtDebut(dtDebut);
            vol.setDtFin(dtFin);
            vol.setVilleDepart(villeDepart);
            vol.setVilleArrivee(villeArrive);

            // // Connection conn = Dbconn.getConnection();
             System.out.println(vol.getDtDebut()+"ity aho");
            Connection connection=Dbconn.getConnection();
            // vol.inserer(connection);
            vol.updateById(connection, idVol);
            connection.close();
            return view;
        }




    @Post
    @Url(path = "/updateVol")
    @ErrorUrl(url = "/updateVol")
    public ModelView formulaireUpdateVole(int idVol,int idAvion,String dtDebut,String dtFin,int idVilleDepart,int idVilleArrivee) throws Exception {   
            ModelView view= new ModelView();
            view.setUrl("insertionVole.jsp");
            
            Vol vol =new Vol();
            vol.setId(idVol);
            vol.setAvion(idAvion);
            vol.setDtDebut(dtDebut);
            vol.setDtFin(dtFin);
            vol.setVilleDepart(idVilleArrivee);
            vol.setVilleArrivee(idVilleDepart);
            Connection connection=Dbconn.getConnection();
            List<DB> Lvilles = new Ville().selectAll(connection);
            List<DB> Lavions = new Avion().selectAll(connection);
            List<DB> Lvols = new Vol().selectAll(connection);
            view.setUrl("updateVol.jsp");
            view.add("Lavions",Lavions);
            view.add("Lvilles",Lvilles);
            view.add("Lvols",Lvols);
         view .add("volToUpdate", vol);

            return view;
        }
    
}
