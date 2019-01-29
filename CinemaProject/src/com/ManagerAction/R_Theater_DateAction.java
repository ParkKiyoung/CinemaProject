package com.ManagerAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.R_ManagerDAO;

/**
 * Servlet implementation class R_Theater_DateAction
 */
@WebServlet("/Manager/insertMovieDate")
public class R_Theater_DateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public R_Theater_DateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String movie_num = request.getParameter("movie_num");
		String indate = request.getParameter("indate");
		String outdate = request.getParameter("outdate");
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		
		dao.theater_dateInsert(movie_num,indate,outdate);
			
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('정상등록되었습니다.');location.href='Manager_Main.jsp?page=Manager_Theater&source6=Theater_Control_Temp';</script>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int showCountIdx = Integer.parseInt(request.getParameter("ShowCountIdx"));
		String roomnum = request.getParameter("roomList");
		String movienum = request.getParameter("movieList");
		String movieDate = request.getParameter("ShowDate");
		
		ArrayList<String> ontime = new ArrayList<>();
		
		for(int i = 0 ; i<showCountIdx ; i++) {
			ontime.add(request.getParameter("Showcount"+i));
		}
		
		R_ManagerDAO dao = R_ManagerDAO.getInstance();
		dao.Theater_timeInsert(ontime,movieDate,roomnum,movienum);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('정상등록 되었습니다.');location.href='Manager_Main.jsp?page=Manager_Theater&source6=Theater_Control_Temp';</script>");
		
		
		
	}

}
