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
public class Element {
    private int idelement;
    private String nom;
    private String image;

    public Element(int idelement, String nom, String image) {
        this.idelement = idelement;
        this.nom = nom;
        this.image = image;
    }

    public Element(String nom, String image) {
        this.nom = nom;
        this.image = image;
    }

    public int getIdelement() {
        return idelement;
    }

    public void setIdelement(int idelement) {
        this.idelement = idelement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
