package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import vo.CafeMemGradeVo;
import vo.CafeMemberGradeNameVo;
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
			String sql = "insert into cafemember values (?, ?, ?, ?, ?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNum());
			pstmt.setInt(2, vo.getCafeNum());
			pstmt.setString(3, vo.getCafeMemNick());
			pstmt.setInt(4, vo.getCafeMemGradeNum());
			pstmt.setInt(5, 1);
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
				int cafeMemGradeNum = rs.getInt("cafeMemGradeNum");
				int cafeInviteCount = rs.getInt("cafeInviteCount");
				CafeMemberVo vo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, null);
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
					int cafeMemGradeNum = rs2.getInt("cafeGradeNum");
					int cafeInviteCount = rs2.getInt("cafeInviteCount");
					vo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, null);
				} else {
					return null;
				}
			} else {
				return null;
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
	
	public CafeMemberVo getcafeMemGradeNum(int userNum, int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMemberVo cafeMemVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemember where userNum = ? and cafeNum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, cafeNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String cafeMemNick = rs.getString("cafeMemNick");
				int cafeMemGradeNum = rs.getInt("cafeMemGradeNum");
				int cafeInviteCount = rs.getInt("cafeInviteCount");
				Date cafeMemRegDate = rs.getDate("cafeMemRegDate");
				
				cafeMemVo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, cafeMemRegDate);
			}
			return cafeMemVo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public ArrayList<CafeMemberGradeNameVo> getListGradeName(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMemberGradeNameVo cafeMemberGradeNameVo = null;
		ArrayList<CafeMemberGradeNameVo> list = new ArrayList<CafeMemberGradeNameVo>();
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemember natural join cafememgrade where cafenum=? order by cafememgradenum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int userNum = rs.getInt("userNum");
				String cafeMemNick = rs.getString("cafeMemNick");
				int cafeMemGradeNum = rs.getInt("cafeMemGradeNum");
				int cafeInviteCount = rs.getInt("cafeInviteCount");
				String cafeMemGradeName = rs.getString("cafeMemGradeName");
				String cafeMemRegDate = rs.getDate("cafeMemRegDate").toString();
				cafeMemberGradeNameVo = new CafeMemberGradeNameVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, cafeMemGradeName, cafeMemRegDate);
				list.add(cafeMemberGradeNameVo);
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int getMaxGradeNum (int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cafeMemGradeNum = 0;
		try {
			con= DBCPBean.getConn();
			String sql = "select max(cafememgradenum) max from cafemember natural join cafememgrade where cafenum=? order by cafememgradenum";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cafeMemGradeNum = rs.getInt("max");
			}
			return cafeMemGradeNum;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int oneGradeUp (CafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		try {
			con = DBCPBean.getConn();
			if(vo.getCafeMemGradeNum() > 0) {
				String sql = "update cafemember set cafememgradenum=? where usernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getCafeMemGradeNum()+1);
				pstmt.setInt(2, vo.getUserNum());
				n = pstmt.executeUpdate();
			} else if(vo.getCafeMemGradeNum() == 0) {
				String sql = "update cafemember set cafememgradenum = ? where usernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, 0);
				pstmt.setInt(2, vo.getUserNum());
				n = pstmt.executeUpdate();
			} 
			return n;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int oneGradeDown (CafeMemberVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int n = 0;
		CafeMemGradeDao cafeMemGradeDao = CafeMemGradeDao.getInstance();
		try {
			con = DBCPBean.getConn();
			if(vo.getCafeMemGradeNum() <= cafeMemGradeDao.getMaxNum(vo.getCafeNum())) {
				String sql = "update cafemember set cafememgradenum = ? where usernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, vo.getCafeMemGradeNum()-1);
				pstmt.setInt(2, vo.getUserNum());
				n = pstmt.executeUpdate();
			} else if (vo.getCafeMemGradeNum() == cafeMemGradeDao.getMaxNum(vo.getCafeNum())) {
				String sql = "update cafemember set cafememgradenum = ? where usernum = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cafeMemGradeDao.getMaxNum(vo.getCafeNum()));
				pstmt.setInt(2, vo.getUserNum());
				n = pstmt.executeUpdate();
			}
			return n;
		} catch(SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public int update(CafeMemberVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DBCPBean.getConn();
			String sql="update cafemember set cafeMemNick=? where userNum=? and cafeNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getCafeMemNick());
			pstmt.setInt(2, vo.getUserNum());
			pstmt.setInt(3, vo.getCafeNum());
			return pstmt.executeUpdate();
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public CafeMemberVo getOne(int userNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CafeMemberVo cafeMemberVo = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from cafemember where usernum = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int cafeNum = rs.getInt("cafeNum");
				String cafeMemNick = rs.getString("cafeMemNick");
				int cafeMemGradeNum = rs.getInt("cafeMemGradeNum");
				int cafeInviteCount = rs.getInt("cafeInviteCount");
				
				cafeMemberVo = new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, null);
			}
			return cafeMemberVo;
		} catch(SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int deleteCafeNumUserNum(int userNum, int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "delete from cafemember where userNum=? and cafeNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, cafeNum);
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
	public CafeMemberVo getNum(int userNum, int cafeNum) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=DBCPBean.getConn();
			String sql="select * from cafemember where userNum=? and cafeNum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setInt(2, cafeNum);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String cafeMemNick=rs.getString("cafeMemNick");
				int cafeMemGradeNum=rs.getInt("cafeMemGradeNum");
				int cafeInviteCount=rs.getInt("cafeInviteCount");
				Date cafeMemRegdate=rs.getDate("cafeMemRegdate");
				CafeMemberVo vo=new CafeMemberVo(userNum, cafeNum, cafeMemNick, cafeMemGradeNum, cafeInviteCount, cafeMemRegdate);
				return vo;
			}return null;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
}