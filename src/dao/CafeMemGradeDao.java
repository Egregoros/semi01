package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import vo.CafeMemGradeVo;

public class CafeMemGradeDao {
	private static CafeMemGradeDao instance = new CafeMemGradeDao();
	private CafeMemGradeDao() {}
	public static CafeMemGradeDao getInstance()	{
		return instance;
	}
	
	public int insert(CafeMemGradeVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafememgrade values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCafeNum());
			pstmt.setInt(2, vo.getCafeMemGradeNum());
			pstmt.setString(3, vo.getCafeMemGradeName());
			
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public ArrayList<CafeMemGradeVo> getList(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMemGradeVo cafeMemGradeVo = null;
		ArrayList<CafeMemGradeVo> cafeMemGradeList = new ArrayList<CafeMemGradeVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafememgrade where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int cafeMemGradeNum = rs.getInt("cafeMemGradeNum");
				String cafeMemGradeName = rs.getString("cafeMemGradeName");
				cafeMemGradeVo = new CafeMemGradeVo(cafeNum, cafeMemGradeNum, cafeMemGradeName);
				cafeMemGradeList.add(cafeMemGradeVo);
			}
			return cafeMemGradeList;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int delete(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafememgrade where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			int n = pstmt.executeUpdate();
			
			return n;
		} catch(SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int getMaxNum (int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int max = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "select max(cafememgradenum) max where cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				max = rs.getInt("max");
			}
			return max;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally{
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
}
