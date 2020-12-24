package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import db.DBCPBean;
import vo.MessageVo;

public class MessageDao {

	private static MessageDao instance=new MessageDao();
	private MessageDao() {}
	public static MessageDao getInstance() {	
		return instance;
	}
	
	public HashMap<Integer, String> getUserList(int userNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="select * from (select distinct u.* from message m join userinfo u on m.sendusernum=u.usernum where recusernum=?) union (select distinct u.* from message m join userinfo u on m.recusernum=u.usernum where sendusernum=?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, userNum);
			rs=pstmt.executeQuery();
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			while(rs.next()) {
				map.put(rs.getInt("usernum"),rs.getString("nickname"));
			}
			return map;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public HashMap<Integer, Integer> getNewMessage(int userNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="select recusernum, count(recusernum) count from message where checkread=0 and sendusernum=? group by recusernum";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs=pstmt.executeQuery();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			while(rs.next()) {
				map.put(rs.getInt("recusernum"),rs.getInt("count"));
			}
			return map;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<MessageVo> getMessage(int messageUserNum, int userNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="select * from message where (recusernum=? and sendusernum=?) or (recusernum=? and sendusernum=?) order by sendtime";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, messageUserNum);
			pstmt.setInt(3, messageUserNum);
			pstmt.setInt(4, userNum);
			rs=pstmt.executeQuery();
			ArrayList<MessageVo> list = new ArrayList<MessageVo>();
			while(rs.next()) {
				list.add(new MessageVo(rs.getInt("messnum"), rs.getInt("recusernum"), rs.getInt("sendusernum"), rs.getString("messtitle"), rs.getString("messContent").replaceAll("\n", "<br>"), rs.getDate("sendtime"), rs.getInt("checkread")));
			}
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int insertMessage(int userNum, int sendUserNum, String title, String content) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="insert into message values((select nvl(max(messnum),0)+1 from message),?,?,?,?,sysdate,0)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, sendUserNum);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			int i = pstmt.executeUpdate();
			return i;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int readMessage(int userNum, int recUserNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="update message set checkread=1 where sendusernum=? and recusernum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, recUserNum);
			int i = pstmt.executeUpdate();
			return i;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int deleteMessage(int userNum, int recUserNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="delete message where (recusernum=? and sendusernum=?) or (recusernum=? and sendusernum=?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, recUserNum);
			pstmt.setInt(3, recUserNum);
			pstmt.setInt(4, userNum);
			int i = pstmt.executeUpdate();
			return i;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int getUserNum(String nickName) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=DBCPBean.getConn();
			String sql="select usernum from userinfo where nickname=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
