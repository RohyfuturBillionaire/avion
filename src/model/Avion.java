package model;

import java.sql.Date;

import DAO.DB;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

@Table(tableName = "avion")
public class Avion extends DB {
    @PrimaryKey
    @Column(name = "id_avion")
    int id;
    @Column(name = "modele_Avion")
    String modelAvion;
    @Column(name = "datefabrication_avion")
    Date dateFabrication;

    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = Date.valueOf(dateFabrication);
    }

    public void setId(int idAvion) {
        this.id = idAvion;
    }

    public void setModelAvion(String modelAvion) {
        this.modelAvion = modelAvion;
    }
    public Date getDateFabrication() {
        return dateFabrication;
    }
    public int getId() {
        return id;
    }
    public String getModelAvion() {
        return modelAvion;
    }
}
