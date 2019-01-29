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
import com.Reservation.ReservationBean;

/**
 * Servlet implementation class R_CreateRoomAction
 */
@WebServlet("/Manager/RoomCallBack")
public class R_CreateRoomAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_CreateRoomAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int theaterNum = Integer.parseInt(request.getParameter("theaterNum"));
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		ArrayList<ReservationBean> arr = dao.roomCallBack(theaterNum);
		
		JSONArray jarr = new JSONArray();
		for(int i = 0 ; i<arr.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("RoomName", arr.get(i).getRoom_Name());
			obj.put("RoomNum", arr.get(i).getRoomNum());
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
		int roomNum = Integer.parseInt(request.getParameter("roomNum"));
			R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		ReservationBean rb = dao.roomSeatCall(roomNum);
		
			JSONObject obj = new JSONObject();
			obj.put("r_blockSeat", rb.getStyle());
			obj.put("r_seat_x", rb.getSeat_x());
			obj.put("r_seat_y", rb.getSeat_y());

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println(obj.toString());
		
		
		
	}

}
