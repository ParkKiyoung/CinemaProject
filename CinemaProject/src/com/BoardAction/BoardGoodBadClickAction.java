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
 * Servlet implementation class BoardGoodBadClickAction
 */
@WebServlet("/Board/goodbadClick")
public class BoardGoodBadClickAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardGoodBadClickAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		if(request.getParameter("membernum").equals("")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 후 이용하세요.');location.href='../Main/MainLoginTemp.jsp';</script>");
			return;
		}
		int checknum = Integer.parseInt(request.getParameter("checknum"));
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		BoardDAO dao = new BoardDAO();
		int check = dao.boardGoodBadCheck(membernum,boardnum);
		if(check == 1) {
			dao.boardGoodBadClick(checknum,boardnum,membernum);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 추천/비추천을 하셨습니다.');location.href='boardList';</script>");
			return;
		}
		
		response.sendRedirect("boardList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
