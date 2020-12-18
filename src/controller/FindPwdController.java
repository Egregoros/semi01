package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBCPBean;

public class FindPwdController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String phone=req.getParameter("phone");
		String pwd=req.getParameter("pwd");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String id=null;
		try{
			con=DBCPBean.getConn();
			String sql="select * from userinfo where phone=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, phone);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				id=rs.getString("id");
			}
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			DBCPBean.close(con, pstmt, rs);
		}
		if(id!=null){
			System.out.print("회원님의 아이디는 " + id + "입니다.");
		}else{
			System.out.print("입력하신 휴대폰번호 또는 비밀번호와 일치하는 아이디가 없습니다.");
		}
	}
}