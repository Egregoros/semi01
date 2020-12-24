package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dao.filedao;
import vo.FileTableVo;
import vo.PostVo;

@WebServlet("/controller/upcon.do")///여기다가 /ccon/upcon.do 이거 저긍셔야대여 하으 여러개 만들다보니
public class upcon extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("/upload"); //파일이저장될서버의경로.
		int sizeLimit = 1024*1024*15; //파일크기제한
		MultipartRequest multi = new MultipartRequest(req, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		//파일자체의 업로드.

			File file=multi.getFile("file");
			System.out.println(file);

			String orgFileName=multi.getOriginalFileName("file"); //submit한 파일이름
			String saveFileName=multi.getFilesystemName("file")	;//저장이름
			System.out.println(saveFileName);
			System.out.println(orgFileName);
			
			
			PostVo postvo=new PostVo(); //vo객체생성.
			FileTableVo filetablevo = new FileTableVo(); //데이터들셋팅
			filetablevo.setFileNum(0);
			filetablevo.setPostNum(postvo.getPostNum());
			filetablevo.setOrgFileName(orgFileName);
			filetablevo.setSaveFileName(saveFileName);		
			
			filedao dao=new filedao();
			dao.insert(filetablevo); //dao객체생성.한후 vo넣기
			int n=dao.insert(filetablevo);
			if(n>0) {
				System.out.println("파일저장성공");
			}else {
				System.out.println("파일저장실패");
			}
			
			req.setAttribute("filetablevo", filetablevo); //return할 페이지에 출력하게위한 vo속성.
	}
}