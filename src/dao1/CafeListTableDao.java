package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBCPBean;

public class CafeListTableDao {
	
	public int insert() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafelist values (seq, grade_num, catnum, usernum)"; 
			//카페이름 쓰는 란이 없어보임. 필요할듯
			pstmt = con.prepareStatement(sql);
			int n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int delete(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafelist where cafeNum = ?";
			String sql1 = "delete from cafeMember where cafeNum = ?";
			String sql2 = "delete from cafeMemGrade where cafeNum = ?";
			String sql3 = "delete from cafeBoard where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt3 = con.prepareStatement(sql3);
			pstmt.setInt(1, cafeNum);
			pstmt1.setInt(1, cafeNum);
			pstmt2.setInt(1, cafeNum);
			pstmt3.setInt(1, cafeNum);
			
			pstmt3.executeUpdate();
			pstmt2.executeUpdate();
			pstmt1.executeUpdate();
			return pstmt.executeUpdate();
			
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(pstmt3);
			DBCPBean.close(pstmt2);
			DBCPBean.close(pstmt1);
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int update(int cafeNum) {
		//이름 바꾸거나 카테고리 바꿀수 있는 란.
		return 0;
	}
}
