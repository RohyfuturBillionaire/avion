package controllers;
import java.time.LocalDateTime;
import java.sql.Connection;
import connect.Dbconn;
import model.Reservation;
import model.Utilisateur;
import model.Vol;
import outils.Controller;
import outils.ErrorUrl;
import outils.ModelView;
import outils.MySession;
import outils.Post;
import outils.Url;
import outils.User;

@Controller
public class ReservationController {
    @Post
    @Url(path = "/reserver")
    @ErrorUrl(url = "/list")
    public ModelView reserver(int id,MySession session) throws Exception{
        User user= (User)session.get("user");
        Connection connection= Dbconn.getConnection();
        ModelView view= new ModelView();
        Reservation reservation= new Reservation();
        reservation.setDateReservation(LocalDateTime.now());
        reservation.setUser((Utilisateur)new Utilisateur().getById(connection,(int)user.getInfo("id")));
        reservation.setVol((Vol)new Vol().getById(connection,id));
        reservation.inserer(connection);
        connection.close();
        view.setUrl("list");
        return view;
    }
}
