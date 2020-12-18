package dao1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBCPBean;
import vo.UserInfoVo;

public class UserInfoDao {
	public int insert(UserInfoVo vo) {
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
}