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
@WebServlet("/Manager/M_CallList.do")
public class M_CallListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public M_CallListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		M_ManagerDAO dao = M_ManagerDAO.getInstance();
		
		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 5;
		int startRow = ((currentPage - 1) * pageSize) + 1; // 2page ->6
		int endRow = currentPage * pageSize;
		
		ArrayList<MovieBean> arr = dao.MovieCallList(startRow, endRow);
		int count = dao.MovieListCount();
		
		int totpage = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int blockpage = 5; // 
		int startpage = ((currentPage - 1) / blockpage) * blockpage + 1; // 4
		int endpage = startpage + blockpage - 1;
		
		if (endpage > totpage)
			endpage = totpage;
		
		request.setAttribute("totpage", totpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("blockpage", blockpage);

		request.setAttribute("count", count);
		
		request.setAttribute("movies", arr);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_Movie&source1=Movie_Update");
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
