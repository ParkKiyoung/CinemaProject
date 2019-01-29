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
 * Servlet implementation class MovieViewDataAction
 */
@WebServlet("/Manager/M_CallData.do")
public class M_CallDataAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_CallDataAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int movienum = Integer.parseInt(request.getParameter("movienum"));
		M_ManagerDAO dao = M_ManagerDAO.getInstance();
		
		MovieBean bean = dao.MovieCallData(movienum);
		String interArr = dao.MovieCallData2(movienum);
		request.setAttribute("dto", bean);
		request.setAttribute("arr", interArr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Movie_ReInput.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
