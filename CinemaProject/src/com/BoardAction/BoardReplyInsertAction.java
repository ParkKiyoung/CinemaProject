package com.BoardAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardDAO;
import com.Board.BoardReplyBean;

/**
 * Servlet implementation class BoardReplyInsertAction
 */
@WebServlet("/Board/replyInsert")
public class BoardReplyInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		String userid = request.getParameter("userid");
		String reply = request.getParameter("reply");
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
		BoardReplyBean brb = new BoardReplyBean();
		brb.setBoardnum(num);
		brb.setMembernum(membernum);
		brb.setContent(reply);
		brb.setIp(ip);
		brb.setUserid(userid);
		BoardDAO dao = new BoardDAO();
		dao.bardReplyInsert(brb);
		
		response.sendRedirect("boardView?num="+num);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
