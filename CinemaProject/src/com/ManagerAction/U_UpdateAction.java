package com.ManagerAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.U_ManagerDAO;
import com.Member.MemberBean;

/**
 * Servlet implementation class U_UpdateAction
 */
@WebServlet("/Manager/U_UpdateAction.do")
public class U_UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_UpdateAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int U_Num = Integer.parseInt(request.getParameter("U_Num"));
		String U_Id = request.getParameter("U_Id");
		String U_Pwd = request.getParameter("U_Pwd");
		String U_Name = request.getParameter("U_Name");
		String U_Pnum = request.getParameter("U_Pnum");
		String U_Phone = request.getParameter("U_Phone");
		String U_Gender = request.getParameter("U_Gender");
		String U_Email = request.getParameter("U_Email");
		String U_Zip = request.getParameter("U_Zip");
		String U_Addr1 = request.getParameter("U_Addr1");
		String U_Addr2 = request.getParameter("U_Addr2");
		String U_Inter1 = request.getParameter("U_Genre1");
		String U_Inter2 = request.getParameter("U_Genre2");
		String U_Inter3 = request.getParameter("U_Genre3");
		
		MemberBean mb = new MemberBean();
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		
		mb.setUserid(U_Id);
		mb.setPassword(U_Pwd);
		mb.setName(U_Name);
		mb.setPnum(U_Pnum);
		mb.setPhone(U_Phone);
		mb.setGender(U_Gender);
		mb.setEmail(U_Email);
		mb.setZipcode(U_Zip);
		mb.setAddr1(U_Addr1);
		mb.setAddr2(U_Addr2);
		mb.setInter1(U_Inter1);
		mb.setInter2(U_Inter2);
		mb.setInter3(U_Inter3);
		
		dao.U_Update_Go(mb, U_Num);
		
		response.setContentType("text/html; charset=utf-8");
	    PrintWriter out = response.getWriter();
	    out.println("<script>");
	    out.println("opener.parent.location='U_Update_List.do'");
	    out.println("opener.parent.location.reload();");
	    out.println("window.opener = self;");
	    out.println("self.close();");
	    out.println("</script>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
