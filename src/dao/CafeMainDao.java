package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import db.DBCPBean;
import vo.CafeNavBoardVo;
import vo.CafeNavCatVo;
import vo.PostListVo;

public class CafeMainDao {

	private static CafeMainDao instance=new CafeMainDao();
	private CafeMainDao() {}
	public static CafeMainDao getInstance() {	
		return instance;
	}
	
	public HashMap<String, String> getCafeInfo(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		HashMap<String, String> map=new HashMap<String, String>();
		try {
			con = DBCPBean.getConn();
			String sql1 = "select c.*, to_char(caferegdate,'yyyy.mm.dd.') cr  from (select * from (cafelist natural join cafegrade natural join cafemember  natural join cafememgrade)) c where cafenum=?";
			String sql2 = "select count(*) count from cafemember where cafenum=? and cafememgradenum!=0";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, cafeNum);
			rs1 = pstmt1.executeQuery();
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, cafeNum);
			rs2 = pstmt2.executeQuery();
			if (rs1.next()&&rs2.next()) {
				map.put("cafeAdmin",rs1.getString("cafememnick"));
				map.put("cafeAdminGrade",rs1.getString("cafememgradename"));
				map.put("cafeName",rs1.getString("cafename"));
				map.put("cafeRegdate",rs1.getString("cr"));
				map.put("cafeGrade",rs1.getString("gradename"));
				map.put("cafeNum",rs1.getString("cafenum"));
				map.put("cafeUsers",rs2.getString("count"));
				return map;
			} else {
				return null;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public HashMap<String, String> getUserInfo(int userNum, int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		HashMap<String, String> map=new HashMap<String, String>();
		try {
			con = DBCPBean.getConn();
			String sql1 = "select * from cafemember where usernum=? and cafenum=? and cafememgradenum != 2";	//��ȸ�� ��ȣ 2��
			String sql2 = "select u.*, to_char(cafememregdate,'yyyy.mm.dd.') cr from (select * from (select * from cafemember natural join cafememgrade where cafenum=? and usernum=?)) u";
			String sql3 = "select count(*) count from post where boardnum in (select boardnum from cafeboard where boardcatnum in (select boardcatnum from cafeboardcat where cafenum=?)) and usernum=?";
			String sql4 = "select count(*) count from postcomment where postnum in (select postnum from post where boardnum in (select boardnum from cafeboard where boardcatnum in (select boardcatnum from cafeboardcat where cafenum=?))) and usernum = ?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, userNum);
			pstmt1.setInt(2, cafeNum);
			rs1 = pstmt1.executeQuery();
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, cafeNum);
			pstmt2.setInt(2, userNum);
			rs2 = pstmt2.executeQuery();
			pstmt3 = con.prepareStatement(sql3);
			pstmt3.setInt(1, cafeNum);
			pstmt3.setInt(2, userNum);
			rs3 = pstmt3.executeQuery();
			pstmt4 = con.prepareStatement(sql4);
			pstmt4.setInt(1, cafeNum);
			pstmt4.setInt(2, userNum);
			rs4 = pstmt4.executeQuery();
			if (rs1.next()&&rs2.next()&&rs3.next()&&rs4.next()) {
				map.put("isUser", "true");
				map.put("userNick",rs2.getString("cafememnick"));
				map.put("userRegdate",rs2.getString("cr"));
				map.put("userGrade",rs2.getString("cafememgradename"));
				map.put("userInvite",rs2.getString("cafeinvitecount"));
				map.put("userCountPost",rs3.getString("count"));
				map.put("userCountPostComment",rs4.getString("count"));
				return map;
			} else {
				map.put("isUser", "false");
				return map;
			}
		} catch (SQLException se) {
			se.printStackTrace();
			map.put("isUser", "false");
			return map;
		} finally {
			DBCPBean.close(null, pstmt4, rs4);
			DBCPBean.close(null, pstmt3, rs3);
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public ArrayList<CafeNavCatVo> getCafeNavList(int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select * from cafeboardcat where cafenum=? order by catorder";
			String sql2 = "select * from cafeboard where boardcatnum in (select boardcatnum from cafeboardcat where cafenum=?) order by ordernum";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, cafeNum);
			pstmt2 = con.prepareStatement(sql2, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt2.setInt(1, cafeNum);
			rs1 = pstmt1.executeQuery();
			rs2 = pstmt2.executeQuery();
			ArrayList<CafeNavCatVo> list = new ArrayList<CafeNavCatVo>();
			while (rs1.next()) {
				ArrayList<CafeNavBoardVo> board = new ArrayList<CafeNavBoardVo>();
				while(rs2.next()) {
					if(rs2.getInt("boardcatnum")==rs1.getInt("boardcatnum")) {
						board.add(new CafeNavBoardVo(rs2.getInt("boardnum"), rs2.getString("boardName")));
					}
				}
				rs2.absolute(1);
				list.add(new CafeNavCatVo(rs1.getInt("boardcatnum"), rs1.getString("catname"), board));
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public ArrayList<PostListVo> getCafePostList(int cafeNum, int boardNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql2 = "";
			if(boardNum==0) {
				sql2 = "select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=1 order by postnum desc) c";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, cafeNum);
				rs2 = pstmt2.executeQuery();
				ArrayList<PostListVo> list = new ArrayList<PostListVo>();
				while (rs2.next()) {
					list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
				}
				return list;
			}else {
				String sql1="select * from cafeboard where boardnum=?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, boardNum);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("cafeboardnum")==0) {
						sql2 = "select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=1 order by postnum desc) c";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, cafeNum);
					}else {
						sql2 = "select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from post natural join cafemember where boardnum=? and postcatnum=1 order by postnum desc) c";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, boardNum);
					}
					rs2 = pstmt2.executeQuery();
					ArrayList<PostListVo> list = new ArrayList<PostListVo>();
					while (rs2.next()) {
						list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
					}
					return list;
				}else {
					return null;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public int getCafePostCount(int cafeNum, int boardNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql2 = "";
			if(boardNum==0) {
				sql2 = "select count(*) count from cafeboardcat natural join cafeboard natural join post where cafenum=? and postcatnum =1";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, cafeNum);
				rs2 = pstmt2.executeQuery();
				if (rs2.next()) {
					return rs2.getInt("count");
				}else {
					return 0;
				}
			}else {
				String sql1="select * from cafeboard where boardnum = ?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, boardNum);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("cafeboardnum")==0) {
						sql2 = "select count(*) count from cafeboardcat natural join cafeboard natural join post where cafenum=? and postcatnum =1";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, cafeNum);
					}else {
						sql2 = "select count(*) count from post where boardnum=? and postcatnum=1";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, boardNum);
					}
					rs2 = pstmt2.executeQuery();
					if (rs2.next()) {
						return rs2.getInt("count");
					}else {
						return 0;
					}
				}else {
					return 0;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return 0;
		} finally {
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public ArrayList<PostListVo> getCafeNoticeList(int cafeNum, int boardNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql2 = "";
			if(boardNum==0) {
				sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=0 order by postnum desc) c) where rownum<=10";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, cafeNum);
				rs2 = pstmt2.executeQuery();
				ArrayList<PostListVo> list = new ArrayList<PostListVo>();
				while (rs2.next()) {
					list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
				}
				return list;
			}else {
				String sql1="select * from cafeboard where boardnum=?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, boardNum);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("cafeboardnum")==0) {
						sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=0 order by postnum desc) c) where rownum<=10";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, cafeNum);
					}else {
						sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd from (select * from post natural join cafemember where boardnum=? and postcatnum=0 order by postnum desc) c) where rownum<=10";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, boardNum);
					}
					rs2 = pstmt2.executeQuery();
					ArrayList<PostListVo> list = new ArrayList<PostListVo>();
					while (rs2.next()) {
						list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
					}
					return list;
				}else {
					return null;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
}