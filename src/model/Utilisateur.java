package model;

import DAO.DB;
import annotations.Column;
import annotations.PrimaryKey;
import annotations.Table;

// CREATE TABLE utilisateur(
//    id_utilisateur SERIAL,
//    nom_utilisateur VARCHAR(50) ,
//    pwd_utilisateur VARCHAR(255) ,
//    PRIMARY KEY(id_utilisateur)
// );

@Table(tableName = "utilisateur")
public class Utilisateur extends DB {
    @PrimaryKey
    @Column(name = "id_utilisateur")
    int id;
    @Column(name = "nom_utilisateur")
    String nomUtilisateur;
    @Column(name = "pwd_utilisateur")
    String pwdUtilisateur;

    public void setId(int idUtilisateur) {
        this.id = idUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setPwdUtilisateur(String pwdUtilisateur) {
        this.pwdUtilisateur = pwdUtilisateur;
    }

    public int getId() {
        return id;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPwdUtilisateur() {
        return pwdUtilisateur;
    }
    
}
