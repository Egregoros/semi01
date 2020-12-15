package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.cafeMemberVo;
import db.DBCPBean;

public class CafeMemberDao {
	public int insert(cafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafeMem values (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNum());
			pstmt.setInt(2, vo.getCafeNum());
			pstmt.setString(3, vo.getCafeMemNick());;
			pstmt.setInt(4, vo.getCafeMemGrade());// ??? 자동부여? 새싹부터?
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int delete(cafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafeMem where userNum = ?";
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
	
	public int updateNickName(cafeMemberVo vo, String newNick) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "update set cafeMemNick = ? where userNum = ?";
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
	
	public ArrayList<cafeMemberVo> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<cafeMemberVo> list = new ArrayList<cafeMemberVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafeMem";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int userNum = rs.getInt("userNum");
				int cafeNum = rs.getInt("cafeNum");
				String cafeMemNick = rs.getString("cafeMemNick");
				int cafeMemGrade = rs.getInt("cafeMemGrade");
				cafeMemberVo vo = new cafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGrade);
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
	
	public cafeMemberVo getOne(String userName) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		cafeMemberVo vo = null;
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
					vo = new cafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGrade);
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
}
