package co.sunny.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.sunny.member.dao.MemberDao;
import co.sunny.member.vo.MemberVo;
import net.sf.json.JSONArray;


@WebServlet("/MemberIdCheck.do")
public class JoinIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JoinIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		MemberDao dao = new MemberDao();	
		MemberVo vo = new MemberVo();
		
		vo.setmId(request.getParameter("id"));
		
		List<MemberVo> list = dao.checkId(vo); 
		
		JSONArray jAry = new JSONArray();	
			
		for(MemberVo m : list) {
			jAry.add(m);
		}
		
		System.out.println(jAry);
		

		response.getWriter().append(JSONArray.fromObject(jAry).toString());
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
