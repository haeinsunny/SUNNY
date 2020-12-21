package co.sunny.board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.sunny.board.dao.BoardDao;
import co.sunny.board.vo.BoardVo;


@WebServlet("/BoardInput.do")
public class BoardInputServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardInputServ() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    // command구현없이 여기 컨트롤러에서 처리
				request.setCharacterEncoding("utf-8");
				BoardDao dao = new BoardDao();
				BoardVo vo = new BoardVo();

				vo.setBid(request.getParameter("writer")); 
				vo.setBtitle(request.getParameter("title"));
				vo.setBcontent(request.getParameter("content"));
				// id값은 시뭔스로 자동 넣을거임!

				int n = dao.insert(vo); // n에 dao의 insert(vo)를 실어서 보낸다. 입력건 나타냄

				String viewPage;
				if (n != 0) { // 항상 정상적으로 입력됐는지 모르므로 입력이 성공했으면 메세지 보여주려고
					response.sendRedirect("/Member/BorderList.do");  
					//어노테이션 기반에서 서블릿 호출시는 response객체를 이용해서 호출한다.
				} else {
					String msg = "정상적으로 입력하지 못했습니다.";
					request.setAttribute("msg", msg);  //위의 매세지를 req객체에 실어보낸다.
					viewPage = "jsp/border/inputError.jsp";	
					RequestDispatcher dp = request.getRequestDispatcher(viewPage);	//디스패쳐에 실어보내기
					dp.forward(request, response);
				}		
						
			}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
