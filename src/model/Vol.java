package model;

import java.sql.Date;

import annotations.BaseObject;
import annotations.Column;
import annotations.PrimaryKey;

public class Vol {

    @PrimaryKey
    @Column(name = "id_vol")
    int id;
    @Column(name = "date_debut")
    Date dtDebut;
    @Column(name = "date_fin")
    Date dtFin;
    @BaseObject(idBaseName="id_avion")
    Avion avion;

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
    }
    
    public void setDtDebut(Date dtDebut) {
        this.dtDebut = dtDebut;
    }

    public void setDtFin(Date dtFin) {
        this.dtFin = dtFin;
    }
    public void setId(int idVol) {
        this.id = idVol;
    }

    public Date getDtDebut() {
        return dtDebut;
    }

    public Date getDtFin() {
        return dtFin;
    }
    public int getIdVol() {
        return id;
    }
    
}