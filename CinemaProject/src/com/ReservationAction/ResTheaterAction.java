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
 * Servlet implementation class ResTheaterAction
 */
@WebServlet("/Main/PlaceCallback")
public class ResTheaterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResTheaterAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		ReservationDAO dao = ReservationDAO.getInstance();
		
		ArrayList<ReservationBean> arr = dao.CallCity();
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("city", arr.get(i).getCity());
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
		ReservationDAO dao = ReservationDAO.getInstance();
		String city = request.getParameter("city");
		
		ArrayList<ReservationBean> arr = dao.CallTheater(city);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size() ; i++) {
			JSONObject obj = new JSONObject();
			obj.put("theaternum", arr.get(i).getTheaterNum());
			obj.put("theatername", arr.get(i).getTheatherName());
			jarr.add(obj);
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

}
