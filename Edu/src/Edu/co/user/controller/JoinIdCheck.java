package Edu.co.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Edu.co.user.dao.UserDao;
import Edu.co.user.vo.UserVo;
import net.sf.json.JSONArray;


@WebServlet("/IdCheck.do")
public class JoinIdCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public JoinIdCheck() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		UserDao dao = new UserDao();	
		UserVo vo = new UserVo();
		
		vo.setId(request.getParameter("id"));
		
		List<UserVo> list = dao.checkId(vo); 
		
		JSONArray jAry = new JSONArray();	
			
		for(UserVo m : list) {
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
