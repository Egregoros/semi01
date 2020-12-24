package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBCPBean;
import vo.FileTableVo;

public class filedao {
    private DBCPBean dbcp;
    public filedao() {
        dbcp=new DBCPBean();
    }
    public int delete(int filenum){
        Connection con=null;
        PreparedStatement pstmt=null;
        try{
            con=dbcp.getConn();
            String sql="delete from POSTFILE where filenum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,filenum);
            int n=pstmt.executeUpdate();
            return n;
        }catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        }finally{
            try{
                if(pstmt!=null) pstmt.close();
                if(con!=null) con.close();
            }catch(SQLException se){}
        }
    }
   
    //���Ϲ�ȣ�� �ش��ϴ� �������� ��ȯ�ϴ� �޼ҵ�
    public FileTableVo getInfo(int filenum){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            con=dbcp.getConn();
            String sql="select * from POSTFILE where filenum=?";
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,filenum);
            rs=pstmt.executeQuery();
            if(rs.next()){
                FileTableVo vo=new FileTableVo(
                        rs.getInt("filenum"),
                        rs.getInt("postNum"),
                        rs.getString("saveFileName"),
                        rs.getString("orgFileName"));
                return vo;
            }
            return null;
        }catch(SQLException se){
            System.out.println(se.getMessage());
            return null;
        }finally{
            try{
                if(rs!=null) rs.close();
                if(pstmt!=null) pstmt.close();
                if(con!=null) con.close();
            }catch(SQLException se){}
        }
    }
    public ArrayList<FileTableVo> getList(){
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        try{
            con=dbcp.getConn();
            String sql="select * from POSTFILE order by filenum desc"; //��ö���մ� vo�� �����Ե��ձ淡 �̸����ٸ��ϱ� �׳ɰ����ٰ����� �ٵ��ȵǳ׿� �Ф�  vo�� ���̺� �̸��̶� �ٸ�����? ���Ŵ� ���̺��̰� vo�� �ڹٿ��� ���� �����ΰŰ� ���̺� �̸��� ���Ͻ�����ߵǿ� �츮 ��� �׷��� �׳� sql �����ø� ���ٵ� ������  
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            ArrayList<FileTableVo> list=new ArrayList<>();
            while(rs.next()){
                FileTableVo vo=new FileTableVo(
                        rs.getInt("filenum"),
                        rs.getInt("postNum"),
                        rs.getString("saveFileName"),
                        rs.getString("orgFileName"));
                list.add(vo);
            }
            return list;
        }catch(SQLException se){
            System.out.println(se.getMessage());
            return null;
        }finally{
            try{
                if(rs!=null) rs.close();
                if(pstmt!=null)pstmt.close();
                if(con!=null) con.close();
            }catch(SQLException se){}
        }        
    }  
    
    public int insert(FileTableVo vo){
        Connection con=null;
        PreparedStatement pstmt=null;
        String sql="insert into POSTFILE values((select NVL(max(filenum),0)+1 from POSTFILE),?,?,?)"; //���� �� �����ص� ����? �̸��� ���Ƽ� ��� ������ �ҷ����°Ͱ�����;;;�Ǳ⸸�ȴٸ�� ��������ϴ� �ФФ�
        try{
            con=dbcp.getConn();
            pstmt=con.prepareStatement(sql);
            pstmt.setInt(1,vo.getPostNum());
            pstmt.setString(2,vo.getSaveFileName());
            pstmt.setString(3,vo.getOrgFileName());
            int n=pstmt.executeUpdate();
            return n;
        }catch(SQLException se){
            System.out.println(se.getMessage());
            return -1;
        }finally{
            try{ //���������� ������ ã�ƺ��ϱ� not null�ε� null���·� ������ ��ٰ��ϴ����� �� �ٵ� �ȵǿ� �Ф�
                if(pstmt!=null) pstmt.close();
                if(con!=null) con.close();
            }catch(SQLException se){}
        }
    }
    
    /*
    public int insert(int filenum,int postNum,String saveFileName, String orgFileName) {
    	 	Connection con=null;
		PreparedStatement pstmt=null;
	try {
		con=DBCPBean.getConn();
		String sql="insert into POSTFILE values((select NVL(max(filenum),0)+1 from POSTFILE),?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, postNum);
		pstmt.setString(2, saveFileName);
		pstmt.setString(3, orgFileName);
		pstmt.executeUpdate();
		return 1;
	}catch (Exception se) {
		se.printStackTrace();
		return -1;
	}finally {
		DBCPBean.close(con, pstmt, null);
	}
	
}*/
	
}