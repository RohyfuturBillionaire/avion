package controllers;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;

import DAO.DB;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import connect.Dbconn;
import model.Reservation;
import model.Utilisateur;
import model.Vol;
import outils.Controller;
import outils.DButils;
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
    @Post
    @Url(path = "/listReservation")
    public ModelView listReservation(MySession session) throws Exception{
        User user=(User)session.get("user");
    
        Connection connection=Dbconn.getConnection();
        List<DB> reservation = new Reservation().rechercheMultiCritaire(connection,"",""+(int)user.getInfo("id"),"");
        DB [] res=reservation.toArray(new DB[0]);
        String filename="C:\\Users\\rohyr\\Documents\\agep\\agep\\src\\main\\resources\\static\\pdf_exported\\reservationFarany.csv";
        String [] entete= new String[4];
        entete[0]="id";
        entete[1]="dateReservation";
        entete[2]="iduser";
        entete[3]="idvol";
        new DButils().ToCSV(res, filename,",", entete);
        ModelView view= new ModelView();
        view.setUrl("listReservation.jsp");
        view.add("reservations", reservation);
        connection.close();
        return view;

    }

    @Post
    @Url(path  = "/exportReservation")
    public ModelView export( int id) throws Exception {
        // Lire la réservation par ID
        Connection connection= Dbconn.getConnection();
        Reservation reservation = (Reservation)new Reservation().getById(connection, id);

        // Convertir la réservation en JSON (vous pouvez utiliser une bibliothèque comme
        // Gson ou Jackson)
        String reservationJson = convertReservationToJson(reservation);

        // URL de l'API pour générer le PDF
        String apiUrl = "http://localhost:8081/api/pdf/generate";

        // Effectuer un appel HTTP POST vers l'API
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(reservationJson))
                .build();

        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        // Vérifier la réponse de l'API
        if (response.statusCode() == 200) {
            String fileName = "reservation_" + id + ".pdf";
            String directoryPath = "public/files/";
            String filePath = directoryPath + fileName;

            // Créer le dossier s’il n’existe pas
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs(); // Crée le dossier et ses parents si besoin
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(response.body());
            }

            ModelView mv = new ModelView();
            mv.setUrl("listReservation");
            
            return mv;
        } else {
            throw new Exception("Erreur lors de la génération du PDF : " + response.statusCode());
        }
    }

    // Méthode utilitaire pour convertir une réservation en JSON
    private String convertReservationToJson(Reservation reservation) {
        // Exemple avec Gson (vous pouvez utiliser Jackson si nécessaire)
        Gson gson = new Gson();
        return gson.toJson(reservation);
    }
}
