package com.tjoeun.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AjaxInsert
 */
@WebServlet("/AjaxInsert")
public class AjaxInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AjaxInsert 서블릿이 GET 방식으로 요청됨");
		// System.out.println(request.getParameter("name"));
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AjaxInsert 서블릿이 POST 방식으로 요청됨");
		// request.setCharacterEncoding("UTF-8");
		// System.out.println(request.getParameter("name"));
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ajaxInsert 서블릿 actionDo 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		

		int result;
		
		try {
		// inser.jsp의 데이터들 
			String name = request.getParameter("name").trim();
			int age = Integer.parseInt(request.getParameter("age").trim());
			String gender = request.getParameter("gender").trim();
			String email = request.getParameter("email").trim();
			
			// 넘겨받은 데이터 vo에 저장
			AjaxVO vo = new AjaxVO(name, age, gender, email);
			
			result = new AjaxDAO().insert(vo);
			
		} catch ( Exception e ) {
			result = -1;
		}

		System.out.println(result);
		
		// write() 인수 : 문자열 only
		// 공백 붙여서 문자열 만들어 리턴
		response.getWriter().write(result);
	}

}
