package dao1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import db.DBConnection;
import vo.cafeListVo;

public class CafeListTableDao {
	
	public int insert(String cafeName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafelist values (?, grade_num, catnum, ?, usernum)"; 
			//카페이름 쓰는 란이 없어보임. 필요할듯
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, getMaxNum()+1);
			pstmt.setString(2, cafeName);
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
	
	public ArrayList<cafeListVo> list(int startRow, int endRow, String field, String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(field != null && !field.equals("")) {
			sql = "select * from( select rownum rnum, aa.* from(select * from cafelist where "+ field +" like '%" + keyword + "%' order by userNum desc)aa)where rnum>=? and rnum <=?";
		} else {
			sql = "select * from( select rownum rnum, aa.* from(select * from cafelist order by userNum desc)aa)where rnum>=? and rnum <=?";
		}
		
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			ArrayList<cafeListVo> list = new ArrayList<cafeListVo>();
			while (rs.next()) {
				int cafeNum = rs.getInt("cafeNum");
				int gradeNum = rs.getInt("gradeNum");
				int catNum = rs.getInt("catNum");
				String cafeName = rs.getString("cafeName");
				int userNum = rs.getInt("userNum");
				String cafeRegDate = rs.getDate("cafeRegDate").toString();
				cafeListVo vo = new cafeListVo(cafeNum, gradeNum, catNum, cafeName, userNum, cafeRegDate);
				list.add(vo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int getMaxNum() {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(max(cafeNum),0) from cafeList";
			rs = pstmt.executeQuery();
			rs.next();
			int maxNum = rs.getInt(1);
			return maxNum;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int getCount(String field, String keyword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select NVL(count(cafeNum),0) from cafeList";
			if(field != null && !field.equals("")) {
				sql = "select NVL(count(cafeNum),0) from cafeList where " + field + " like '%" + keyword + "%'";
			}
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
