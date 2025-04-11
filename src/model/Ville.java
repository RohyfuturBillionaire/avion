package model;

import DAO.DB;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

@Table(tableName = "ville")
public class Ville extends DB {

    @PrimaryKey
    @Column(name = "id_ville")
    private int id;
    @Column(name = "nom_ville")
    private String nomVille;

public void setId(int id) {
    this.id = id;
}


public void setNomVille(String nomVille) {
    this.nomVille = nomVille;
}

public int getId() {
    return id;
}
public String getNomVille() {
    return nomVille;
}
    
}
