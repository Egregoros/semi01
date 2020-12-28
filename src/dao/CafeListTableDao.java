package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import vo.CafeListCatNameVo;
import vo.CafeListVo;
import vo.CafeMemberVo;

public class CafeListTableDao {
   
   public int insert(CafeListVo vo) {
      Connection con = null;
      PreparedStatement pstmt = null;
      PreparedStatement pstmt1 = null;
      PreparedStatement pstmt2 = null;
      int maxNum = getMaxNum()+1;
      CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
      UserInfoDao userDao = new UserInfoDao();
      
      try {
         con = DBCPBean.getConn();
         String sql = "insert into cafelist values (?, ?, ?, ?, ?, ?, sysdate)"; 
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, maxNum);
         pstmt.setInt(2, vo.getGradeNum());
         pstmt.setInt(3, vo.getCatNum());
         pstmt.setString(4, vo.getCafeName());
         pstmt.setInt(5, vo.getUserNum());
         pstmt.setString(6, vo.getContent());
         pstmt.executeUpdate();
         
         return maxNum;
      } catch (SQLException se) {
         se.printStackTrace();
         return -1;
      } finally {
         DBCPBean.close(pstmt1);
         DBCPBean.close(pstmt2);
         DBCPBean.close(con, pstmt, null);
      }
   }
   
   public int delete(int cafeNum) {
      Connection con = null;
      PreparedStatement pstmt = null;
      PreparedStatement pstmt1 = null;
      PreparedStatement pstmt2 = null;
      PreparedStatement pstmt3 = null;
      try {
         con = DBCPBean.getConn();
         String sql = "delete from cafelist where cafeNum = ?";
         String sql1 = "delete from cafeMember where cafeNum = ?";
         String sql2 = "delete from cafeMemGrade where cafeNum = ?";
         String sql3 = "delete from cafeBoard where cafeNum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt1 = con.prepareStatement(sql1);
         pstmt2 = con.prepareStatement(sql2);
         pstmt3 = con.prepareStatement(sql3);
         pstmt.setInt(1, cafeNum);
         pstmt1.setInt(1, cafeNum);
         pstmt2.setInt(1, cafeNum);
         pstmt3.setInt(1, cafeNum);
         
         pstmt3.executeUpdate();
         pstmt2.executeUpdate();
         pstmt1.executeUpdate();
         return pstmt.executeUpdate();
         
      } catch (SQLException se) {
         se.printStackTrace();
         return -1;
      } finally {
         DBCPBean.close(pstmt3);
         DBCPBean.close(pstmt2);
         DBCPBean.close(pstmt1);
         DBCPBean.close(con, pstmt, null);
      }
   }
   
   public ArrayList<CafeListVo> list(int startRow, int endRow, String field, String keyword) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "";
      if(field != null && !field.equals("")) {
         sql = "select * from(select rownum rnum, aa.* from(select * from cafelist natural join cafecat natural join (select cafenum, count(*) count from cafemember group by cafenum) where "+ field +" like '%" + keyword + "%' order by count desc)aa)where rnum>=? and rnum <=?";
      } else {
         sql = "select * from(select rownum rnum, aa.* from(select * from cafelist natural join cafecat natural join (select cafenum, count(*) count from cafemember group by cafenum) order by count desc)aa)where rnum>=? and rnum <=?";
      }
      
      try {
         con = DBCPBean.getConn();
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);   
         rs = pstmt.executeQuery();
         ArrayList<CafeListVo> list = new ArrayList<CafeListVo>();
         while (rs.next()) {
            int cafeNum = rs.getInt("cafeNum");
            int gradeNum = rs.getInt("gradeNum");
            int catNum = rs.getInt("catNum");
            String cafeName = rs.getString("cafeName");
            int userNum = rs.getInt("userNum");
            String cafeRegDate = rs.getDate("cafeRegDate").toString();
            String content = rs.getString("content");
            CafeListVo vo = new CafeListVo(cafeNum, gradeNum, catNum, cafeName, userNum, cafeRegDate, content);
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
   
   public int getMaxNum() {
      Connection con = null;
      PreparedStatement pstmt = null;
      int n = 0;
      ResultSet rs = null;
      try {
         con = DBCPBean.getConn();
         String sql = "select NVL(max(cafeNum),0) from cafeList";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         rs.next();
         int maxNum = rs.getInt(1);
         return maxNum;
      } catch (SQLException se) {
         se.printStackTrace();
         return -1;
      } finally {
         DBCPBean.close(con, pstmt, rs);
      }
   }
   
   public int getCount(String field, String keyword) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = DBCPBean.getConn();
         String sql = "select NVL(count(cafeNum),0) from cafeList";
         if(field != null && !field.equals("")) {
            sql = "select NVL(count(cafeNum),0) from cafeList where " + field + " like '%" + keyword + "%'";
         }
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         rs.next();
         return rs.getInt(1);
      } catch (SQLException se) {
         se.printStackTrace();
         return -1;
      } finally {
         DBCPBean.close(con, pstmt, rs);
      }
   }

   public ArrayList<CafeListCatNameVo> catList(int startRow, int endRow, String field, String keyword) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "";
      if(field != null && !field.equals("")) {
         sql = "select * from(select rownum rnum, aa.* from(select * from cafelist natural join cafecat natural join (select cafenum, count(*) count from cafemember group by cafenum) where "+ field +" like '%" + keyword + "%' order by count desc)aa)where rnum>=? and rnum <=?";
      } else {
         sql = "select * from(select rownum rnum, aa.* from(select * from cafelist natural join cafecat natural join (select cafenum, count(*) count from cafemember group by cafenum) order by count desc)aa)where rnum>=? and rnum <=?";
      }
      
      try {
         con = DBCPBean.getConn();
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);   
         rs = pstmt.executeQuery();
         ArrayList<CafeListCatNameVo> list = new ArrayList<CafeListCatNameVo>();
         while (rs.next()) {
            int cafeNum = rs.getInt("cafeNum");
            int gradeNum = rs.getInt("gradeNum");
            int catNum = rs.getInt("catNum");
            String cafeName = rs.getString("cafeName");
            int userNum = rs.getInt("userNum");
            String cafeRegDate = rs.getDate("cafeRegDate").toString();
            String catName = rs.getString("catName");
            CafeListCatNameVo vo = new CafeListCatNameVo(cafeNum, gradeNum, catNum, cafeName, userNum, cafeRegDate, catName);
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
   
   public CafeListVo getOne(int cafeNum) {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      CafeListVo cafeListVo = null;
      try {
         con = DBCPBean.getConn();
         String sql = "select * from cafelist where cafeNum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setInt(1, cafeNum);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            int userNum = rs.getInt("userNum");
            int gradeNum = rs.getInt("gradeNum");
            int catNum = rs.getInt("catNum");
            String cafeName = rs.getString("cafeName");
            String content = rs.getString("content");
            String cafeRegDate = rs.getString("cafeRegDate");
            cafeListVo = new CafeListVo(cafeNum, gradeNum, catNum, cafeName, userNum, content, cafeRegDate);
         }
         return cafeListVo;
      } catch (SQLException se) {
         se.printStackTrace();
         return null;
      } finally {
         DBCPBean.close(con, pstmt, rs);
      }
   }
   
   public int update(CafeListVo vo) {
      Connection con = null;
      PreparedStatement pstmt = null;
      int n = 0;
      try {
         con = DBCPBean.getConn();
         String sql = "update cafelist set cafename = ?, catnum = ?, content = ? where cafenum = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, vo.getCafeName());
         pstmt.setInt(2, vo.getCatNum());
         pstmt.setString(3, vo.getContent());
         pstmt.setInt(4, vo.getCafeNum());
         
         return pstmt.executeUpdate();
      } catch (SQLException se) {
         se.printStackTrace();
         return -1;
      } finally {
         DBCPBean.close(con, pstmt, null);
      }
   }
   
}