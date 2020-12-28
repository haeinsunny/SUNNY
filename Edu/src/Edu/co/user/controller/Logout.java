package Edu.co.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout.do")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃은  DB갈 필요없이 세션과 권한만 지워주면 됨
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);	
		
		String name = (String) (session.getAttribute("name")); //세션에 담긴 name값을 String에 담는다.
		
		session.invalidate();  //session값 삭제
		
		request.setAttribute("name", name); //왜 request 해주냐? (페이지에서 로그아웃 메세지를 보여주기위해)
		
		String viewPage = "jsp/common/main.jsp";	 //페이지를 보여주자
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);	
		dp.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
