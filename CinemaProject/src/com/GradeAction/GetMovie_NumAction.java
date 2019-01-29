package com.GradeAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.Grade.GradeDAO;
import com.Movie.MovieBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class GetMovie_NumAction
 */
@WebServlet("/Main/GetMovie_NumAction")
public class GetMovie_NumAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMovie_NumAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		GradeDAO dao = GradeDAO.getInstance();
		
		int GradeShowingNum = dao.getGradeShowingNum();//������ ���� ���� ��ȭ
		int GradeWaitingNum = dao.getGradeWaitingNum();//�̰��� ���� ������ȭ
		int ManyGradeWatingNum = dao.getManyGradeWatingNum();//�̰��� �� ���� ��ȭ
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		obj.put("GradeShowingNum", GradeShowingNum);
		obj.put("GradeWatingNum", GradeWaitingNum);
		obj.put("ManyGradeWatingNum", ManyGradeWatingNum);
		out.println(obj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
