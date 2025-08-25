package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import DAO.DB;
import annotations.BaseObject;
import annotations.Column;
import annotations.Table;
import connect.Dbconn;

@Table(tableName = "v_statistique")
public class Statistique extends DB {
    @BaseObject(idBaseName = "id_utilisateur")
    Utilisateur user;
        
    @BaseObject(idBaseName = "id_vol")
    Vol vol;

    @Column(name = "dt_debut_vol")
    Timestamp dtDebut;
    
    @Column(name = "dt_fin_vol")
    Timestamp dtFin;

    @BaseObject(idBaseName ="id_avion")
    Avion avion;

    @BaseObject(idBaseName="id_ville_depart")
    Ville villeDepart;
    
    @BaseObject(idBaseName="id_ville_arrive")
    Ville villeArrivee;

    @Column(name = "date_butoire_paiement")
    private Date dateButoirePaiement;

    @Column(name = "dt_reservation")
    Timestamp dateReservation;
    @Column(name = "paiement")
    boolean paiment;

    @Column(name = "nbr_billet")
    int nbrBillet;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setNbrBillet(int nbr){
        nbrBillet=nbr;
    }

    public int getNbrBillet(){
        return nbrBillet;
    }

    public void setPaiment(boolean paiment){
        this.paiment=paiment;
    }

    public boolean getPaiment(boolean paiment){
        return paiment;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }
    
    public Vol getVol() {
        return vol;
    }

    public void setDateButoirePaiment(String dateButoire){
        this.dateButoirePaiement =Date.valueOf(dateButoire);
    }

    public Date getDateButoirePaiment(){
        return this.dateButoirePaiement;
    }

    public void setDtDebut(String dtDebut) {
        LocalDateTime localDateTime= LocalDateTime.parse(dtDebut);

        this.dtDebut = Timestamp.valueOf(localDateTime);
    }

    public void setDtDebut(Timestamp dtDebut) {
        this.dtDebut = dtDebut;
    }

    public void setDtFin(Timestamp dtFin) {
        this.dtFin = dtFin;
    }
    public void setDtFin(String dtFin) {
        LocalDateTime localDateTime= LocalDateTime.parse(dtFin);
        this.dtFin = Timestamp.valueOf(localDateTime);
    }



    public void setDateReservation(String dtDebut) {
        LocalDateTime localDateTime= LocalDateTime.parse(dtDebut);
        this.dateReservation = Timestamp.valueOf(localDateTime);
    }
    public void setDateReservation(LocalDateTime time){
        this.dateReservation = Timestamp.valueOf(time);
    }
    

    
    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    

    public void setVilleDepart(int id) throws Exception
        {
            Connection conn=Dbconn.getConnection();
            Ville ville= (Ville)( new Ville().getById(conn, id));
            ville.setId(id);
            conn.close();
            this.villeDepart=ville;
        }
    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }
    
    public void  setVilleArrivee(int ville) throws Exception
        {
            Connection conn=Dbconn.getConnection();
            Ville vill= (Ville)( new Ville().getById(conn, ville));
            vill.setId(ville);
            conn.close();
            this.villeArrivee=vill;

        }
    
    public Ville getVilleArrivee() {
        return villeArrivee;
    }
    

    public Ville getVilleDepart() {
        return villeDepart;
    }

    
    


}
