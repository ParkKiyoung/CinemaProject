package com.MovieAction;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Grade.GradeBean;
import com.Movie.MovieDAO;

/**
 * Servlet implementation class GradeInsertAction
 */
@WebServlet("/Movie/GradeInsert")
public class GradeInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GradeInsertAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		int movienum = Integer.parseInt(request.getParameter("movienum"));
		int membernum = Integer.parseInt(request.getParameter("membernum"));
		MovieDAO dao = MovieDAO.getInstance();
		int check = dao.GradeCheck(movienum, membernum);
		if (check == 1) {// 중복평가
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert('이미 평가한 영화입니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.flush();
			return;
		}
		Double score = Double.parseDouble(request.getParameter("score"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		String reply = request.getParameter("reply");
		String ip = request.getRemoteAddr();
		GradeBean bean = new GradeBean();
		bean.setMovie_Num(movienum);
		bean.setMember_Num(membernum);
		bean.setScore(score);
		bean.setReply(reply);
		bean.setIp(ip);
		dao.GradeInsert(bean);
		dao.UpdateRating(movienum, flag);
		response.sendRedirect("../Movie/View?movienum=" + movienum);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
