package com.ManagerAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.R_ManagerDAO;
import com.Reservation.ReservationBean;

/**
 * Servlet implementation class R_CreateNewAction
 */
@WebServlet("/Manager/CreateNew")
public class R_CreateNewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_CreateNewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
		    
			String cityName = request.getParameter("city");
			String theaterName = request.getParameter("theaterName");
			
			
			R_ManagerDAO dao = R_ManagerDAO.getInstance();
			int theaterNum = dao.createNewTheater(cityName, theaterName);
			
			
			request.setAttribute("cityName", cityName);
			request.setAttribute("theaterName", theaterName);
			request.setAttribute("theaterNum", theaterNum);
			
			response.setContentType("text/html;charset=utf-8");
			RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_Theater");
			rd.forward(request, response);
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		int theaterNum = Integer.parseInt(request.getParameter("theaterNum"));//Theater Number
		int roomCountIdx = Integer.parseInt(request.getParameter("roomCountIdx"));//Count of Room
		ArrayList<String> roomArr = new ArrayList<>();
		ArrayList<String> seat_x = new ArrayList<>();
		ArrayList<String> seat_y = new ArrayList<>();
		for(int i =0; i < roomCountIdx ;i++) {
			roomArr.add(request.getParameter("theaterRoom"+i));
			seat_x.add(request.getParameter("seat_x"+i));
			seat_y.add(request.getParameter("seat_y"+i));
		}
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		dao.createNewRoom(theaterNum,seat_x,seat_y,roomArr);
		
		response.sendRedirect("Manager_Main.jsp?page=Manager_Theater");
		
		
		
		
		
	}

}
