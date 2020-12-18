package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import db.DBCPBean;

public class LoginDao {
	public Boolean isUser(HashMap<String, String> map) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from userinfo where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			String id=map.get("id").toUpperCase();
			pstmt.setString(1, id);
			System.out.println(id);
			pstmt.setString(2, map.get("pwd"));
			rs=pstmt.executeQuery();
			if(!rs.next()) {
				System.out.println(1);
				return false;
			} else {
				System.out.println(2);
				return true;
			}
		}catch(SQLException se) {
			se.printStackTrace();
			System.out.println(3);
			return false;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
