/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fonction;

import db.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapping.Element;
import mapping.Partie;

/**
 *
 * @author Hobiana
 */
public class Fonction {
    DBUtils db=new DBUtils();
    
    public int getNextsequence(String nomseq,Connection con)
    {
        String seq="select nextval('"+nomseq+"')";
        int sequ=0;
        
        try{

            java.sql.Statement stmt = con.createStatement();
            ResultSet sequence = stmt.executeQuery(seq);
            sequence.next();

            sequ = sequence.getInt(1);

            stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return sequ;
    }
    
    
    public void insertElementDebloque(String idElement,int idutilisateur,Connection con){
        try {
            int sequence=this.getNextsequence("elementdebloque_idelementdebloque_seq", con);
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into elementdebloque values("+sequence+","+idElement+","+idutilisateur+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    public void insertElementDebloque(Element e,int idutilisateur,Connection con){
        try {
            int sequence=this.getNextsequence("elementdebloque_idelementdebloque_seq", con);
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into elementdebloque values("+sequence+","+e.getIdelement()+","+idutilisateur+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public boolean dansElementDebloque(Element e,int idutilisateur,Connection con){
        boolean rep=false;
        try{
            int count=-1;
            String sql= "select count(*) from elementdebloque where idelement="+e.getIdelement()+" and idutilisateur="+idutilisateur;
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            while (res.next())
            {
                count=res.getInt(1);
            }
            if(count>0){
                rep=true;
            }
            
            stmt.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return rep;
    }
    
    public Element[] fusionner(Element[] e,Partie p){
        Connection con=db.getConn();
        Element[] rep=null;
        try{
            String requete="";
            String historique_mere=e[0].getNom();
            for(int i=1;i<e.length;i++){
                requete+="join (select idelementmere from element as e\n" +
                    "join mere as m on m.idelement=e.idelement\n" +
                    "where e.idelement="+e[i].getIdelement()+") as element"+i+"\n" +
                    "on element0.idelementmere=element"+i+".idelementmere ";
                
                historique_mere+= " "+e[i].getNom();
            }
            
            String sql= "select e.idelement,e.nom,e.image from (select idelementmere from element as e\n" +
                        "join mere as m on m.idelement=e.idelement\n" +
                        "where e.idelement="+e[0].getIdelement()+") as element0\n" +requete+
                        "join elementfille as ef on ef.idelementmere=element1.idelementmere\n" +
                        "join element as e on e.idelement=ef.idelement";
            PreparedStatement stmt = con.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = stmt.executeQuery();
            res.last();
            int taille= res.getRow();
            if(taille==0){
                throw new Exception("ne peut pas etre fusionner");
            }
            rep=new Element[taille];
            
            res.beforeFirst();
            int i=0;
            String resultat_fusion="";
            while (res.next())
            {
                rep[i]=new Element(res.getInt(1), res.getString(2), res.getString(3));
                resultat_fusion+=rep[i].getNom()+" ";
                boolean b=this.dansElementDebloque(rep[i], p.getUser().getIdUtilisateure(), con);
                if(b==false){
                    this.insertElementDebloque(rep[i], p.getUser().getIdUtilisateure(), con);
                }
                i++;
            }
            
            String historique="Vous avez fusionné "+historique_mere+" = "+resultat_fusion;
            p.getHistorique().add(historique);
            
            stmt.close();
            //con.commit();
            con.close();
        }
        catch (Exception ex)
        {
            //throw new Exception("erreur eto @ fonction get liste employe reliquat ");
            ex.printStackTrace();
        }
        return rep;
    }
    
    //MODIFIED
    public String fusionnerJSON(String liste_id, String idUtilisateur){
        Connection con=db.getConn();
        Element[] rep=null;
        String json = "[]";
        try{
            String sql= "select e.idelement,nom,image from (select idelementmere from liste_mere\n" +
                        "where idlements='"+liste_id+"') as id\n" +
                        "join elementfille as ef on ef.idelementmere=id.idelementmere\n" +
                        "join element as e on e.idelement=ef.idelement";
            PreparedStatement stmt = con.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = stmt.executeQuery();
            res.last();
            int taille= res.getRow();
            if(taille==0){
                throw new Exception("ne peut pas être fusionné");
            }
            rep=new Element[taille];
            
            res.beforeFirst();
            int i=0;
            while (res.next())
            {
                rep[i]=new Element(res.getInt(1), res.getString(2), res.getString(3));
                boolean b=this.dansElementDebloque(rep[i], Integer.parseInt(idUtilisateur), con);
                if(b==false){
                    this.insertElementDebloque(rep[i], Integer.parseInt(idUtilisateur), con);
                }
                i++;
            }
            
            res.beforeFirst();
            json = JSONUtil.convert(res).toString();
            
            stmt.close();
            //con.commit();
            con.close();
            return json;
        }
        catch (Exception ex)
        {
            //throw new Exception("erreur eto @ fonction get liste employe reliquat ");
            ex.printStackTrace();
        }
        return json;
    }
    
    
    public void insertElementMere(int idelementmere,Connection con){
        try {
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into elementmere values("+idelementmere+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void insertMere(int idelement,int idelementmere,Connection con){
        try {
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into mere values("+idelement+","+idelementmere+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void insertElementFille(Element e,int idelementmere,Connection con){
        try {
            int sequence=this.getNextsequence("elementfille_idelementfille_seq", con);
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into elementfille values("+sequence+","+e.getIdelement()+","+idelementmere+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void ajouterFusion(Element[] mere,Element[] fille){
        Connection con=db.getConn();
        try{
            int idelementmere=this.getNextsequence("elementmere_idelementmere_seq", con);
            
            this.insertElementMere(idelementmere, con);
            
            for(int i=0;i<mere.length;i++){
                System.out.println(mere[i].getIdelement()+"  "+mere[i].getNom());
                this.insertMere(mere[i].getIdelement(), idelementmere, con);
            }
            
            for(int j=0;j<fille.length;j++){
                System.out.println(fille[j].getIdelement()+"  "+mere[j].getNom());
                this.insertElementFille(fille[j], idelementmere, con);
            }
            
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    public Element[] fissionner(Element e,Partie p) throws Exception{
        Connection con=db.getConn();
        
        int idelementMere=this.getIdElementMereFission(e, con);
        Element[] rep=null;
        if(idelementMere!=-1){
            rep=this.getElementMere(idelementMere);
            String historique="Vous avez fissionner "+e.getNom()+" = ";
            for(int i=0;i<rep.length;i++){
                historique+=rep[i].getNom()+" "; 
            }
            p.getHistorique().add(historique);
             
        }
        else throw new Exception("ne peut pas etre fissionner");
        
        return rep;
    }
    
    //MODIFIED
    public String fissionnerJSON(String idel){
        Connection con=db.getConn();
        int idelementMere=this.getIdElementMereFission(idel, con);
        return this.getElementMereJSON(idelementMere);
    }
    
    public Element[] getElementMere(int idelementmere){
        Connection con=db.getConn();
        Element[] rep=null;
        try{
            
            String sql= "select e.idelement,nom,image from element as e \n" +
                        "join mere as m on m.idelement=e.idelement\n" +
                        "where idelementmere="+idelementmere;
            PreparedStatement stmt = con.prepareCall(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet res = stmt.executeQuery();
            res.last();
            int taille= res.getRow();
            rep=new Element[taille];

            res.beforeFirst();
            int i=0;

            while (res.next())
            {
                rep[i]=new Element(res.getInt(1), res.getString(2), res.getString(3));
                i++;
            }
            stmt.close();
            //con.commit();
            con.close();
        }
        catch (Exception ex)
        {
            //throw new Exception("erreur eto @ fonction get liste employe reliquat ");
            ex.printStackTrace();
        }
        return rep;
    }
    
    //MODIFIED
    public String getElementMereJSON(int idelementmere){
        Connection con=db.getConn();
        String json = "[]";
        try{
            
            String sql= "select e.idelement,nom,image from element as e \n" +
                        "join mere as m on m.idelement=e.idelement\n" +
                        "where idelementmere="+idelementmere;
            ResultSet res = con.prepareStatement(sql).executeQuery();
            json = JSONUtil.convert(res).toString();
            //con.commit();
            con.close();
        }
        catch (Exception ex)
        {
            //throw new Exception("erreur eto @ fonction get liste employe reliquat ");
            ex.printStackTrace();
        }
        return json;
    }
    
    //MODIFIED
    public String getElementsDebloques(String utilisateur){
        Connection con=db.getConn();
        String json="[]";
        try{
            
            String sql= "select e.idelement,e.nom,e.image from elementdebloque as ed join element as e on e.idelement=ed.idelement join utilisateurs as u on u.idutilisateur=ed.idutilisateur where u.idutilisateur="+utilisateur;
            
            ResultSet res = con.prepareStatement(sql).executeQuery();
            json = JSONUtil.convert(res).toString();
            //con.commit();
            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return json;
    }
    
    public int getIdElementMereFission(Element e,Connection con){
        int rep=-1;
        try {            
            String sql="select idelementmere from fission where idelement="+e.getIdelement();
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next())
            {
                rep=res.getInt(1);
            }
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    //MODIFIED
    public int getIdElementMereFission(String idel,Connection con){
        int rep=-1;
        try {            
            String sql="select idelementmere from fission where idelement="+idel;
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next())
            {
                rep=res.getInt(1);
            }
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public void ajouterFission(int idElementMere,Element e){
        Connection con=db.getConn();
        try {
            int sequence=this.getNextsequence("fission_idfission_seq", con);
            
            java.sql.Statement stmt = con.createStatement();
            String sql="insert into fission values("+sequence+","+idElementMere+","+e.getIdelement()+")";
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
            con.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ArrayList<Element[]> listeElementMere(){
        Connection con=db.getConn();
        ArrayList<Element[]> rep=new ArrayList<Element[]>();
        try {            
            String sql="select * from elementmere ";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next())
            {
                rep.add(this.getElementMere(res.getInt(1)));
            }
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    //03/02/2017
    public String checkUtilisateur(String mail,String mdp,Connection con){
        String rep=null;
        try {            
            String sql="SELECT idutilisateur FROM utilisateurs WHERE mdp = (CRYPT('"+mdp+"', mdp)) and email='"+mail+"' or pseudo='"+mail+"'";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next())
            {
                rep=res.getString(1);
            }
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public String insertToken(String idutilisateur,Connection con){
        
        Calendar cal=Calendar.getInstance();
        String token = java.util.UUID.randomUUID().toString().toUpperCase() 
            + "|" + "userid" + "|"
            + cal.getTimeInMillis();
        try {       
            int sequence=this.getNextsequence("token_idtoken_seq", con);
            String sql="insert into token values("+sequence+","+idutilisateur+",'"+token+"',current_timestamp,current_timestamp + time '06:00:00');";
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }    
    public String authentification(String mail,String mdp) throws Exception{
        Connection con=db.getConn();
        String token=null;
        String idutilisateur=this.checkUtilisateur(mail, mdp, con);
        if(idutilisateur==null){
            token="Erreur de mot de passe ou d'email.";
        }
        else token=this.insertToken(idutilisateur, con);
        
        con.close();
        return token;
    }
    
    public String getIdUtilisateur_with_token(String token){
        Connection con=db.getConn();
        String rep=null;
        try {            
            String sql="SELECT idutilisateur FROM token \n" +
                        "where token='"+token+"'\n" +
                        "and current_timestamp between datedebut and datefin";
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            while (res.next())
            {
                rep=res.getString(1);
            }
            stmt.close();
            con.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    //2017-02-08
    public void insertScore(String idUser,String temps,String level){
        Connection con=db.getConn();
        try {
            String sql="insert into score(idutilisateur,temps,niveau) values ("+idUser+",'"+temps+"',"+level+")";
            java.sql.Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } 
        catch (Exception ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkPseudo(String pseudo)throws Exception{
        Connection con=db.getConn();
        try {
            String sql="select * from utilisateurs where pseudo = '"+pseudo+"'";
            
            java.sql.Statement stmt = con.createStatement();
            ResultSet res=stmt.executeQuery(sql);
            if(res.next()){
                throw new Exception("Le pseudo est déja utilisé.");
            }
        
            stmt.close();
            con.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public String getBestScore(){
        Connection con=db.getConn();
        String rep = null;
        try {            
            String sql="select * from best_score limit 10";
            java.sql.Statement stmt = con.createStatement();
            rep = JSONUtil.convert(stmt.executeQuery(sql)).toString();
            stmt.close();
            con.close();
        } 
        catch (Exception ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public String inscription(String nom,String pseudo,String email,String mdp,Connection con){
        int sequence=this.getNextsequence("utilisateurs_idutilisateur_seq", con);
        try {
            String sql="insert into utilisateurs values ("+sequence+",'"+nom+"','"+pseudo+"','"+email+"',crypt('"+mdp+"', gen_salt('bf')))";
            
            java.sql.Statement stmt = con.createStatement();
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return Integer.toString(sequence);
    }
    
    public String inscription_authentification(String nom,String pseudo,String email,String mdp) throws Exception{ // return token
        Connection con=db.getConn();
        
        String iduser=this.inscription(nom, pseudo, email, mdp,con);
        this.insertElementBase(iduser,con);
        this.insertNiveauUser(iduser,"1");
        
        con.close();
        
        return this.authentification(email, mdp);
    }
    
    public void insertElementBase(String iduser,Connection con) throws SQLException{
        for (int i = 1; i < 5; i++) {
            this.insertElementDebloque(Integer.toString(i), Integer.parseInt(iduser), con);
        }
    }
    
    public void insertNiveauUser(String idUser,String niveau){
        Connection con=db.getConn();
        try {
            String sql="insert into niveau (idutilisateur,niveau) values("+idUser+","+niveau+")";
            
            java.sql.Statement stmt = con.createStatement();
            int valeur=stmt.executeUpdate(sql);
            
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public void updateNiveauUser(String idUser){
        Connection con=db.getConn();
        try {
            String sql="update niveau set niveau=niveau+1 where idutilisateur="+idUser;
            
            java.sql.Statement stmt = con.createStatement();
            int valeur=stmt.executeUpdate(sql);
        
            stmt.close();
        
        } catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    public ResultSet getNombreElement(){
        Connection con=db.getConn();
        ResultSet rep = null;
        try {            
            String sql="select count(*) from element";
            java.sql.Statement stmt = con.createStatement();
            rep = stmt.executeQuery(sql);
            stmt.close();
            con.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public ResultSet getNombreElementDebloque(){
        Connection con=db.getConn();
        ResultSet rep = null;
        try {            
            String sql="select count(*) from elementdebloque";
            java.sql.Statement stmt = con.createStatement();
            rep = stmt.executeQuery(sql);
            stmt.close();
            con.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public ResultSet getElement(String idElement){
        //ilaina ref le iget an le idelement anaty session anapotran anaz eo @ defi mb tsy iovan ra oatr ka mi actualise ilay client
        //ary koa mb samy anana ny niveau any ny tsirairay avy
        Connection con=db.getConn();
        ResultSet rep = null;
        try {            
            String sql="select * from element where idelement="+idElement;
            java.sql.Statement stmt = con.createStatement();
            rep = stmt.executeQuery(sql);
            stmt.close();
            con.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public String getNiveauUser(String idUser,Connection con){
        String niveau=null;
        try {            
            String sql="select niveau from utilisateurs as u\n" +
                        "join niveau as n on n.idutilisateur=u.idutilisateur\n" +
                        "where u.idutilisateur="+idUser;
            java.sql.Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            
            while (res.next())
            {
               niveau=res.getString(1);
            }
            stmt.close();
            
            stmt.close();
        } 
        catch (SQLException ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return niveau;
    }
    
    public String getElementAleatoireJSON(String idUser){
        Connection con=db.getConn();
        String niveau=this.getNiveauUser(idUser, con);
        String rep = null;
        try {            
            String sql="SELECT e.idelement,nom,image\n" +
                        "FROM elementfille as ef\n" +
                        "join element as e on e.idelement=ef.idelement\n" +
                        "where niveau="+niveau+"\n" +
                        "ORDER BY random()\n" +
                        "LIMIT 1";
            java.sql.Statement stmt = con.createStatement();
            rep = JSONUtil.convert(stmt.executeQuery(sql),niveau).toString();
            stmt.close();
            con.close();
        } 
        catch (Exception ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    public void checkDefi(String token,String idElement1,String idElement2) throws Exception{
        Connection con=db.getConn();
        if(idElement1.equals(idElement2)){
            String idUser=this.getIdUtilisateur_with_token(token);
            this.updateNiveauUser(idUser);
            throw new Exception("Felicitation! vous passer au niveau superieur!");
        }
        con.close();
    }
    
    public void checkDefi(String token,String idElement,String[] idElementFille)throws Exception{
        for(int i=0;i<idElementFille.length;i++){
            this.checkDefi(token, idElement, idElement);
        }
    }
    
    
    
    
    public String getElementsBaseJSON(){
        Connection con=db.getConn();
        String rep = null;
        try {            
            String sql="select e.idelement,nom,image from element_base as eb\n" +
                        "join element as e on e.idelement=eb.idelement ";
            java.sql.Statement stmt = con.createStatement();
            rep = JSONUtil.convert(stmt.executeQuery(sql)).toString();
            stmt.close();
            con.close();
        } 
        catch (Exception ex) {
            Logger.getLogger(Fonction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rep;
    }
    
    
}
