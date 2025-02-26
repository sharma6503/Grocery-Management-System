package com.grocery.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.grocery.constants.*;

public class DBConnection {
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        
    	Class.forName("org.sqlite.JDBC");

        Connection con = DriverManager.getConnection(GenericConstants.DB_URL,"","");
        return con;
    }
    
    public static void closeConnection(Connection con) {
        
        try {
            
            if(con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}