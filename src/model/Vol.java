package model;

import java.sql.Date;
import java.sql.Timestamp;

import DAO.DB;
import annotations.BaseObject;
import annotations.Column;
import annotations.PrimaryKey;

public class Vol  extends DB{

    @PrimaryKey
    @Column(name = "id_vol")
    int id;
    @Column(name = "dt_debut_vol")
    Timestamp dtDebut;
    @Column(name = "dt_fin_vol")
    Timestamp dtFin;

    @BaseObject(idBaseName="id_ville_depart")
    Ville villeDepart;
    
    @BaseObject(idBaseName="id_ville_arrive")
    Ville villeArrivee;
    
    @BaseObject(idBaseName="id_avion")
    Avion avion;

    public void setVilleDepart(Ville villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleArrivee(Ville villeArrivee) {
        this.villeArrivee = villeArrivee;
    }
    
    public Ville getVilleArrivee() {
        return villeArrivee;
    }
    

    public Ville getVilleDepart() {
        return villeDepart;
    }
    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
    }
    
    public void setDtDebut(String dtDebut) {
        this.dtDebut = Timestamp.valueOf(dtDebut);
    }

    public void setDtDebut(Timestamp dtDebut) {
        this.dtDebut = dtDebut;
    }

    public void setDtFin(Timestamp dtFin) {
        this.dtFin = dtFin;
    }
    public void setId(int idVol) {
        this.id = idVol;
    }

    public Timestamp getDtDebut() {
        return dtDebut;
    }

    public Timestamp getDtFin() {
        return dtFin;
    }
    public int getId() {
        return id;
    }
    
}