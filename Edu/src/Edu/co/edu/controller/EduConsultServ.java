package Edu.co.edu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.consult.dao.ConDao;
import Edu.co.consult.vo.ConVo;
import Edu.co.consult.vo.ReqVo;
import Edu.co.edu.vo.EduVo;
import Edu.co.user.dao.UserDao;
import Edu.co.user.vo.UserVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/EduConsult.do")
public class EduConsultServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EduConsultServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		ConDao dao = new ConDao();
		try {
			EduVo vo = new EduVo();
			// 학원이름을 가져와서 쿼리하고 학원정보 저장
			vo.setName(request.getParameter("cname")); // 학원이름 저장
			vo = dao.selectCon(vo);
			System.out.println(vo.getName()); //학원이름
			
			dao = new ConDao();
			// 학원정보를 가지고 REQ 저장
			int n = dao.uInsert(vo);
			System.out.println(n + " 건 상담입력 되었습니다.");
			
			dao = new ConDao();
			UserVo uo = new UserVo();
			// 학생 이름을 가져와서 쿼리하고 학생정보 저장
			uo.setName(request.getParameter("name")); // 학원이름 저장
			uo = dao.selectOne(uo);
			System.out.println(uo.getName()); //학생이름
			
			dao = new ConDao();
			// 학생정보를 가지고 CON 저장
			n = dao.cInsert(uo);
			System.out.println(n + " 건 상담신청 들어왔습니다.");		
			
			//ajax 결과값 돌려주기	
			response.getWriter().append("ok");
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
