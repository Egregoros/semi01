package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBCPBean;
import vo.CafeMainPicVo;

public class CafeMainPicDao {
	private static CafeMainPicDao instance = new CafeMainPicDao();
	private CafeMainPicDao() {}
	public static CafeMainPicDao getInstance() {
		return instance;
	}
	
	public int insert (CafeMainPicVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafemainpic values (fileinfo_seq, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCafeNum());
			pstmt.setString(2, vo.getOrgFileName());
			pstmt.setString(3, vo.getSaveFileName());
			pstmt.setLong(4, vo.getFileSize());
			
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int delete (int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafemainpic where cafenum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int update(CafeMainPicVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafemainpic set orgfilename = ?, savefilename = ?, filesize = ? where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getOrgFileName());
			pstmt.setString(2, vo.getSaveFileName());
			pstmt.setLong(3, vo.getFileSize());
			pstmt.setInt(4, vo.getCafeNum());
			
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
}
