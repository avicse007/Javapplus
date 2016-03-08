package com.snapdeal.datavalidator.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static Connection connection =null;

	
	 public static void sqlConnection(String ForwardPort,String DBUsername,String DBPassword) throws Exception {
	        if (connection == null) {
	            synchronized (DBUtil.class) {

	                if (connection == null) {

	                    try {
	                        Class.forName("com.mysql.jdbc.Driver");
	                        connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" +ForwardPort,DBUsername,DBPassword);
	                        System.out.println("SQL DB connection created successfully !!!");
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }

	    }
	 public static void closeConnection() throws SQLException{
		    if(connection!=null&&connection.isClosed())
		    {
			connection.close();
			System.out.println("SQL DB connection closed");
		    }
		}
	 
	 public static void main(String avi[]) throws Exception{
		 //DBUsernameas7892IU=as7892IU,DBPassword=AnjS@522# ForwardPort=3011
		 //SSHCinnectionUtil.myStart("54.169.156.29", "as7892", "20.0.0.65", 3011, 3306);
		 //myStart(config.getConfig("SSHIP"), config.getConfig("SSHUsername"), config.getConfig("DBIP"), Integer.parseInt(config.getConfig("ForwardPort")),port);
		 System.out.println("Waiting");
		 Thread.sleep(6000);
		 sqlConnection("3011","as7892IU","AnjS@522#");
		 System.out.println("Connected");
		 //closeConnection();
	 }
	 
}


