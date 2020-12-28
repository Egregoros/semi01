package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CafeListTableDao;
import dao.CafeMemberDao;
import dao.CatTableDao;
import dao.UserInfoDao;
import vo.CafeListVo;
import vo.CafeMemberVo;
import vo.CatTableVo;
import vo.UserInfoVo;
@WebServlet("/cafe/cafeUpdate")
public class CafeUpdateController extends HttpServlet{
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      HttpSession session = req.getSession();
      int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
      Integer userNum = (Integer)session.getAttribute("userNum");
      UserInfoDao userDao = new UserInfoDao();
      UserInfoVo userVo = userDao.getOne(userNum);
      String id = userVo.getId();
      
      CafeListTableDao cafeListDao = new CafeListTableDao();
      CafeListVo cafeListVo = cafeListDao.getOne(cafeNum);
      
      CafeMemberDao cafeMemDao = CafeMemberDao.getInstance();
      CafeMemberVo cafeMemVo = cafeMemDao.getcafeMemGradeNum(userNum, cafeNum);
      
      CatTableDao catTableDao = CatTableDao.getInstance();
      ArrayList<CatTableVo> catList = catTableDao.list();
      
      //get userNum from cafeNum
      if(session.getAttribute("userNum") == null) {
         resp.sendRedirect(req.getContextPath()+"/login/login.jsp");
      } else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() > 1) {
         resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
      } else if (userNum != cafeMemVo.getUserNum()) {
         resp.sendRedirect(req.getContextPath()+"/jsp/cafe-main.do?cafeNum="+cafeNum);
      } else if (userNum == cafeMemVo.getUserNum() && cafeMemVo.getCafeMemGradeNum() <= 1) {
         req.setAttribute("cafeListVo", cafeListVo);
         req.setAttribute("catList", catList);
         req.setAttribute("userNum", userNum);
         req.getRequestDispatcher("/cafe/cafeUpdate.jsp?cafeNum="+cafeNum).forward(req, resp);
      } else {
         resp.sendRedirect(req.getContextPath()+"/cafeList");
      }
      
   }
   
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.setCharacterEncoding("utf-8");
      HttpSession session = req.getSession();
      Integer userNum = (Integer)session.getAttribute("userNum");
       int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
      
      String cafeName = req.getParameter("cafeName");
      String catName = req.getParameter("catName");
      String content = req.getParameter("content");
      
      CafeListTableDao listDao = new CafeListTableDao();
      CafeListVo listVo = listDao.getOne(cafeNum);
      
      CatTableDao catDao = CatTableDao.getInstance();
      CatTableVo catVo = catDao.getCatVo(catName);
      
      UserInfoDao userDao = new UserInfoDao();
      UserInfoVo userVo = userDao.getOne(userNum);
      
      CafeListVo cafeListVo = new CafeListVo(cafeNum, listVo.getGradeNum(), catVo.getCatNum(), cafeName, userVo.getUserNum(), content, null);
      
      listDao.update(cafeListVo);
      
      int n = listDao.update(cafeListVo);
      req.getRequestDispatcher("/cafe/CafeUpdateMain.jsp?cafeNum="+cafeNum).forward(req, resp);
   }
}