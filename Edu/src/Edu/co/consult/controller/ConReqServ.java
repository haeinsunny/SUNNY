package Edu.co.consult.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.consult.dao.ConDao;
import Edu.co.consult.vo.ConVo;
import Edu.co.consult.vo.ReqVo;
import Edu.co.edu.dao.EduDao;
import Edu.co.edu.vo.EduVo;
import Edu.co.edu.vo.PageVo;

@WebServlet("/ConReq.do")
public class ConReqServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConReqServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ConDao dao = new ConDao();
		ReqVo vo = new ReqVo();

		String name = request.getParameter("name");
		String type = request.getParameter("type");

		System.out.println(name);
		System.out.println(type);

		// 전체 조회 페이징 처리

		String pageNum = request.getParameter("pageNum"); // jsp페이지로부터 값 받아오기
		dao = new ConDao(); // 위에서 dao.selectAll()하고 디비 닫아버림 다시 conn해야해서
		PageVo paging = new PageVo();
		pageNum = pageNum == null ? "1" : pageNum; // page가 null이면 1이라고 해주고 아니면 pageNum을 넣는다
		paging.setPageNo(Integer.parseInt(pageNum));
		paging.setPageSize(10);
		


		if (request.getParameter("type").equals("user")) {
			paging.setTotalCount(dao.getRCount(name)); // 리스트의 총 데이터갯수 (기준으로 페이지나눔)
			int startRow = paging.getPageNo() * 10 - 9; // 리스트 조회 (한페이지당 10개씩)
			int endRow = paging.getPageNo() * 10;
			dao = new ConDao();
			ArrayList<ReqVo> list = dao.selectPage(name, startRow, endRow);
			request.setAttribute("rlist", list);
			int currentPage = Integer.parseInt(pageNum); // 넘어온 pageNum은 string이므로 파싱하기
			int prevPage = currentPage == 1 ? 1 : currentPage - 1; // 이전페이지 유효성검사: 현재페이지가 1이면 이전페이지도 1이라고 한다 아니면(:) -1																// 해주기
			int nextPage = currentPage == paging.getFinalPageNo() ? currentPage : currentPage + 1; // 다음페이지 유효성 검사
			paging.setPageNo(currentPage);
			paging.setPrevPageNo(prevPage);
			paging.setNextPageNo(nextPage);
			System.out.println(paging);
			request.setAttribute("params", paging);
			String viewPage = "jsp/consult/conReq.jsp"; 
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		} else if (request.getParameter("type").equals("client")){
			
			paging.setTotalCount(dao.getcCount(name)); // 리스트의 총 데이터갯수 (기준으로 페이지나눔)
			int startRow = paging.getPageNo() * 10 - 9; // 리스트 조회 (한페이지당 10개씩)
			int endRow = paging.getPageNo() * 10;
			dao = new ConDao();
			ArrayList<ConVo> list = dao.selectCPage(name, startRow, endRow);
			request.setAttribute("clist", list);
			int currentPage = Integer.parseInt(pageNum); // 넘어온 pageNum은 string이므로 파싱하기
			int prevPage = currentPage == 1 ? 1 : currentPage - 1; // 이전페이지 유효성검사: 현재페이지가 1이면 이전페이지도 1이라고 한다 아니면(:) -1
																	// 해주기
			int nextPage = currentPage == paging.getFinalPageNo() ? currentPage : currentPage + 1; // 다음페이지 유효성 검사
			paging.setPageNo(currentPage);
			paging.setPrevPageNo(prevPage);
			paging.setNextPageNo(nextPage);
			System.out.println(paging);
			request.setAttribute("params", paging);
			String viewPage = "jsp/consult/conList.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
			
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
