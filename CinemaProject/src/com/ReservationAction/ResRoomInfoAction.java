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
 * Servlet implementation class ResRoomInfoAction
 */
@WebServlet("/Main/ResRoomInfo")
public class ResRoomInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResRoomInfoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String moive_num = request.getParameter("movie_num");
		String room_num = request.getParameter("room_num");
		String theater_time_num = request.getParameter("theater_time_num");
		
		ReservationDAO dao = ReservationDAO.getInstance();
		ReservationBean rb = dao.CallRoom(moive_num,room_num,theater_time_num);
		
			JSONObject obj = new JSONObject();	
			obj.put("ResSeat",rb.getResSeat());
			obj.put("style", rb.getStyle());
			obj.put("seat_x", rb.getSeat_x());
			obj.put("seat_y", rb.getSeat_y());
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
