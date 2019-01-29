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

import com.Board.BoardBean;
import com.Grade.GradeBean;
import com.Manager.U_ManagerDAO;
import com.Member.MemberBean;

/**
 * Servlet implementation class U_Id_InfoAction
 */
@WebServlet("/Manager/U_Id_Info.do")
public class U_Id_InfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_Id_InfoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String U_NUM = request.getParameter("U_NUM");
		
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		
		MemberBean memBean = dao.UserActDetail(U_NUM);
		
		int Gcnt = dao.U_Id_Info_Grade(U_NUM);
		int Bcnt = dao.U_Id_Info_Board(U_NUM);
		Double Avg_Score = dao.U_Info_Avg_Score(U_NUM);
		int Res_Count = dao.U_Info_Res_Count(U_NUM);
		
		request.setAttribute("memBean", memBean);
		request.setAttribute("Gcnt", Gcnt);
		request.setAttribute("Bcnt", Bcnt);
		request.setAttribute("Res_Count", Res_Count);
		request.setAttribute("Avg_Score", Avg_Score);
		
		RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_User&source5=User_Id_Info");
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
