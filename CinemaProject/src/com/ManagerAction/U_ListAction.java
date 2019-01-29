package com.ManagerAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.U_ManagerDAO;
import com.Member.MemberBean;

/**
 * Servlet implementation class U_ListAction
 */
@WebServlet("/Manager/U_Update_List.do")
public class U_ListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_ListAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberBean mb = new MemberBean();
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		
		String pageNum = request.getParameter("pageNum")==null?"1":request.getParameter("pageNum");
		int currentPage = Integer.parseInt(pageNum);
		int pageSize = 10;
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		ArrayList<MemberBean> arr = dao.U_Update_List(startRow,endRow);
		int count = dao.U_Update_List_Count();
		
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
		request.setAttribute("arr", arr);
		
		RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_User&source5=User_Update");
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
