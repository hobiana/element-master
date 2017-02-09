/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires;

import fonction.Fonction;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author ITU
 */
public class Format {
    public static void header(JspWriter out, String title) throws IOException
    {
        out.write("\n");
        out.write("\n");
        out.write("\n");
        out.write("<!DOCTYPE html>\n");
        out.write("<html>\n");
        out.write("    <head>\n");
        out.write("        <link rel=\"stylesheet\"  href=\"/MasterElements/css/bootstrap.min.css\"/>\n");
        out.write("        <link rel=\"stylesheet\"  href=\"/MasterElements/css/styles.css\"/>\n");
        //out.write("        <link rel=\"stylesheet\"  href=\"/MasterElements/css/iso-ang-css/style.css\"/>\n");
        //out.write("        <link rel=\"stylesheet\"  href=\"/MasterElements/css/iso-ang-css/main.css\"/>\n");
        out.write("        <link rel=\"stylesheet\"  href=\"/MasterElements/css/font-awesome.min.css\"/>\n");
        out.write("        \n");
        out.write("        \n");
        out.write("\n");
        out.write("        <title>"+title+"</title>\n");
        out.write("        <meta charset=\"utf-8\" />\n");
        out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        out.write("        <script  src=\"/MasterElements/js/jquery-3.1.0.min.js\"></script>\n");
        out.write("        <script src=\"/MasterElements/js/tether.min.js\"></script>\n");
        out.write("        <script src=\"/MasterElements/js/angular.min.js\"></script>\n");
        out.write("        <script src=\"/MasterElements/js/bootstrap.min.js\"></script>\n");
        //out.write("        <script src=\"/MasterElements/js/myjavascript.js\"></script>\n");
        //out.write("        <script src=\"/MasterElements/js/isotope.pkgd.min.js\"></script>\n");
        
        out.write("    </head>\n");
        //out.write("    <body>\n");
        out.write("\n");
    }
   
    
    
    public static void templateNav(JspWriter out,HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        out.write("<nav  class=\"navbar navbar-fixed-top navbar-light bg-faded\">\n" +
            "    <button class=\"navbar-toggler hidden-sm-up pull-left\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapsingNavbar\">\n" +
            "        <i class=\"fa fa-bars\" aria-hidden=\"true\"></i>\n" +
            "    </button>\n" +
            "    <a class=\"navbar-brand\" href=\"#\">Element's Master</a>\n" +
            "    <div class=\"collapse navbar-toggleable-xs\" id=\"collapsingNavbar\">\n" +
            "        <ul class=\"nav navbar-nav pull-left\">\n" +
            "            <li class=\"nav-item\">\n" +
            "                <a class=\"nav-link\" href=\"#features\">Accueil</a>\n" +
            "            </li>\n" +
            "            <li class=\"nav-item active\">\n" +
            "               \n" +
            "                <div class=\"nav-item dropdown\">\n" +
            "                    <a class=\"nav-link dropdown-toggle\" href=\"#\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n" +
            "                    Jouer\n" +
            "                    </a>\n" +
            "                    <div class=\"dropdown-menu dropdown-menu-left\">\n" +
            "                      <a class=\"dropdown-item\" href=\"defis.jsp\">Défis</a>\n" +
            "                      <a class=\"dropdown-item\" href=\"libre.jsp\">Libre</a>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "            </li>\n" +
            "            \n" +
            "            \n" +
            "            <li class=\"nav-item\">\n" +
            "                <a class=\"nav-link\" href=\"#myAlert\" data-toggle=\"collapse\">Score</a>\n" +
            "            </li>\n" +
            "            <li class=\"nav-item\">\n" +
            "                <a class=\"nav-link\" href=\"\" data-target=\"#myModal\" data-toggle=\"modal\">Nous contacter</a>\n" +
            "            </li>\n" +
            "        </ul>\n" );
           navConnexion(out, request, response);
            out.write("    </div>\n" +
            "</nav>");
    }
   
    public static void footer(JspWriter out) throws IOException
    {
        out.write("\n");
        out.write("    <!-- Bootstrap core JavaScript\n");
        //out.write("    <script src=\"js/bootstrap.min.js\"></script>\n");
        out.write("    ================================================== -->\n");
        out.write("    <!-- Placed at the end of the document so the pages load faster -->\n");

        out.write("  \n");
        out.write("\n");
        out.write("<section style=\"text-align:center; margin:10px auto;\"><p>© 2016 - Tous droits réservés - <a href=\"#\">HN Group</a></p></section>");
        out.write("</body>\n");
        out.write("</html>\n");
    }
    
    
    //Form Connexion/Deconnexion
    public static void navConnexion(JspWriter out,HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        if(request.getSession().getAttribute("utilisateur")==null)
        {
            out.write("        <form class=\"form-inline pull-right\" method=\"post\" action=\"#\" >\n" +
            "  <input type=\"text\" name=\"username\" class=\"form-control form-control-sm mb-2 mr-sm-2 mb-sm-0\" id=\"inlineInputPseudo\" placeholder=\"Pseudo\">\n" +
            "\n" +
            "    <input type=\"password\" name=\"password\" class=\"form-control form-control-sm mb-2 mr-sm-2 mb-sm-0\" id=\"inlineFormInputPassword\" placeholder=\"Mot de passe\">\n" +

            "\n" +
            "        <button class=\"form-control btn btn-primary btn-sm\" type=\"submit\" >Se connecter</button>\n" +
            "        </form>\n");
        }
        else
        {
            out.write("        <form class=\"form-inline pull-right\" method=\"get\" action=\"#\" >\n" +
                    "<input  name=\"request\" value=\"deconnexion\" hidden>"+
                    "        <button class=\"form-control btn btn-primary btn-sm\" type=\"submit\" >Déconnexion</button>\n"
            +"        </form>\n");
        }
    }        
    
    public static void checkSession(HttpServletRequest request, HttpServletResponse response)
    {
        if(request.getSession().getAttribute("utilisateur")==null){
            try {
                response.sendRedirect("index.jsp");
            } catch (IOException ex) {
                Logger.getLogger(Format.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        checkConnexion(request, response);
    }
    
    public static void checkConnexion(HttpServletRequest request, HttpServletResponse response)
    {
        if(request.getParameter("username")!=null&&request.getParameter("password")!=null){
            if(!"".equals(request.getParameter("username"))&&!"".equals(request.getParameter("password"))){
                Fonction f= new Fonction();
                try
                {
                    String token = f.authentification(request.getParameter("username"),request.getParameter("password"));
                    request.getSession().setAttribute("utilisateur", token);
                    response.sendRedirect("libre.jsp");
                }
                catch(Exception e)
                {
                    try {
                        response.sendRedirect("index.jsp");
                    } catch (IOException ex) {
                        Logger.getLogger(Format.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
                
        }
        else if(request.getParameter("request")!=null)
        {
            try {
                request.getSession().removeAttribute("utilisateur");
                response.sendRedirect("index.jsp");
                
            } catch (IOException ex) {
                Logger.getLogger(Format.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
