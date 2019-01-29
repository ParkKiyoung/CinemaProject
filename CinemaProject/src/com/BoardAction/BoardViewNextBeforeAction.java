package com.BoardAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardDAO;

/**
 * Servlet implementation class BoardViewNextAction
 */
@WebServlet("/Board/viewNextBefore")
public class BoardViewNextBeforeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewNextBeforeAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		int checknum = Integer.parseInt(request.getParameter("checknum"));
		
		BoardDAO dao = new BoardDAO();
		//board_num 은 db에서 받아온 다음글에대한 번호
		int board_num = dao.boardViewNext(boardnum,checknum);
		if(board_num == -1) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('마지막페이지입니다.');location.href='boardView?num="+boardnum+"';</script>");
			return;
		}
		if(board_num == -2) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('처음페이지입니다.');location.href='boardView?num="+boardnum+"';</script>");
			return;
		}
		
		response.sendRedirect("boardView?num="+board_num);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
