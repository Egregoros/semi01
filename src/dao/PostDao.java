package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBCPBean;

public class PostDao {
	private static PostDao instance = new PostDao();
	private PostDao() {}
	public static PostDao getInstance() {
		return instance;
	}
	
	public int delete(int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = DBCPBean.getConn();
			String sql = "delete from post where boardNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
}
