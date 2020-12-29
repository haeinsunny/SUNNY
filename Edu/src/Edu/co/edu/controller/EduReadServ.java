package Edu.co.edu.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.edu.dao.EduDao;
import Edu.co.edu.vo.EduVo;

@WebServlet("/EduRead.do")
public class EduReadServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public EduReadServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EduDao dao = new EduDao(); 
		EduVo vo = new EduVo(); 

		vo.setE_no(request.getParameter("e_no")); 
		vo = dao.selectOne(vo); 

		request.setAttribute("vo", vo);
		String viewPage = "/Edu/jsp/eduList/eduRead.jsp"; 

		RequestDispatcher dp = request.getRequestDispatcher(viewPage); 
		dp.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
