package com.LoginAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Member.MemberBean;
import com.Member.MemberDAO;

/**
 * Servlet implementation class DeleteAction
 */
@WebServlet("/Login/DeleteAction")
public class DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//Cancel Reservation
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		int bookingNum = Integer.parseInt(request.getParameter("bookingNum"));
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean mb = new MemberBean();
		
		mb.setBookingNum(bookingNum);
		mb.setNum(num);
		mb.setPassword(password);
		
		int res = dao.ResCancel(mb);
		PrintWriter out = response.getWriter();
		out.println(res);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Exit member
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean mb = new MemberBean();
		
		mb.setNum(num);
		mb.setPassword(password);
		
		int res = dao.MemberDelete(mb);
		PrintWriter out = response.getWriter();
		out.println(res);
	}

}
