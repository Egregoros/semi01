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

@WebServlet("/controller/upcon.do")///����ٰ� /ccon/upcon.do �̰� ����žߴ뿩 ���� ������ ����ٺ���
public class upcon extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String savePath = req.getServletContext().getRealPath("/upload"); //����������ɼ����ǰ��.
		int sizeLimit = 1024*1024*15; //����ũ������
		MultipartRequest multi = new MultipartRequest(req, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
		//������ü�� ���ε�.

			File file=multi.getFile("file");
			System.out.println(file);

			String orgFileName=multi.getOriginalFileName("file"); //submit�� �����̸�
			String saveFileName=multi.getFilesystemName("file")	;//�����̸�
			System.out.println(saveFileName);
			System.out.println(orgFileName);
			
			
			PostVo postvo=new PostVo(); //vo��ü����.
			FileTableVo filetablevo = new FileTableVo(); //�����͵����
			filetablevo.setFileNum(0);
			filetablevo.setPostNum(postvo.getPostNum());
			filetablevo.setOrgFileName(orgFileName);
			filetablevo.setSaveFileName(saveFileName);		
			
			filedao dao=new filedao();
			dao.insert(filetablevo); //dao��ü����.���� vo�ֱ�
			int n=dao.insert(filetablevo);
			if(n>0) {
				System.out.println("�������强��");
			}else {
				System.out.println("�����������");
			}
			
			req.setAttribute("filetablevo", filetablevo); //return�� �������� ����ϰ����� vo�Ӽ�.
	}
}