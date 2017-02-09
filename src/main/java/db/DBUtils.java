/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Hobiana
 */
public class DBUtils {
    public Connection getConn()
    {
        Connection conn=null;
        try 
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/element_master","postgres", "itu");
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
