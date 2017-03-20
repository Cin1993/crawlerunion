package com.crawlerunion.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//Program Purpose: Connect to database Class
//Program input: N/A
//Program output: N/A
//Program data/time: 2015/7/1 13:29
//Create by: Jack
//Create time: 2015/7/1
//Program start
public class DBUtil {
    
    private final static String DRIVER_CLASS="com.mysql.jdbc.Driver";
    private final static String CONN_STR="jdbc:mysql://localhost:3306";
    private final static String DB_USER="root";
    private final static String DB_PWD="RobotGo!";

    static{
        try{
            Class.forName(DRIVER_CLASS);
        }catch(ClassNotFoundException e){
        	System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
    }
    
    // Program Purpose: Connect to database function
 	// Program input: N/A
 	// Program output: Connection
 	// Program data/time: 2015/7/1 13:32
 	// Create by: Jack
 	// Create time: 2015/7/1
 	// Program start
    public Connection getConn(String database){
        try {
        	String connstr = CONN_STR;
        	if(!database.equals(""))
        		connstr = connstr+"/"+database;
            return DriverManager.getConnection(connstr,DB_USER,DB_PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Program end
    
    // Program Purpose: Close connect to database function
  	// Program input: ResultSet,PreparedStatement,Connection
  	// Program output: N/A
  	// Program data/time: 2015/7/1 13:32
  	// Create by: Jack
  	// Create time: 2015/7/1
  	// Program start
    public static void closeConn(ResultSet rs,PreparedStatement pstmt,Connection conn){
        try {
            if (rs!=null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (pstmt!=null) {
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            if (conn!=null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

