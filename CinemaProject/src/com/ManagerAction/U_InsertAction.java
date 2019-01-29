package com.ManagerAction;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.U_ManagerDAO;
import com.Member.MemberBean;

/**
 * Servlet implementation class U_InsertAction
 */
@WebServlet("/Manager/U_InsertAction.do")
public class U_InsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_InsertAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String U_Id = request.getParameter("U_Id");
		String U_Pwd = request.getParameter("U_Pwd_Confirm");
		String U_Name = request.getParameter("U_Name");
		String U_Pnum = request.getParameter("U_Pnum");
		String U_Phone = request.getParameter("U_Phone");
		String U_Gender = request.getParameter("U_Gender");
		String U_Email = request.getParameter("U_Email")+request.getParameter("E_Addr")+request.getParameter("W_Email");
		String U_Zip = request.getParameter("U_Zip1")+request.getParameter("U_Zip2");
		String U_Addr1 = request.getParameter("U_Addr1");
		String U_Addr2 = request.getParameter("U_Addr2");
		String[] U_Cb = request.getParameterValues("U_Cb");
		String[] Checklist = {"","",""};
		MemberBean mb = new MemberBean();
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
		if(U_Cb != null) {
			for(int i=0;i<U_Cb.length;i++) {
				Checklist[i] = U_Cb[i];
			}
			mb.setInter1(Checklist[0]);
			mb.setInter2(Checklist[1]);
			mb.setInter3(Checklist[2]);
		}
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		dao.U_Insert(mb);
		response.sendRedirect("Manager_Main.jsp?page=Manager_User&source5=User_Insert");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
