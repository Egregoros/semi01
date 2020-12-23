package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import db.DBCPBean;
import vo.CafeBoardInfoVo;
import vo.CafeNavBoardVo;
import vo.CafeNavCatVo;
import vo.PostCommentVo;
import vo.PostListVo;
import vo.PostVo;

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
			String sql2 = "select count(*) count from cafemember where cafenum=? and cafememgradenum!=(select cafememgradenum from cafememgrade where cafememgradename='비회원' and cafenum=?)";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, cafeNum);
			rs1 = pstmt1.executeQuery();
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, cafeNum);
			pstmt2.setInt(2, cafeNum);
			rs2 = pstmt2.executeQuery();
			if (rs1.next()&&rs2.next()) {
				map.put("cafeAdmin",rs1.getString("cafememnick"));
				map.put("cafeAdminNum",rs1.getString("usernum"));
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
			String sql1 = "select * from cafemember where usernum=? and cafenum=? and cafememgradenum != 2";	//비회원 번호 2번
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
				map.put("userGradeNum",rs2.getString("cafememgradenum"));
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
						board.add(new CafeNavBoardVo(rs2.getInt("boardnum"),rs2.getInt("cafeboardnum"), rs2.getString("boardname")));
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
	
	public ArrayList<PostListVo> getCafePostList(int cafeNum, int boardNum, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql2 = "";
			if(boardNum==0) {
				sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd, rownum rnum from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=1 order by postnum desc) c) where rnum>=? and rnum <= ?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, cafeNum);
				pstmt2.setInt(2, startRow);
				pstmt2.setInt(3, endRow);
				rs2 = pstmt2.executeQuery();
				ArrayList<PostListVo> list = new ArrayList<PostListVo>();
				while (rs2.next()) {
					list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getInt("userNum"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
				}
				return list;
			}else {
				String sql1="select * from cafeboard where boardnum=?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, boardNum);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("cafeboardnum")==0) {
						sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd, rownum rnum from (select * from cafeboardcat natural join cafeboard natural join post natural join cafemember where cafenum=? and postcatnum=1 order by postnum desc) c) where rnum>=? and rnum <=?";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, cafeNum);
						pstmt2.setInt(2, startRow);
						pstmt2.setInt(3, endRow);
					}else {
						sql2 = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd, rownum rnum from (select * from post natural join cafemember natural join cafeboard natural join cafeboardcat where boardnum=? and postcatnum=1 and cafenum=? order by postnum desc) c) where rnum>=? and rnum<=?";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, boardNum);
						pstmt2.setInt(2, cafeNum);
						pstmt2.setInt(3, startRow);
						pstmt2.setInt(4, endRow);
					}
					rs2 = pstmt2.executeQuery();
					ArrayList<PostListVo> list = new ArrayList<PostListVo>();
					while (rs2.next()) {
						list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getInt("userNum"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
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
	
	public CafeBoardInfoVo getCafeBoardInfo(int cafeNum, int boardNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql2 = "";
			if(boardNum==0) {
				String sql1="select * from cafeboard where cafeboardnum=0";
				pstmt1=con.prepareStatement(sql1);
				rs1=pstmt1.executeQuery();
				sql2 = "select count(*) count from cafeboardcat natural join cafeboard natural join post where cafenum=? and postcatnum=1";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, cafeNum);
				rs2 = pstmt2.executeQuery();
				if (rs1.next()&&rs2.next()) {
					return new CafeBoardInfoVo(boardNum, rs1.getInt("cafeboardnum"), rs1.getInt("boardcatnum"), rs1.getString("boardname"), rs1.getInt("useGrade"), rs1.getInt("ordernum"), rs2.getInt("count"));
				}else {
					return null;
				}
			}else {
				String sql1="select * from cafeboard where boardnum=?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, boardNum);
				rs1=pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("cafeboardnum")==0) {
						sql2 = "select count(*) count from cafeboardcat natural join cafeboard natural join post where cafenum=? and postcatnum=1";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, cafeNum);
					}else {
						sql2 = "select count(*) count from post where boardnum=? and postcatnum=1";
						pstmt2 = con.prepareStatement(sql2);
						pstmt2.setInt(1, boardNum);
					}
					rs2 = pstmt2.executeQuery();
					if (rs2.next()) {
						return new CafeBoardInfoVo(boardNum, rs1.getInt("cafeboardnum"), rs1.getInt("boardcatnum"), rs1.getString("boardname"), rs1.getInt("useGrade"), rs1.getInt("ordernum"), rs2.getInt("count"));
					}else {
						return null;
					}
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
					list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getInt("userNum"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
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
						list.add(new PostListVo(rs2.getInt("postnum"), rs2.getInt("cafepostnum"), rs2.getString("posttitle"), rs2.getString("cafememnick"), rs2.getInt("userNum"), rs2.getString("pd"), rs2.getInt("postinvitecount")));
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
	
	public ArrayList<PostListVo> getCafeSearchList(int cafeNum, int startRow, int endRow, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql = "select * from (select c.*, to_char(postdate,'yyyy.mm.dd') pd, rownum rnum from (select * from post natural join cafemember where cafenum=? and (posttitle like ? or postcontent like ? or cafememnick like ?) order by postnum desc) c) where rnum>=? and rnum<=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			pstmt.setInt(5, startRow);
			pstmt.setInt(6, endRow);
			
			rs = pstmt.executeQuery();
			ArrayList<PostListVo> list = new ArrayList<PostListVo>();
			while (rs.next()) {
				list.add(new PostListVo(rs.getInt("postnum"), rs.getInt("cafepostnum"), rs.getString("posttitle"), rs.getString("cafememnick"), rs.getInt("userNum"), rs.getString("pd"), rs.getInt("postinvitecount")));
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public CafeBoardInfoVo getCafeSearchInfo(int cafeNum, String search) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			
			String sql = "select count(*) count from post natural join cafemember where cafenum=? and (posttitle like ? or postcontent like ? or cafememnick like ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			pstmt.setString(2, "%"+search+"%");
			pstmt.setString(3, "%"+search+"%");
			pstmt.setString(4, "%"+search+"%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new CafeBoardInfoVo(-1, -1, -1, "'"+search+"' 의 검색결과", -1, -1, rs.getInt("count"));
			}else {
				return null;
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public int invitePlus(int cafeNum, int userNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			
			String sql = "update cafemember set cafeinvitecount = (select cafeinvitecount+1 from cafemember where cafenum=? and usernum=?) where cafenum=? and usernum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cafeNum);
			pstmt.setInt(2, userNum);
			pstmt.setInt(3, cafeNum);
			pstmt.setInt(4, userNum);
			int i = pstmt.executeUpdate();
			if (i>0) {
				return 1;
			}else {
				return 0;
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	 
	public PostVo getPostInfo(int postNum, int cafeNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "update post set postinvitecount = (select postinvitecount+1 from post where postnum=?) where postnum=?";
			String sql2 = "select * from post natural join cafemember where postnum=? and cafenum=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, postNum);
			pstmt1.setInt(2, postNum);
			pstmt2 = con.prepareStatement(sql2);
			pstmt2.setInt(1, postNum);
			pstmt2.setInt(2, cafeNum);
			int i = pstmt1.executeUpdate();
			if(i>0) {
				rs2 = pstmt2.executeQuery();
			}
			PostVo vo = null;
			while (rs2.next()) {
				vo=new PostVo(postNum, rs2.getInt("cafepostnum"), rs2.getInt("boardnum"), rs2.getString("posttitle"), rs2.getString("postcontent").replaceAll("\n", "<br>"), rs2.getDate("postdate"), rs2.getString("cafememnick"),rs2.getInt("usernum"), rs2.getInt("postcatnum"), rs2.getInt("postInviteCount"));
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally { 
			DBCPBean.close(null, pstmt2, rs2);
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public ArrayList<PostCommentVo> getPostCommentInfo(int postNum, int cafeNum, int startRow, int endRow) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select * from (select p.*, rownum rnum from postcomment p where postnum=? order by commentregdate desc) natural join cafemember where cafenum=? and rnum>=? and rnum<=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, postNum);
			pstmt1.setInt(2, cafeNum);
			pstmt1.setInt(3, startRow);
			pstmt1.setInt(4, endRow);
			rs1=pstmt1.executeQuery();
			ArrayList<PostCommentVo> list = new ArrayList<PostCommentVo>();
			while (rs1.next()) {
				list.add(new PostCommentVo(rs1.getInt("commentnum"), rs1.getInt("postnum"), rs1.getString("cafememnick"), rs1.getInt("usernum"), rs1.getString("postcomment"), rs1.getDate("commentRegdate")));
			}
			return list;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public HashMap<Integer, Integer> getPostCommentCount() {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select postnum, count(*) count from postcomment group by postnum";
			pstmt1 = con.prepareStatement(sql1);
			rs1=pstmt1.executeQuery();
			HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
			while (rs1.next()) {
				map.put(rs1.getInt("postnum"), rs1.getInt("count"));
			}
			return map;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt1, rs1);
		}
	}
	
	public int deleteComment(int commentNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "delete postcomment where commentnum=?";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, commentNum);
			int i = pstmt1.executeUpdate();
			return i;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt1, null);
		}
	}
	
	public int insertComment(int userNum, int postNum, String comment) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "insert into postcomment values((select max(commentnum)+1 from postcomment),?,?,?,sysdate) ";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, postNum);
			pstmt1.setInt(2, userNum);
			pstmt1.setString(3, comment);
			int i = pstmt1.executeUpdate();
			return i;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt1, null);
		}
	}
	
	public HashMap<Integer, String> getPostCatList() {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "select * from postcat";
			pstmt1 = con.prepareStatement(sql1);
			rs=pstmt1.executeQuery();
			HashMap<Integer, String> map = new HashMap<Integer, String>();
			while(rs.next()) {
				map.put(rs.getInt("postcatnum"), rs.getString("postcatname"));
			}
			return map;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt1, null);
		}
	}
	
	public int insertPost(int userNum, int boardNum, String postTitle, String postContent, int postCatNum) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		try {
			con = DBCPBean.getConn();
			String sql1 = "insert into post values((select max(postnum)+1 from post),(select max(postnum)+1 from post natural join cafeboard natural join cafeboardcat where cafenum=(select cafenum from cafeboard natural join cafeboardcat where boardnum=?)),?,?,?,sysdate,?,?,0)";
			pstmt1 = con.prepareStatement(sql1);
			pstmt1.setInt(1, boardNum);
			pstmt1.setInt(2, boardNum);
			pstmt1.setString(3, postTitle);
			pstmt1.setString(4, postContent);
			pstmt1.setInt(5, userNum);
			pstmt1.setInt(6, postCatNum);
			int i = pstmt1.executeUpdate();
			return i;
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt1, null);
		}
	}
	
}
