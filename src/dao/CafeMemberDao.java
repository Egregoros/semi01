package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vo.CafeMemberVo;
import db.DBCPBean;

public class CafeMemberDao {
	private static CafeMemberDao instance=new CafeMemberDao();
	private CafeMemberDao() {}
	public static CafeMemberDao getInstance() {	
		return instance;
	}
	
	public int insert(CafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafemember values (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNum());
			pstmt.setInt(2, vo.getCafeNum());
			pstmt.setString(3, vo.getCafeMemNick());;
			pstmt.setInt(4, vo.getCafeMemGrade());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int delete(CafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafemember where userum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int updateNickName(CafeMemberVo vo, String newNick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update cafemember set nickname=? where usernum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newNick);
			pstmt.setInt(2, vo.getUserNum());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public ArrayList<CafeMemberVo> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CafeMemberVo> list = new ArrayList<CafeMemberVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemember";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int userNum = rs.getInt("userNum");
				int cafeNum = rs.getInt("cafeNum");
				String cafeMemNick = rs.getString("cafeMemNick");
				int cafeMemGrade = rs.getInt("cafeMemGrade");
				CafeMemberVo vo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGrade);
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
	
	public CafeMemberVo getUserName(String userName) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		CafeMemberVo vo = null;
		int userNum = 0;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select userNum from userInfo where userName = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setString(1, userName);
			rs1 = pstmt1.executeQuery();
			if (rs1.next()) {
				userNum = rs1.getInt("userNum");
				String sql2 = "select * from cafeMem where userNum = ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, userNum);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					int cafeNum = rs2.getInt("cafeNum");
					String cafeMemNick = rs2.getString("cafeMemNick");
					int cafeMemGrade = rs2.getInt("cafeMemGrade");
					vo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGrade);
				} else {
					//시스템오류
				}
			} else {
				System.out.println("유저를 찾을수 없습니다?");
				// 아니면 회원찾기? 
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(rs2);
			DBCPBean.close(rs1);
			DBCPBean.close(pstmt2);
			DBCPBean.close(pstmt1);
			DBCPBean.close(con);
		}
	}
	
	public Boolean getIsUser(int userNum, int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select * from cafemember where usernum=? and cafenum=? and cafememgradenum != 0";
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, cafeNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public HashMap<Integer, Integer> getCafeCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		try {
			con = DBCPBean.getConn();
			String sql1 = "select cafenum, count(cafenum) cnt from cafemember group by cafenum";
			pstmt = con.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt("cafeNum"), rs.getInt("cnt"));
			}
			return map;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
