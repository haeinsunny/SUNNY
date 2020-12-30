package Edu.co.edu.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.edu.api.EduRequest;
import Edu.co.edu.api.GetEduVo;
import Edu.co.edu.dao.EduDao;
import Edu.co.edu.vo.EduVo;
import Edu.co.edu.vo.PageVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/EduList.do")
public class EduListServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EduDao dao = new EduDao();

		try {
			// 서울 OpenAPI
			String result = EduRequest.GetEdu();

			JSONObject obj = JSONObject.fromObject(result);
			JSONObject obj1 = obj.getJSONObject("InstutBuildInfo");
			JSONArray arr = obj1.getJSONArray("row");
			
			//DELETE
//			dao = new EduDao();
//			int n = dao.delete();
//			System.out.println("총" + n + "건" + " 저장소가 정리 되었습니다.");

			// 가져온 JSON데이터를 ArrayList에 담기(DB에 넣기 위함)
			ArrayList<GetEduVo> elist = new ArrayList<GetEduVo>();
			GetEduVo vo = new GetEduVo();

			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);

				String id = json.getString("ID");
				int n = id.indexOf('.');
				vo.setID(id.substring(0, n));

				try {
					if (json.getString("ADDR") != "") {
						String address = json.getString("ADDR");
						int a = address.indexOf('구');
						String ad = address.substring(0, a);
						String addr = ad + "구";
						vo.setADDR(addr);
					} else {
						vo.setADDR(json.getString("ADDR"));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				vo.setNM(json.getString("NM"));
				vo.setTEL(json.getString("TEL"));

				elist.add(vo);
				
				// INSERT
				dao = new EduDao();
				n = dao.aMerge(elist);
				
			}			
			System.out.println(" OpenAPI가 저장 되었습니다.");
			

			// MERGE
			dao = new EduDao();
			int m = dao.smerge();
			System.out.println("총" + m + "건" + " 레코드가 병합 되었습니다.");

			// 전체 조회 페이징 처리
			String pageNum = request.getParameter("pageNum"); // jsp페이지로부터 값 받아오기
			dao = new EduDao(); // 위에서 dao.selectAll()하고 디비 닫아버림 다시 conn해야해서
			PageVo paging = new PageVo();
			pageNum = pageNum == null ? "1" : pageNum; // page가 null이면 1이라고 해주고 아니면 pageNum을 넣는다
			paging.setPageNo(Integer.parseInt(pageNum));
			paging.setPageSize(10);
			paging.setTotalCount(dao.getCount()); // 리스트의 총 데이터갯수 (기준으로 페이지나눔)

			int startRow = paging.getPageNo() * 10 - 9; // 리스트 조회 (한페이지당 10개씩)
			int endRow = paging.getPageNo() * 10;
			dao = new EduDao();
			ArrayList<EduVo> list = dao.selectAllPage(startRow, endRow);
			request.setAttribute("elist", list);

			int currentPage = Integer.parseInt(pageNum); // 넘어온 pageNum은 string이므로 파싱하기
			int prevPage = currentPage == 1 ? 1 : currentPage - 1; // 이전페이지 유효성검사: 현재페이지가 1이면 이전페이지도 1이라고 한다 아니면(:) -1
																	// 해주기
			int nextPage = currentPage == paging.getFinalPageNo() ? currentPage : currentPage + 1; // 다음페이지 유효성 검사

			paging.setPageNo(currentPage);
			paging.setPrevPageNo(prevPage);
			paging.setNextPageNo(nextPage);

			System.out.println(paging);

			request.setAttribute("params", paging);

		} catch (Exception e) {
			throw e;
		}

		String viewPage = "jsp/eduList/seoul.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
