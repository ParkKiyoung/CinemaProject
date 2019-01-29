package com.GradeAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Grade.GradeBean;
import com.Grade.GradeDAO;
import com.Movie.MovieBean;

/**
 * Servlet implementation class MainOnScreenAction
 */
@WebServlet("/Main/MainOnScreenAction")
public class MainOnScreenAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainOnScreenAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//인기 영화의 평점
			request.setCharacterEncoding("utf-8");
			String movie_num = request.getParameter("movie_num");
			GradeDAO dao = GradeDAO.getInstance();
			
			MovieBean mb = dao.MainOnScreenSubject(movie_num);
			ArrayList<GradeBean> arr = dao.MainOnScreenGrade(movie_num);
			
			JSONArray jarr = new JSONArray();
			for(int i = 0 ; i<arr.size() ; i++) {
				JSONObject obj = new JSONObject();
				obj.put("movie_num", movie_num);
				obj.put("reply", arr.get(i).getReply());
				obj.put("score", arr.get(i).getScore());
				obj.put("img", mb.getImg());
				obj.put("subject", mb.getSubject());
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
		//영화 랭킹
		request.setCharacterEncoding("utf-8");
		GradeDAO dao = GradeDAO.getInstance();
		
		ArrayList<MovieBean> arr = dao.getMovieRanking();

		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("movie_num", arr.get(i).getNum());
			obj.put("img", arr.get(i).getImg());
			obj.put("subject", arr.get(i).getSubject());
			obj.put("score", arr.get(i).getOn_rating());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

}
