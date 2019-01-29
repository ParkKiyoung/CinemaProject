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
import com.Board.BoardDAO;
import com.Movie.MovieBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class MainSearchAction
 */
@WebServlet("/Main/MainSearch")
public class MainSearchAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainSearchAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String word = request.getParameter("word");
		
		MovieDAO dao = new MovieDAO();
		ArrayList<MovieBean> arr1 = dao.MainSearchMovie(word);
		ArrayList<BoardBean> arr2 = dao.MainSearchBoard(word);
		request.setAttribute("arr1", arr1);
		request.setAttribute("arr2", arr2);
		request.setAttribute("word", word);
		response.setContentType("text/html;charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("../Main/MainSearchResult.jsp");
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
