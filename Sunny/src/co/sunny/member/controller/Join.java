package co.sunny.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.sunny.member.dao.MemberDao;
import co.sunny.member.vo.MemberVo;


@WebServlet("/MemberJoin.do")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDao dao = new MemberDao();
		MemberVo vo = new MemberVo();

		vo.setmId(request.getParameter("id")); // request객체의 getParameter가 가지고있는 "writer": input.jsp page가 가지고있는 name의 변수명
		vo.setmName(request.getParameter("name")); // 넘어온 date타입을 date타입으로 바꿔준다(db에는 date타입이므로)
		vo.setPassword(request.getParameter("pw"));

		int n = dao.join(vo); // n에 dao의 insert(vo)를 실어서 보낸다. 입력건 나타냄
		
		request.setAttribute("name", vo.getmName());	
		
		String viewPage = "jsp/member/joinResult.jsp";
		RequestDispatcher dp = request.getRequestDispatcher(viewPage);	//디스패쳐에 실어보내기
				
		if (n != 0) { // 항상 정상적으로 입력됐는지 모르므로 입력이 성공했으면 메세지 보여주려고	
			dp.forward(request, response);
			//response.sendRedirect("/Member/jsp/member/joinResult.jsp"); 
			//어노테이션 기반에서 서블릿 호출시는 response객체를 이용해서 호출한다.
		} else {
			String msg = "정상적으로 입력하지 못했습니다.";
			request.setAttribute("msg", msg);  //위의 매세지를 req객체에 실어보낸다.
			viewPage = "jsp/border/inputError.jsp";	
			dp.forward(request, response);
		}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
