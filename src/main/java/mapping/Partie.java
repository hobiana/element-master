/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapping;

import java.util.ArrayList;

/**
 *
 * @author Hobiana
 */
public class Partie {
    private ArrayList<String> historique=new ArrayList<String>();
    private Utilisateur user;

    public Partie(Utilisateur user) {
        this.user = user;
    }

    public ArrayList<String> getHistorique() {
        return historique;
    }

    public void setHistorique(ArrayList<String> historique) {
        this.historique = historique;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    
}
