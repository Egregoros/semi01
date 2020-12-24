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
   
    //파일번호에 해당하는 파일정보 반환하는 메소드
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
            String sql="select * from POSTFILE order by filenum desc"; //깃올라와잇는 vo에 저렇게되잇길래 이름만다르니깐 그냥가져다가썻죠 근데안되네용 ㅠㅠ  vo랑 테이블 이름이랑 다른데여? 저거는 테이블이고 vo는 자바에서 만든 형태인거고 테이블 이름은 통일시켜줘야되요 우리 모두 그래서 그냥 sql 돌리시면 될텐데 ㅇㅅㅇ  
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
        String sql="insert into POSTFILE values((select NVL(max(filenum),0)+1 from POSTFILE),?,?,?)"; //저거 다 삭제해도 되죠? 이름이 같아서 계속 딴데서 불러오는것같은데;;;되기만된다면야 상관없습니다 ㅠㅠㅠ
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
            try{ //저거저번에 떳을때 찾아보니깐 not null인데 null형태로 들어가져서 뜬다고도하더라고요 아 근데 안되요 ㅠㅠ
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