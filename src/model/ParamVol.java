package model;

import DAO.DB;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

@Table(tableName = "ParamVol")
public class ParamVol extends DB {
    
    @PrimaryKey
    @Column(name = "id_paramvol")
    private int id_paramvol;

    @Column(name= "reserv_nb_heure_avant")
    private double nb_heure_avant;

    @Column(name = "cancel_heure_reserv")
    private double cancel_heure_reserv;

    @Column(name = "promotion")
    private double promotion;

    @Column(name = "idvol")
    private int idvol;

    public void setCancel_heure_reserv(double cancel_heure_reserv) {
        this.cancel_heure_reserv = cancel_heure_reserv;
    }


    public void setId_paramvol(int id_paramvol) {
        this.id_paramvol = id_paramvol;
    }


    public void setIdvol(int idvol) {
        this.idvol = idvol;
    }

    public void setNb_heure_avant(double nb_heure_avant) {
        this.nb_heure_avant = nb_heure_avant;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }
    
    public double getCancel_heure_reserv() {
        return cancel_heure_reserv;
    }

    public int getId_paramvol() {
        return id_paramvol;
    }

    public int getIdvol() {
        return idvol;
    }
    public double getNb_heure_avant() {
        return nb_heure_avant;
    }

    public double getPromotion() {
        return promotion;
    }
}
