package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DBCPBean;
import vo.CafeMemGradeVo;

public class CafeMemGradeDao {
	private static CafeMemGradeDao instance = new CafeMemGradeDao();
	private CafeMemGradeDao() {}
	public static CafeMemGradeDao getInstance()	{
		return instance;
	}
	
	public int insert(CafeMemGradeVo vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBCPBean.getConn();
			String sql = "insert into cafememgrade values (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, vo.getCafeNum());
			pstmt.setInt(2, vo.getCafeMemGrade());
			pstmt.setString(3, vo.getCafeMemGradeName());
			
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			se.printStackTrace();
			return -1;
		} finally {
			DBCPBean.close(con, pstmt, null);
		}
	}
	
}
