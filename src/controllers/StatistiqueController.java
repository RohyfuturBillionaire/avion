package controllers;
import java.time.LocalDateTime;
import java.util.List;
// import java.util.TimerTask;

import com.google.gson.Gson;

import DAO.DB;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Timestamp;
import java.sql.Connection;
import connect.Dbconn;
import model.ParamVol;
import model.Reservation;
import model.Statistique;
import model.Utilisateur;
import model.Vol;
import outils.Controller;
import outils.DButils;
import outils.ErrorUrl;
import outils.Get;
import outils.ModelView;
import outils.MySession;
import outils.Post;
import outils.RestApi;
import outils.Url;
import outils.User;

@Controller
@RestApi
public class StatistiqueController {

   
   @Get
   @Url(path = "/annulerRes")
    public ModelView annulerReservation(MySession session,String date) throws Exception
        {
            User user=(User)session.get("user");
        // date="2025-04-21";
        //     LocalDateTime localDateTime= LocalDateTime.parse(date);
        //     java.sql.Timestamp dt= java.sql.Timestamp.valueOf(localDateTime);
        //    date=dt.toString();
            Connection connection=Dbconn.getConnection();
        List<DB> reservation = new Reservation().recherche( "id_utilisateur = "+(int)user.getInfo("id")+" and dt_reservation<='"+date+ "' and paiement = false",connection);
        
        for (DB db : reservation) {
            System.out.println("idvol = "+((Reservation)db).getVol().getId()+" classe='"+((Reservation)db).getClasse()+ "' order by date_butoire_paiement limit 1");
            List<DB> paramVol= new ParamVol().recherche("idvol ="+((Reservation)db).getVol().getId()+" and date_butoire_paiement>'"+date+ "' limit 1", connection);
            if (paramVol!=null) {
                ParamVol first= ((ParamVol)paramVol.getFirst());
                first.setNbrBillet(first.getNbrBillet()+1);
                first.setCancel_heure_reserv(0);
                first.setNb_heure_avant(0);
                first.updateById(connection,first.getId_paramvol());     
            }
            ((Reservation)db).deleteById(connection,((Reservation)db).getId());
        }
        ModelView view= new ModelView();
        view.setUrl("listReservation.jsp");
        view.add("reservations", reservation);
        connection.close();
        return view;
}

}
