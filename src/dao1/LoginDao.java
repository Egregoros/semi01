package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import db.DBCPBean;

public class LoginDao {
	public Boolean isUser(HashMap<String, String>map	) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from userinfo where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			String id=map.get("id");
			pstmt.setString(1, id);
			pstmt.setString(2, map.get("pwd"));
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
