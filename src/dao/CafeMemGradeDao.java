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
			String sql = "select max(cafememgradenum) max from cafememgrade where cafeNum = ?";
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
	
	public int update(int cafeNum, String cafeMemGradeName, int cafeMemGradeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafememgrade set cafeMemGradeName = ? where cafememgradenum = ? and cafenum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cafeMemGradeName);
			pstmt.setInt(1, cafeMemGradeNum);
			pstmt.setInt(3, cafeNum);
			n = pstmt.executeUpdate();
			return n;
		} catch(SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int deleteOne(int cafeNum, int cafeMemGradeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafememgrade where cafenum = ? and cafememgradenum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			pstmt.setInt(2, cafeMemGradeNum);
			n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int memGradeUp(int cafeNum, int cafeMemGradeNum, String cafeMemGradeName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafememgrade set cafememgradenum = ? where cafenum = ? cafememgradename = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeMemGradeNum-1);
			pstmt.setInt(2, cafeNum);
			pstmt.setString(3, cafeMemGradeName);
			n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int memGradeDown (int cafeNum, int cafeMemGradeNum, String cafeMemGradeName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafememgrade set cafememgradenum = ? where cafenum = ? cafememgradename = ?";
			pstmt.setInt(1, cafeMemGradeNum+1);
			pstmt.setInt(2, cafeNum);
			pstmt.setString(3, cafeMemGradeName);
			n = pstmt.executeUpdate();
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt , null);
		}
	}
}
