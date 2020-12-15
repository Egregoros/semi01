package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static Connection getConn() {
		Connection con = null;
		try{
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(url, "scott", "tiger");
			return con;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
	}
	
	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static void close (Connection con) {
		try {
			if(con != null) con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	
	public static void close (Statement stmt) {
		try {
			if(stmt!= null) stmt.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	public static void close (ResultSet rs) {
		try {
			if(rs!= null) rs.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
