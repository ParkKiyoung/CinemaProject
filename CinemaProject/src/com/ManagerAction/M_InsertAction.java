package com.ManagerAction;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.M_ManagerDAO;
import com.Movie.MovieBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class WriteAction
 */
@WebServlet("/Manager/MovieInsert.do")
public class M_InsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public M_InsertAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ServletContext context = request.getServletContext();
		String filePath = context.getRealPath("/Movie/PosterIMG");
		int size = 10 * 1024 * 1024;
		MovieBean bean = new MovieBean();

		MultipartRequest multi = new MultipartRequest(request, filePath, size, "UTF-8", new DefaultFileRenamePolicy());

		bean.setSubject(multi.getParameter("M_Title"));
		bean.setDirector(multi.getParameter("M_Director"));
		bean.setActor(multi.getParameter("M_Actor"));
		// sql�� �� Date �������� ��ȯ
		String Rel_date = multi.getParameter("M_Rel_Date");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(Rel_date);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sql_rel_date = new java.sql.Date(date.getTime());
		bean.setRel_date(sql_rel_date);

		bean.setPlaytime(multi.getParameter("M_Playtime"));
		bean.setAge_res(Integer.parseInt(multi.getParameter("M_Age")));
		bean.setSummary(multi.getParameter("M_Story"));

		String checklist[] = multi.getParameterValues("M_Genre");
		String Genre = "";
		for (int i = 0; i < checklist.length; i++) {
			Genre += checklist[i];
			if (i != checklist.length - 1) {
				Genre += ", ";
			}
		}
		bean.setGenre(Genre);

//		String orgName = multi.getOriginalFileName("M_Img"); // ���� �����̸�. ����� �ʿ����.
		String filename = multi.getFilesystemName("M_Img");
		bean.setImg(filename);
		M_ManagerDAO dao = M_ManagerDAO.getInstance();
		dao.MovieInsert(bean);

		response.sendRedirect("Manager_Main.jsp?page=Manager_Movie&source1=Movie_Insert");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}