package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import vo.CafeBoardCatVo;

public class CafeBoardCatDao {
	private static CafeBoardCatDao instance = new CafeBoardCatDao();
	private CafeBoardCatDao() {}
	public static CafeBoardCatDao getInstance() {
		return instance;
	}
	
	public int delete(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafeboardcat where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public CafeBoardCatVo getOne(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeBoardCatVo cafeBoardCatVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafeboardcat where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int boardCatNum = rs.getInt("boardCatNum");
				String catName = rs.getString("catName");
				int catOrder = rs.getInt("catorder");
				cafeBoardCatVo = new CafeBoardCatVo(boardCatNum, cafeNum, catName, catOrder);
			}
			return cafeBoardCatVo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<CafeBoardCatVo> getCafeCat(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeBoardCatVo cafeBoardCatVo = null;
		ArrayList<CafeBoardCatVo> list = new ArrayList<CafeBoardCatVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafeboardcat where cafeNum = ? order by catorder";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int boardCatNum = rs.getInt("boardCatNum");
				String catName = rs.getString("catName");
				int catOrder = rs.getInt("catorder");
				cafeBoardCatVo = new CafeBoardCatVo(boardCatNum, cafeNum, catName, catOrder);
				list.add(cafeBoardCatVo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
