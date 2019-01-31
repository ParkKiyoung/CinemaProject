package com.ReservationAction;

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

import com.Reservation.ReservationBean;
import com.Reservation.ReservationDAO;

/**
 * Servlet implementation class ResSearchMovieList
 */
@WebServlet("/Main/ResSearchMovieList")
public class ResSearchMovieList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResSearchMovieList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String theater = request.getParameter("theater");
		String resDate = request.getParameter("resDate");
		
		
		ReservationDAO dao = ReservationDAO.getInstance();
		ArrayList<ReservationBean> arr = dao.searchMovie(theater,resDate);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("movie_num", arr.get(i).getMovieNum());
			obj.put("theater_time_num", arr.get(i).getTheaterTimeNum());
			obj.put("room_name", arr.get(i).getRoom_Name());
			obj.put("room_num", arr.get(i).getRoomNum());
			obj.put("subject", arr.get(i).getMovieSubject());
			obj.put("ontime", arr.get(i).getOnTime());
			obj.put("seat_x", arr.get(i).getSeat_x());
			obj.put("seat_y", arr.get(i).getSeat_y());
			obj.put("moviecnt", arr.get(i).getShowingMovieCnt());
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
