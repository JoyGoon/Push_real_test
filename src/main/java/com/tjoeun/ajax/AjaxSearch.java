package com.tjoeun.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjaxSearch")
public class AjaxSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AjaxSearch 서블릿이 GET 방식으로 요청됨");
		// System.out.println(request.getParameter("name"));
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AjaxSearch 서블릿이 POST 방식으로 요청됨");
		// request.setCharacterEncoding("UTF-8");
		// System.out.println(request.getParameter("name"));
		actionDo(request, response);
	}

	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ajaxsearch 서블릿 actionDo 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String name = request.getParameter("name");
		
		// ajax 방식
		// 요청한 곳으로 데이터 리턴
		
		// write() 메소드 인수는 반드시 문자열,
		// ajax로 서블릿 호출한 곳에는 responseText
		response.getWriter().write(getJSON(name));
	}
	
	private String getJSON(String name) {
		// 만약 null 넘어오면 공백 처리
		
		if ( name == null ) name = "";
		
		// 테이블 'ajax'에서 입력한 문자열이 name 필드에 포함된 데이터 얻어옴
		ArrayList<AjaxVO> list = new AjaxDAO().search(name);
		System.out.println(list);
		
		// 테이블 검색해 얻어온 데이터 json 형식 문자열로
		StringBuffer result = new StringBuffer();
		result.append("{\"result\": ["); // json 시작
		// 데이터의 개수만큼 반복하며 json 형태의 문자열을 만든다.
		for(AjaxVO vo : list) {
			result.append("[{\"value\": \"" + vo.getIdx() + "\"},");
			result.append("{\"value\": \"" + vo.getName().trim() + "\"},");
			result.append("{\"value\": \"" + vo.getAge() + "\"},");
			result.append("{\"value\": \"" + vo.getGender().trim() + "\"},");
			result.append("{\"value\": \"" + vo.getEmail().trim() + "\"}],");
		}
	result.append("]}"); // json 끝
		
		//	StringBuffer 타입 객체를 String 타입으로 리턴
		return result.toString();
	}
}
