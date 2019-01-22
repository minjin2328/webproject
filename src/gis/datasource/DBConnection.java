package gis.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getInstance() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		Connection conn;
		
		//URL, ID, PASSWORD 통하여 postgresql에 연결
		String DB_URL = "jdbc:postgresql://localhost:5432/DB_TRIP1";
		String DB_ID = "postgres";
		String DB_PASSWORD = "postgis";
		conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PASSWORD);		 
		return conn;
	}
}
