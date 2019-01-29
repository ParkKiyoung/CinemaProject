package com.MovieAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardBean;
import com.Movie.MovieBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class MainResultAction
 */
@WebServlet("/Main/MoreBoard")
public class MoreBoardAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MoreBoardAction() {
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
		MovieDAO dao = MovieDAO.getInstance();

		String pageNum = request.getParameter("pageNum") == null ? "1" : request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10;
		int startRow = ((currentPage - 1) * pageSize) + 1; // 2page ->6
		int endRow = currentPage * pageSize;

		String word = request.getParameter("word") == null ? "" : request.getParameter("word");

		ArrayList<BoardBean> arr = dao.BoardSearchList(word, startRow, endRow);
		int count = dao.BoardSearchCount(word);
		request.setAttribute("arr", arr);

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
		request.setAttribute("word", word);
		
		
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("MainSearchBoard.jsp");
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
