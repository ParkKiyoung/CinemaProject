package com.ManagerAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.M_ManagerDAO;
import com.Movie.MovieBean;

/**
 * Servlet implementation class MovieDataAction
 */
@WebServlet("/Manager/M_CallHistory.do")
public class M_CallHistoryAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public M_CallHistoryAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		M_ManagerDAO dao = M_ManagerDAO.getInstance();

		String word = request.getParameter("word") == null ? "" : request.getParameter("word");
		String field = request.getParameter("field") == null ? "" : request.getParameter("field");

		ArrayList<MovieBean> arr = dao.MovieCallHistory(word, field);

		request.setAttribute("movies", arr);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("Manager_Main.jsp?page=Manager_Movie&source1=Movie_History");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
