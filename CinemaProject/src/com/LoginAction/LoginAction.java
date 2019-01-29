package com.LoginAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Member.MemberBean;
import com.Member.MemberDAO;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/Main/login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberDAO dao = MemberDAO.getInstance();
		String msg = "";
		
		//1占싱몌옙 占싸깍옙占쏙옙 2占싱몌옙 占쏙옙占싣뀐옙占� 3占싱몌옙 회占쏙옙占싣댐옙
		int flag = dao.MemberLogin(id,password);
		if(flag==1) {
			HttpSession session = request.getSession();
			MemberBean mb = dao.getMember(id);
			session.setAttribute("mb", mb);
			msg="1";
		}else if(flag==2) {
			msg ="비번이 틀렸습니다.";
		}else if(flag==3) {
			msg ="회원이 아닙니다.";
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(msg);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
