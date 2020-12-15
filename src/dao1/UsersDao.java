package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import db.DBCPBean;
import db.DBConnection;

public class UsersDao {
	private static UsersDao instance=new UsersDao();
	private UsersDao() {}	
	public static UsersDao getInstance() {
		return instance;
	}
	public int cafeMember(HashMap<String,String> map) {
		String id=map.get("id");
		String pwd=map.get("pwd");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from userinfo where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return 1;
			}else {
				return 0;
			}			
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBConnection.close(con, pstmt, rs);
		}
	}
}