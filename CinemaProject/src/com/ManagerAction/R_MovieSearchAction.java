package com.ManagerAction;

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

import com.Manager.R_ManagerDAO;
import com.Movie.MovieBean;

/**
 * Servlet implementation class R_MovieSearchAction
 */
@WebServlet("/Manager/SearchMovieInfo")
public class R_MovieSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_MovieSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String movieSubject = request.getParameter("movieSubject");
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		ArrayList<MovieBean> titleArr = dao.SearchMovieSubject(movieSubject);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<titleArr.size() ;  i++) {
			JSONObject obj = new JSONObject();
			obj.put("movie_num", titleArr.get(i).getNum());
			obj.put("movieSubject", titleArr.get(i).getSubject());
			obj.put("rel_date", titleArr.get(i).getRel_date().toString());
			obj.put("age", titleArr.get(i).getAge_res());
			obj.put("runtime", titleArr.get(i).getPlaytime());
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
		request.setCharacterEncoding("utf-8");
		String thisDate = request.getParameter("thisDate");
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		ArrayList<MovieBean> dateArr = dao.dateMovieList(thisDate);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<dateArr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("movie_num", dateArr.get(i).getNum());
			obj.put("MovieSubject", dateArr.get(i).getSubject());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
		
	}

}
