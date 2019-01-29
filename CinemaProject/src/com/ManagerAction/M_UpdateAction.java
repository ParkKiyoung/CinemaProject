package com.ManagerAction;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class M_UpdateAction
 */
@WebServlet("/Manager/MovieUpdate.do")
public class M_UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public M_UpdateAction() {
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

		ServletContext context = request.getServletContext();
		String filePath = context.getRealPath("/Movie/PosterIMG");

		int size = 10 * 1024 * 1024;
		MovieBean bean = new MovieBean();

		MultipartRequest multi = new MultipartRequest(request, filePath, size, "UTF-8", new DefaultFileRenamePolicy());
		bean.setNum(Integer.parseInt(multi.getParameter("movienum")));

		bean.setSubject(multi.getParameter("M_Title"));
		bean.setDirector(multi.getParameter("M_Director"));
		bean.setActor(multi.getParameter("M_Actor"));
		// sql에 쓸 Date 형식으로 변환
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

//		String orgName = multi.getOriginalFileName("M_Img"); // 파일 원래이름. 현재는 필요없음.
		String filename = multi.getFilesystemName("M_Img");
		if (filename == null) {
			filename = multi.getParameter("M_OriImg");
		}
		bean.setImg(filename);

		M_ManagerDAO dao = M_ManagerDAO.getInstance();
		dao.MovieUpdate(bean);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("opener.parent.location='M_CallList.do'");
		out.println("opener.parent.location.reload();");
		out.println("window.opener = self;");
		out.println("self.close();");
		out.println("</script>");
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
