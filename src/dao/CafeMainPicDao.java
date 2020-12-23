package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
			String sql = "insert into cafemainpic values (?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, getMaxNum()+1);
			pstmt.setInt(2, vo.getCafeNum());
			pstmt.setString(3, vo.getOrgFileName());
			pstmt.setString(4, vo.getSaveFileName());
			pstmt.setLong(5, vo.getFileSize());
			
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
	
	public CafeMainPicVo getOne(String saveFileName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMainPicVo vo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemainpic where savefilename = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, saveFileName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cafePicNum = rs.getInt("cafepicnum");
				int cafeNum = rs.getInt("cafeNum");
				String orgFileName = rs.getString("orgFileName");
				long fileSize = rs.getLong("fileSize");
				vo = new CafeMainPicVo(cafePicNum, cafeNum, orgFileName, saveFileName, fileSize);
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public CafeMainPicVo getOne(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMainPicVo vo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemainpic where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cafePicNum = rs.getInt("cafepicnum");
				String saveFileName = rs.getString("saveFileName");
				String orgFileName = rs.getString("orgFileName");
				long fileSize = rs.getLong("fileSize");
				vo = new CafeMainPicVo(cafePicNum, cafeNum, orgFileName, saveFileName, fileSize);
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int delete (String saveFileName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "delete from cafemainpic where savefilename = ?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, saveFileName);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(max(cafepicnum),0) from cafemainpic";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
}
