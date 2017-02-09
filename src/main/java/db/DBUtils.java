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
            conn = DriverManager.getConnection("jdbc:postgresql://ec2-54-217-235-11.eu-west-1.compute.amazonaws.com/dchpnftkb77lh2","cybcadvbtjhpfy", "c455c9f775c65f2ecac659cf71d58c872f67e7437c2ecada488bac887f180efe");
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }
}
