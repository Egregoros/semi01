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
	
	
}
