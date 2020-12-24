package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.filedao;
import vo.FileTableVo;

@WebServlet("/download")
public class downcon extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int fileNum=Integer.parseInt(req.getParameter("filenum"));
		filedao dao=new filedao();
		FileTableVo vo=dao.getInfo(fileNum);
		String filename=vo.getOrgFileName();
		String savefilename=vo.getSaveFileName();
		filename=URLEncoder.encode(filename,"utf-8");
		filename=filename.replaceAll("\\+", "%20");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename=" + filename);		
		OutputStream out=resp.getOutputStream();
		String path=getServletContext().getRealPath("/upload");//ServletContext=application
		File f=new File(path + File.separator + savefilename);
		FileInputStream fis=new FileInputStream(f);	
		BufferedInputStream bis=new BufferedInputStream(fis);
		BufferedOutputStream bos=new BufferedOutputStream(out);
		byte[] b=new byte[1024];
		int n=0;
		while((n=bis.read(b))!=-1) {
			bos.write(b, 0, n);
		}
		bos.close();
		bis.close();
		}
	}