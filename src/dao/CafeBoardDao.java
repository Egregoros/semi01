package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import vo.CafeBoardVo;

public class CafeBoardDao {
	private static CafeBoardDao instance = new CafeBoardDao();
	private CafeBoardDao () {}
	public static CafeBoardDao getInstance() {
		return instance;
	}
	
	public int delete (int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafeboard where boardNum = ?";
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
	
	public int deleteCat (int boardCatNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafeboard where boardcatnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardCatNum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public CafeBoardVo getBoard(int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeBoardVo cafeBoardVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafeboard where boardNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cafeBoardNum = rs.getInt("cafeBoardNum");
				int boardCatNum = rs.getInt("boardCatNum");
				String boardName = rs.getString("boardName");
				int useGrade = rs.getInt("useGrade");
				int orderNum = rs.getInt("orderNum");
				cafeBoardVo = new CafeBoardVo(boardNum, cafeBoardNum, boardCatNum, boardName, useGrade, orderNum);
			}
			return cafeBoardVo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<CafeBoardVo> getList(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeBoardVo cafeBoardVo = null;
		ArrayList<CafeBoardVo> list = new ArrayList<CafeBoardVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select cbc.cafenum, cb.* from cafeboard cb, cafeboardcat cbc where cbc.boardcatnum=cb.boardcatnum and cafenum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int boardNum = rs.getInt("boardNum");
				int cafeBoardNum = rs.getInt("cafeBoardNum");
				int boardCatNum = rs.getInt("boardCatNum");
				String boardName = rs.getString("boardName");
				int useGrade = rs.getInt("useGrade");
				int orderNum = rs.getInt("orderNum");
				cafeBoardVo = new CafeBoardVo(cafeBoardNum, cafeBoardNum, boardCatNum, boardName, useGrade, orderNum);
				list.add(cafeBoardVo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public String getBoardName(int boardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String boardName = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafeboard where boardnum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cafeBoardNum = rs.getInt("cafeBoardNum");
				int boardCatNum = rs.getInt("boardCatNum");
				boardName = rs.getString("boardName");
				int useGrade = rs.getInt("useGrade");
				int orderNum = rs.getInt("orderNum");
			}
			return boardName;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int updateBoard(int boardNum, String boardName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafeboard set boardName = ? where boardNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardName);
			pstmt.setInt(2, boardNum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
}
