package com.GradeAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Grade.GradeBean;
import com.Grade.GradeDAO;
import com.Movie.MovieBean;

/**
 * Servlet implementation class SelectedAction
 */
@WebServlet("/Grade/MovieAfter.do")
public class MovieAfterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieAfterAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String SelectedValue = request.getParameter("SelectedValue");
		
		GradeDAO dao = GradeDAO.getInstance();
		ArrayList<MovieBean> OMarr = new ArrayList<MovieBean>();
		ArrayList<MovieBean> RMarr = new ArrayList<MovieBean>();
		OMarr = dao.OSMovieSubject();
		RMarr = dao.RelMovieSubject();
		
		// 페이징
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10;
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		ArrayList<GradeBean> SArr = dao.SelectedAfterGrade(SelectedValue,startRow,endRow);
		int count = dao.SelectedAfterGradeCount(SelectedValue);
		
		int totPage = count/pageSize+(count%pageSize==0?0:1);
		int blockPage = 5;
		int startPage = ((currentPage-1)/blockPage)*blockPage+1;
		int endPage = startPage+blockPage-1;
		
		if(endPage > totPage) endPage = totPage;
		
		request.setAttribute("totPage", totPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockPage", blockPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);

		request.setAttribute("count", count);
		request.setAttribute("OnScreenTitle", OMarr);
		request.setAttribute("RelScreenTitle", RMarr);
		request.setAttribute("MovieAfterList", SArr);
		request.setAttribute("SelectedValue", SelectedValue);
		request.setAttribute("signal", 1);
		
		RequestDispatcher rd = request.getRequestDispatcher("GradePage.jsp");
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
