package com.ManagerAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.R_ManagerDAO;

/**
 * Servlet implementation class R_RoomUpdateAction
 */
@WebServlet("/Manager/RoomUpdateAction")
public class R_RoomUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_RoomUpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String Room_Num = request.getParameter("ROOM_NUM");
		String blockSeat = request.getParameter("blockSeat");
		
		String blockArr[] = blockSeat.split(" ");
		String seat = "";
		
		for(int i = 0 ; i<blockArr.length ;i++) {
			if(i==0) {
				seat += blockArr[i];
			}else {
				seat += ","+blockArr[i];
			}
		}
		
		
		String changedSeat_x = request.getParameter("changedSeat_x");
		String changedSeat_y = request.getParameter("changedSeat_y");
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		dao.updateRoomInfo(Room_Num,seat,changedSeat_x,changedSeat_y);
		
		response.sendRedirect("Manager_Main.jsp?page=Manager_Theater");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
