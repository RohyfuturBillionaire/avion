package model;
import java.sql.Connection;
// import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import DAO.DB;
import annotations.BaseObject;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;
import connect.Dbconn;
@Table(tableName = "vol")
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
    
    @BaseObject(idBaseName ="id_avion")
    Avion avion;

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

    public void setAvion(int id) throws Exception
        {   
            Connection conn=Dbconn.getConnection();
            System.out.println("index "+id);
            Avion avion=(Avion)(new Avion().getById(conn, id));
            avion.setId(id);
            this.setAvion(avion);
            conn.close();
        }
    
    
    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
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