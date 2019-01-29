package com.BoardAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardBean;
import com.Board.BoardDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertAction
 */
@WebServlet("/Board/boardInsert")
public class BoardInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		String category = request.getParameter("category");//1 ��ȭ 2 �帣 3 ���
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String userid = request.getParameter("userid");
//		String img = request.getParameter("img");
//		//�α����� �ȵǾ������� ���� ���â
//		if(request.getParameter("membernum").equals("")) {
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('�α��� �� �̿����ּ���.');location.href='boardList';</script>");
//		}
		
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		InetAddress local = InetAddress.getLocalHost();
		String ip = local.getHostAddress();
		BoardBean bb = new BoardBean();
		bb.setCategory(category);
		bb.setSubject(subject);
		bb.setContent(content);
		bb.setUserid(userid);
		bb.setMembernum(membernum);
		bb.setIp(ip);
		
		BoardDAO dao = new BoardDAO();
		dao.boardInsert(bb);
		
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
