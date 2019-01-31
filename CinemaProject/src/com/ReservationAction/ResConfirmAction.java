package com.ReservationAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Reservation.ReservationBean;
import com.Reservation.ReservationDAO;

/**
 * Servlet implementation class ResConfirmAction
 */
@WebServlet("/Main/ResConfirm")
public class ResConfirmAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResConfirmAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String membernum = request.getParameter("membernum");
		String seat[] = request.getParameter("seatNo").split(" ");
		String adultCount = request.getParameter("adultCount")==null?"0":request.getParameter("adultCount");;
		String youthCount = request.getParameter("youthCount")==null?"0":request.getParameter("youthCount");
		String ttnum = request.getParameter("ttnum").trim();
		String movienum = request.getParameter("movienum");
		String roomnum = request.getParameter("roomnum");
		String inputSeat = "";
		for(int i = 0 ; i<seat.length ; i++) {
			if(i==0) {
				inputSeat+=seat[i];	
			}else {
				inputSeat+=","+seat[i];
			}
		}
		
		ReservationBean rb = new ReservationBean();
		rb.setMemberNum(Integer.parseInt(membernum));
		rb.setResSeat(inputSeat);
		rb.setAdultCount(Integer.parseInt(adultCount));
		rb.setYouthCount(Integer.parseInt(youthCount));
		rb.setTheaterTimeNum(Integer.parseInt(ttnum));
		rb.setRoomNum(Integer.parseInt(roomnum));
		rb.setMovieNum(Integer.parseInt(movienum));
		
		ReservationDAO dao = ReservationDAO.getInstance();
		dao.InsertReservation(rb);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('예약이 완료 되었습니다.');location.href='../Main/MainHome.jsp'</script>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
