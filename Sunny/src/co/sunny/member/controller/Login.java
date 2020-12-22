package co.sunny.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import co.sunny.member.dao.MemberDao;
import co.sunny.member.vo.MemberVo;

@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 여기서 모든것을 처리(command 안만듬)
		request.setCharacterEncoding("utf-8");
		MemberDao dao = new MemberDao(); // DB연결하기위해선 dao, vo 클래스 받아서 무조건 두개 생성
		MemberVo vo = new MemberVo();

		HttpSession session = request.getSession(false); // 세션객체 만들기. false: 만약 세션이 안만들어졌으면 만들어달라.

		vo.setmId(request.getParameter("id")); // DB로부터 넘어왔을땐 mid에 담겨있다 얘를 m_id에 담음
		vo.setPassword(request.getParameter("pw"));

		vo = dao.mLoginCheck(vo); //login체크한 값이 담기게됨

		if(vo.getmAuth() != "") { //관리자가 널이 아니면(값이 담겨있으면) 세션에 id값과 pw값을 담음
		session.setAttribute("id", vo.getmId());
		session.setAttribute("name", vo.getmName());
		session.setAttribute("auth", vo.getmAuth());
		}
		
		//request객체에 싣기
		request.setAttribute("vo", vo);
//		MemberVo temp = (MemberVo) request.getAttribute("vo"); //request에 담긴걸 (MemberVo)로 명시적변환

		String viewPage = "jsp/member/loginResult.jsp";	 //페이지를 보여주자
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);	
		dp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
