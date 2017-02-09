/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;

import java.io.PrintWriter;

/**
 *
 * @author ITU
 */
public class JSONFonction {
    public static void getElementsFusionnesJSON(String idElements, String idtoken, PrintWriter out){
        Fonction f = new Fonction();
        String idUtilisateur = f.getIdUtilisateur_with_token(idtoken);
        out.print(f.fusionnerJSON(idElements, idUtilisateur));
    }
    public static void getElementsFissionnesJSON(String idElement, PrintWriter out){
        Fonction f = new Fonction();
        out.print(f.fissionnerJSON(idElement));
    }
    public static void getElementsDebloquesJSON(String idtoken, PrintWriter out)
    {
        Fonction f = new Fonction();
        String idUtilisateur = f.getIdUtilisateur_with_token(idtoken);
        out.print(f.getElementsDebloques(idUtilisateur));
    }
    public static void getElementDefisJSON(String idtoken, PrintWriter out)
    {
        Fonction f = new Fonction();
        String idUtilisateur = f.getIdUtilisateur_with_token(idtoken);
        out.print(f.getElementAleatoireJSON(idUtilisateur));
    }
    public static void getElementsBaseJSON(PrintWriter out)
    {
        Fonction f = new Fonction();
        out.print(f.getElementsBaseJSON());
    }
    public static void getLevel(String idtoken,PrintWriter out)
    {
        Fonction f = new Fonction();
        out.print(f.getElementsBaseJSON());
    }
    public static void getScore(PrintWriter out)
    {
        Fonction f = new Fonction();
        out.print(f.getBestScore());
    }
    public static void upgradeLevel(String idToken,String temps,String level)
    {
        Fonction f = new Fonction();
        String idUser = f.getIdUtilisateur_with_token(idToken);
        f.insertScore(idUser, temps, level);
        f.updateNiveauUser(idUser);
    }
    
}
