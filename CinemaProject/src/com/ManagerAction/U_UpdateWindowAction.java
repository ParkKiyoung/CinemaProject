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

import com.Manager.U_ManagerDAO;
import com.Member.MemberBean;

/**
 * Servlet implementation class U_UpdateWindowAction
 */
@WebServlet("/Manager/U_Update_window.do")
public class U_UpdateWindowAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_UpdateWindowAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int U_Num = Integer.parseInt(request.getParameter("U_Num"));
		
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		MemberBean mb = dao.U_Update_window(U_Num);
		
		request.setAttribute("mb", mb);
		
		RequestDispatcher rd = request.getRequestDispatcher("User_Update_window.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
