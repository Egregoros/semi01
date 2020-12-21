package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import db.DBCPBean;
import vo.CatTableVo;

public class CatTableDao {
	private static CatTableDao instance = new CatTableDao();
	private CatTableDao() {}
	public static CatTableDao getInstance() {
		return instance;
	}
	
	public ArrayList<CatTableVo> list(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from (select rownum rnum, aa.* from (select * from cafeCat order by catnum)aa)";
		
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<CatTableVo> list = new ArrayList<CatTableVo>();
			while(rs.next()) {
				int catNum = rs.getInt("catNum");
				String catName = rs.getString("catName");
				CatTableVo vo = new CatTableVo(catNum, catName);
				list.add(vo);
			}
			return list;
		} catch(SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
	
	public CatTableVo getCatVo(String catName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CatTableVo vo = null;
		String sql = "select * from cafecat where catName = ?";
		try {
			con = DBCPBean.getConn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, catName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int catNum = rs.getInt("catNum");
				String catName1 = rs.getString("catName");
				vo = new CatTableVo(catNum, catName1);
			}
			return vo;
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			DBCPBean.close(con, pstmt, rs);
		}
	}
}
