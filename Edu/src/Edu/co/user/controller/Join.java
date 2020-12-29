package Edu.co.user.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		UserDao dao = new UserDao();
		UserVo vo = new UserVo();

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

		vo.setId(request.getParameter("id"));
		vo.setType(request.getParameter("type"));
		vo.setSort(request.getParameter("sort"));

		// 학생이름과 학원명 구분해서 DB입력
		if (request.getParameter("name") == "") {
			vo.setName(request.getParameter("aname"));
		} else {
			vo.setName(request.getParameter("name"));
		}

		// age int형 null값 못들어가게 예외처리
		if (request.getParameter("age") == "") {
			vo.setAge(0);
		} else {
			vo.setAge(Integer.parseInt(request.getParameter("age")));
		}

		vo.setTel(request.getParameter("tel"));
		vo.setAddr(request.getParameter("addr"));

		int n = dao.insert(vo);

		// 학원등록
		if (request.getParameter("aname") != "") {
			dao = new UserDao();
			int m = dao.eInsert(vo);
			System.out.println(m + "건 학원등록 되었습니다.");
		}

		String viewPage = "jsp/user/joinResult.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);

		if (n != 0) {
			String msg = vo.getName() + "님 회원가입 되었습니다.";
			request.setAttribute("msg", msg);
			// request.setAttribute("name", vo.getName());
			dp.forward(request, response);

		} else {
			String msg = "정상적으로 가입하지 못했습니다.";
			request.setAttribute("msg", msg);
			viewPage = "jsp/user/joinResult.jsp";
			dp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
