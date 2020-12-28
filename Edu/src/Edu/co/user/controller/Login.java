package Edu.co.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Edu.co.user.dao.UserDao;
import Edu.co.user.encypt.SHA256;
import Edu.co.user.vo.UserVo;

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
		UserDao dao = new UserDao(); // DB연결하기위해선 dao, vo 클래스 받아서 무조건 두개 생성
		UserVo vo = new UserVo();

		HttpSession session = request.getSession(false); // 세션객체 만들기. false: 만약 세션이 안만들어졌으면 만들어달라.

		// 암호화
		SHA256 sha256 = new SHA256();
		String ePw = "";
		try {
			ePw = sha256.encrytion(request.getParameter("pw"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		vo.setPw(ePw);
		
		System.out.println("입력한 패스워드: " + request.getParameter("pw"));
		System.out.println("암호화된 패스워드: " + ePw);

		vo.setId(request.getParameter("id")); // DB로부터 넘어왔을땐 mid에 담겨있다 얘를 m_id에 담음

		//로그인쿼리
		vo = dao.CheckId(vo);
		
		System.out.println(vo.getId());

		// 페이지를 보여주자
		String viewPage = "jsp/common/main.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);

		// DB의 id가 널이 아니면 세션에 값을 담자
		if (vo.getId() != null) {
			// request객체에 싣기
			request.setAttribute("vo", vo);
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
			session.setAttribute("type", vo.getType());			
			dp.forward(request, response);
		} else {

			String msg = "로그인 실패 했습니다.";
			request.setAttribute("msg", msg);				
			dp.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
