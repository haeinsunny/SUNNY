package Edu.co.edu.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.edu.dao.EduDao;
import Edu.co.edu.vo.EduVo;
import Edu.co.edu.vo.PageVo;

@WebServlet("/SearchList.do")
public class SearchListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SearchListServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EduDao dao = new EduDao();

		// 페이징 처리
		try {
			String word = request.getParameter("word");
			String pageNum = request.getParameter("pageNum"); // jsp페이지로부터 값 받아오기
			dao = new EduDao(); // 위에서 dao.selectAll()하고 디비 닫아버림 다시 conn해야해서
			PageVo paging = new PageVo();
			pageNum = pageNum == null ? "1" : pageNum; // page가 null이면 1이라고 해주고 아니면 pageNum을 넣는다
			paging.setPageNo(Integer.parseInt(pageNum));
			paging.setPageSize(10);
			paging.setTotalCount(dao.getSCount(word)); // 리스트의 총 데이터갯수 (기준으로 페이지나눔)

			int startRow = paging.getPageNo() * 10 - 9; // 리스트 조회 (한페이지당 10개씩)
			int endRow = paging.getPageNo() * 10;
			dao = new EduDao();
			ArrayList<EduVo> list = dao.Search(word, startRow, endRow);
			request.setAttribute("list", list);

			int currentPage = Integer.parseInt(pageNum); // 넘어온 pageNum은 string이므로 파싱하기
			int prevPage = currentPage == 1 ? 1 : currentPage - 1; // 이전페이지 유효성검사: 현재페이지가 1이면 이전페이지도 1이라고 한다 아니면(:) -1
																	// 해주기
			int nextPage = currentPage == paging.getFinalPageNo() ? currentPage : currentPage + 1; // 다음페이지 유효성 검사

			paging.setPageNo(currentPage);
			paging.setPrevPageNo(prevPage);
			paging.setNextPageNo(nextPage);

			System.out.println(paging);

			request.setAttribute("params", paging);
			request.setAttribute("word", word);

		} catch (Exception e) {
			throw e;
		}

		String viewPage = "jsp/eduList/seoul.jsp";
		
		RequestDispatcher dp = request.getRequestDispatcher(viewPage); // 디스패쳐
		dp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
