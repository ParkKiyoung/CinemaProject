package com.BoardAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardDAO;
import com.Board.BoardReplyBean;

/**
 * Servlet implementation class BoardReplyListAction
 */
@WebServlet("/Board/boardReplyList")
public class BoardReplyListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum"); 
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		
		//�Խ��ǿ� ������ �ۿ����� ����¡
				int nowPage = Integer.parseInt(pageNum);//�Խ��� ����������
				int pageSize = 5;//���������� ������ ���ڵ��
				int endRow = nowPage*pageSize;//������ ���ڵ�
				int startRow = ((nowPage-1)*pageSize)+1;//���� ���ڵ�
				
				BoardDAO dao = new BoardDAO();
				ArrayList<BoardReplyBean> arr = dao.boardReplyList(startRow, endRow,boardnum);
				int totcount = dao.boardReplyCount(boardnum);
				
				// ����  1 2 3 ���� �� ���� ����¡
				int totpage = totcount/pageSize+(totcount%pageSize==0?0:1);
				int blockpage = 3;
				int startpage = (((nowPage-1)/blockpage)*blockpage)+1;
				int endpage = (startpage+blockpage)-1;
				
				if(endpage>totpage) endpage = totpage;
				
				request.setAttribute("arr", arr);
				request.setAttribute("totcount", totcount);
				request.setAttribute("boardnum", boardnum);
				
				request.setAttribute("pageNum", pageNum);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("totpage", totpage);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage", endpage);
				request.setAttribute("nowPage", nowPage);
				request.setAttribute("blockpage", blockpage);
				
				response.setContentType("text/html;charset=utf-8");
				RequestDispatcher rd = request.getRequestDispatcher("BoardReplyList_Result.jsp");
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
