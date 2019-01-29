package com.MovieAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Movie.MovieBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class MainMovieAction
 */
@WebServlet("/Main/MainMovieAction")
public class MainMovieAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMovieAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//1. 현재 상영작 2.개봉예정작 3.예매순 4. 평점순 5. 박스오피스
		request.setCharacterEncoding("utf-8");
		String field = request.getParameter("field");
		MovieDAO dao = MovieDAO.getInstance();
		ArrayList<MovieBean> arr = dao.MainMovieTitle(field);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("img", arr.get(i).getImg());
			obj.put("subject", arr.get(i).getSubject());
			obj.put("on_rating", arr.get(i).getOn_rating());
			obj.put("rel_rating", arr.get(i).getRel_rating());
			obj.put("rel_date", arr.get(i).getRel_date().toString());
			obj.put("HomeMovie_num", arr.get(i).getNum());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
