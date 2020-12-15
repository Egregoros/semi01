package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBCPBean;

public class FindPwdController {
	String phone=request.getParameter("phone");
	String pwd=request.getParameter("pwd");
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
		System.out.print("ȸ������ ���̵�� " + id + "�Դϴ�.");
	}else{
		System.out.print("�Է��Ͻ� �޴�����ȣ �Ǵ� ��й�ȣ�� ��ġ�ϴ� ���̵� �����ϴ�.");
	}