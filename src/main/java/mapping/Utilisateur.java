/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

/**
 *
 * @author Hobiana
 */
public class Utilisateur {
    private int idUtilisateure;
    private String nom;
    private String pseudo;
    private String email;

    public Utilisateur(String nom, String pseudo, String email) {
        this.nom = nom;
        this.pseudo = pseudo;
        this.email = email;
    }

    public int getIdUtilisateure() {
        return idUtilisateure;
    }

    public void setIdUtilisateure(int idUtilisateure) {
        this.idUtilisateure = idUtilisateure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
