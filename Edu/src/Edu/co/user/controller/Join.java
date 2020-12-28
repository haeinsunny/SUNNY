package Edu.co.user.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.user.dao.UserDao;
import Edu.co.user.encypt.SHA256;
import Edu.co.user.vo.UserVo;


@WebServlet("/Join.do")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDao dao = new UserDao();
		UserVo vo = new UserVo();
		
		//암호화
		SHA256 sha256 = new SHA256();
		String ePw="";
		try {
			ePw = sha256.encrytion(request.getParameter("pw"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setPw(ePw);
		
		System.out.println("입력한 패스워드: " + request.getParameter("pw"));
		System.out.println("암호화된 패스워드: " + ePw);
		
		vo.setId(request.getParameter("id"));  
		vo.setType(request.getParameter("type"));
		vo.setSort(request.getParameter("sort"));
		vo.setName(request.getParameter("name"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		vo.setTel(request.getParameter("tel"));
		vo.setAddr(request.getParameter("addr"));
		vo.setType(request.getParameter("type"));

		int n = dao.insert(vo); 
		
		request.setAttribute("name", vo.getName());	
		
		String viewPage = "jsp/user/joinResult.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);	
				
		if (n != 0) { 
			dp.forward(request, response);
			
		} else {
			String msg = "정상적으로 입력하지 못했습니다.";
			request.setAttribute("msg", msg); 
			viewPage = "jsp/error/inputError.jsp";	
			dp.forward(request, response);
		}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}