/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import fonction.Fonction;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapping.Element;
import mapping.Partie;
import mapping.Utilisateur;

/**
 *
 * @author Hobiana
 */
public class Essai {
    public static void main(String[] args){
        Fonction f=new Fonction();
        /*
        Element[] mere=new Element[2];
        mere[0]=new Element(2, "Feu", "element/feu.jpg");
        mere[1]=new Element(4, "Terre", "element/terre.jpg");
        
        Element[] fille=new Element[1];
        fille[0]=new Element(6, "Lave", "element/lave.jpg");
        
        f.ajouterFusion(mere, fille);
        
        */
        Utilisateur user=new Utilisateur("Hobs", "Hobs", "hobs@gmail.com");
        user.setIdUtilisateure(1);
        Partie p=new Partie(user);
        
        Element e1=new Element(2, "Feu", "element/feu.jpg");
        Element e2=new Element(6, "Lave", "element/lave.jpg");
        Element e3=new Element(5, "Boue", "element/boue.jpg");
        Element e4=new Element(1,"Eau","elemnt/eau.jpg");
        Element e5=new Element(4,"Terre","element/terre.jpg");
        Element e6=new Element(11,"hydrogene","element/terre.jpg");
        Element e7=new Element(12,"oxygene","element/terre.jpg");
        Element e8=new Element(13,"carbone","element/terre.jpg");
        Element[] listeval = null;
        try {
            Element[] e={e6,e7,e8};
            f.fusionner(e,p);
            f.fissionner(e3,p);
        } catch (Exception ex) {
            Logger.getLogger(Essai.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<p.getHistorique().size();i++){
            System.out.println(p.getHistorique().get(i));
        }
        
        /*
        ArrayList<Element[]> listemere=f.listeElementMere();
        for(int i=0;i<listemere.size();i++){
            String mere="";
            for(int j=0;j<listemere.get(i).length;j++){
                mere+=listemere.get(i)[j].getNom();
                mere+="   ";
            }
            System.out.println(mere);
        }
        */
        
    }
}
