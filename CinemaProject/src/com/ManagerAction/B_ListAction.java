package com.ManagerAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardBean;
import com.Manager.B_ManagerDAO;

/**
 * Servlet implementation class B_ListAction
 */
@WebServlet("/Manager/boardList")
public class B_ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public B_ListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category")==null?"all":request.getParameter("category");
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		//검색카테고리,검색어
		
		//게시판에 보여질 글에대한 페이징
		int nowPage = Integer.parseInt(pageNum);//게시판 현재페이지
		int pageSize = 10;//한페이지에 나오는 레코드수
		int endRow = nowPage*pageSize;//마지막 레코드
		int startRow = ((nowPage-1)*pageSize)+1;//시작 레코드
		
		B_ManagerDAO dao = new B_ManagerDAO();
		ArrayList<BoardBean> arr = dao.boardList(startRow,endRow,category);
		
		int totcount = dao.boardTotCount();
//		int subjectcount = dao.boardSubjectCount();
//		int goodcount = dao.boardGoodCount();
//		int badcount = dao.boardBadCount();
		
		// 이전  1 2 3 다음 에 대한 페이징
		int totpage = totcount/pageSize+(totcount%pageSize==0?0:1);
		int blockpage = 3;
		int startpage = (((nowPage-1)/blockpage)*blockpage)+1;
		int endpage = (startpage+blockpage)-1;
		
		if(endpage>totpage) endpage = totpage;
		
		request.setAttribute("arr", arr);
		request.setAttribute("totcount", totcount);
		request.setAttribute("category", category);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totpage", totpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("blockpage", blockpage);
		
		response.setContentType("text/html;charset=utf-8");
		RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_Board&source3=Board_Delete");
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
