package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import DAO.DB;
import annotations.BaseObject;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

@Table(tableName = "reservation")
public class Reservation extends DB {
    @PrimaryKey
    @Column(name = "id_reservation")
    int id;

    @Column(name = "dt_reservation")
    Timestamp dateReservation;

    @BaseObject(idBaseName = "id_utilisateur")
    Utilisateur user;

    @BaseObject(idBaseName = "id_vol")
    Vol vol;

    public int getId() {
        return id;
    }


    public Utilisateur getUser() {
        return user;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }


    public Vol getVol() {
        return vol;
    }

    public void setDateReservation(String dtDebut) {
        LocalDateTime localDateTime= LocalDateTime.parse(dtDebut);
        this.dateReservation = Timestamp.valueOf(localDateTime);
    }
    public void setDateReservation(LocalDateTime time){
        this.dateReservation = Timestamp.valueOf(time);
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

}
