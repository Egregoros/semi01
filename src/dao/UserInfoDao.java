package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBCPBean;
import vo.UserInfoVo;

public class UserInfoDao {
	public int insert(UserInfoVo vo) {
		System.out.println("vo :" + vo);
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into userinfo values (userinfo_seq.nextval,?,?,?,?,?,?,?,?,1)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getNickName());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getAddr());
			pstmt.setString(6, vo.getEmail());
			pstmt.setDate(7, vo.getBirth());
			pstmt.setString(8, vo.getPhone());
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int delete(UserInfoVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from userinfo where usernum = ?";
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
	
	public UserInfoVo getOne (int userNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoVo userVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from userinfo where usernum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String nickName = rs.getString("nickname");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				Date birth = rs.getDate("birth");
				String phone = rs.getString("phone");
				int isLive = rs.getInt("islive");
				userVo = new UserInfoVo(userNum, id, pwd, nickName, nickName, addr, email, birth, phone, isLive);
			}
			return userVo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public UserInfoVo getOne (String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserInfoVo userVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from userinfo where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int userNum = rs.getInt("userNum");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String nickName = rs.getString("nickname");
				String addr = rs.getString("addr");
				String email = rs.getString("email");
				Date birth = rs.getDate("birth");
				String phone = rs.getString("phone");
				int isLive = rs.getInt("islive");
				userVo = new UserInfoVo(userNum, id, pwd, nickName, nickName, addr, email, birth, phone, isLive);
			}
			return userVo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}