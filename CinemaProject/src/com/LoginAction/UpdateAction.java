package com.LoginAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Member.MemberBean;
import com.Member.MemberDAO;

/**
 * Servlet implementation class UpdateAction
 */
@WebServlet("/Login/MemberUpdate")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String pnum = request.getParameter("pnum");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String zipcode = request.getParameter("zipcode");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String[] checklist = request.getParameterValues("checklist");
		String[] inter = {"","",""};
		
		
		
		MemberBean mb = new MemberBean();
		mb.setAddr1(addr1);
		mb.setAddr2(addr2);
		mb.setEmail(email);
		mb.setGender(gender);
		mb.setName(name);
		mb.setPassword(password);
		mb.setPhone(phone);
		mb.setPnum(pnum);
		mb.setUserid(userid);
		mb.setZipcode(zipcode);
		
		if(checklist!=null) {
			for(int i=0; i<checklist.length;i++) {
				 inter[i] = checklist[i];
			}
			mb.setInter1(inter[0]);
			mb.setInter2(inter[1]);
			mb.setInter3(inter[2]);
		}
	
		
		MemberDAO dao = MemberDAO.getInstance();
		
		HttpSession session = request.getSession();
		session.invalidate();
		HttpSession session1 = request.getSession();
		session1.setAttribute("mb", mb);
		
		dao.MemberUpdate(mb);
		response.sendRedirect("../Main/MainHome.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
