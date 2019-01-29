package com.MovieAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Grade.GradeBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class GradeDeleteAction
 */
@WebServlet("/Movie/GradeDelete")
public class GradeDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GradeDeleteAction() {
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
		int flag = Integer.parseInt(request.getParameter("flag"));
		int gradenum = Integer.parseInt(request.getParameter("gradenum"));
		MovieDAO dao = MovieDAO.getInstance();
		dao.GradeDelete(gradenum);
		dao.UpdateRating(movienum, flag);
		response.sendRedirect("../Movie/View?movienum=" + movienum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
