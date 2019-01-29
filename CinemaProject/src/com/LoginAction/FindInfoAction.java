package com.LoginAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Member.MemberBean;
import com.Member.MemberDAO;

/**
 * Servlet implementation class FindInfoAction
 */
@WebServlet("/Login/FindInfo")
public class FindInfoAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindInfoAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���̵� ã��
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String pnum = request.getParameter("pnum");
		String phone = request.getParameter("phone");
		
		MemberBean mb = new MemberBean();
		mb.setName(name);
		mb.setPnum(pnum);
		mb.setPhone(phone);
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberBean findMb = dao.FindId(mb);
		
		request.setAttribute("findMb", findMb);
		RequestDispatcher rd = request.getRequestDispatcher("FindInfo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��� ã��
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pnum = request.getParameter("pnum");
		String phone = request.getParameter("phone");
		
		MemberBean mb = new MemberBean();
		mb.setUserid(id);
		mb.setPnum(pnum);
		mb.setPhone(phone);
		
		MemberDAO dao = MemberDAO.getInstance();
		//ȸ�� �˻��Ǹ� 1 ��ȯ �ƴϸ� 0 ��ȯ
		MemberBean findPwd = dao.FindPwd(mb);
		
		request.setAttribute("findPwd", findPwd);
		RequestDispatcher rd = request.getRequestDispatcher("FindInfo.jsp");
		rd.forward(request, response);

	}

}
