package com.LoginAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Board.BoardBean;
import com.Board.BoardDAO;
import com.Grade.GradeBean;
import com.Grade.GradeDAO;
import com.Member.MemberDAO;
import com.Reservation.ReservationBean;

/**
 * Servlet implementation class ActivityAction
 */
@WebServlet("/Main/myActivity")
public class MyActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String num = request.getParameter("num");
		MemberDAO dao = MemberDAO.getInstance();
		
		int pageSize = 5;
		
		String bpageNum = request.getParameter("bpageNum")==null?"1":request.getParameter("bpageNum");
		String board_field = request.getParameter("board_field");
		String board_word = request.getParameter("board_word")==null?"":request.getParameter("board_word");
		int bcurrentPage = Integer.parseInt(bpageNum);
		
		int bstartRow = (bcurrentPage-1)*pageSize+1;
		int bendRow = bcurrentPage*pageSize;
		int bcnt = dao.MyBoardCount(num,board_field,board_word);//보드 갯수
		
		
		int btotPage = bcnt/pageSize+(bcnt%pageSize==0?0:1);//보드 토탈 페이지수
		
		
		int bblockPage=5;
		int bstartPage =((bcurrentPage-1)/bblockPage)*bblockPage+1;
		int bendPage = bstartPage+bblockPage-1;
		if(bendPage>btotPage)bendPage=btotPage;
		request.setAttribute("btotPage", btotPage);
		request.setAttribute("bstartPage", bstartPage);
		request.setAttribute("bendPage", bendPage);
		request.setAttribute("bcurrentPage", bcurrentPage);
		request.setAttribute("bblockPage", bblockPage);
		request.setAttribute("board_field", board_field);
		request.setAttribute("board_word", board_word);
		
		ArrayList<BoardBean> mov_arr = dao.MyBoardAction(num,board_field,board_word,bstartRow,bendRow);//보드 페이징
		//--------------------------------------------------------------------------------------
		
		String gpageNum = request.getParameter("gpageNum")==null?"1":request.getParameter("gpageNum");
		String movie_subject = request.getParameter("movie_subject")==null?"":request.getParameter("movie_subject");

		int gcurrentPage = Integer.parseInt(gpageNum);
//		int pageSize = 5; 페이지 사이즈 5개는 공유
		int gstartRow = (gcurrentPage-1)*pageSize+1;
		int gendRow = gcurrentPage*pageSize;
		
		int gcnt = dao.MyGradeCount(num,movie_subject);//그레이드 갯수
		int gtotPage = gcnt/pageSize+(gcnt%pageSize==0?0:1);//그레이드 토탈 페이지수
		
		int gblockPage=5;
		int gstartPage =((gcurrentPage-1)/gblockPage)*gblockPage+1;
		int gendPage = gstartPage+gblockPage-1;
		if(gendPage>gtotPage)gendPage=gtotPage;
		request.setAttribute("gtotPage", gtotPage);
		request.setAttribute("gstartPage", gstartPage);
		request.setAttribute("gendPage", gendPage);
		request.setAttribute("gcurrentPage", gcurrentPage);
		request.setAttribute("gblockPage", gblockPage);
		request.setAttribute("movie_subject", movie_subject);
	
		ArrayList<GradeBean> gra_arr = dao.MyGradeAction(num,movie_subject,gstartRow,gendRow);//그레이드 페이징
		
		//----------------------------------------------------------------------------------------------------
		
		String rpageNum = request.getParameter("bpageNum")==null?"1":request.getParameter("bpageNum");
		int rcurrentPage = Integer.parseInt(rpageNum);
		
		int rstartRow = (rcurrentPage-1)*pageSize+1;
		int rendRow = rcurrentPage*pageSize;
		int rcnt = dao.MyReservaionCount(num);//보드 갯수
		
		
		int rtotPage = rcnt/pageSize+(rcnt%pageSize==0?0:1);//보드 토탈 페이지수
		
		
		int rblockPage=5;
		int rstartPage =((rcurrentPage-1)/rblockPage)*rblockPage+1;
		int rendPage = rstartPage+rblockPage-1;
		if(rendPage>rtotPage)rendPage=rtotPage;
		request.setAttribute("rtotPage", rtotPage);
		request.setAttribute("rstartPage", rstartPage);
		request.setAttribute("rendPage", rendPage);
		request.setAttribute("rcurrentPage", rcurrentPage);
		request.setAttribute("rblockPage", rblockPage);
		
		ArrayList<ReservationBean> res_arr = dao.MyReservationAction(num,rstartRow,rendRow);
		
		
		
		//---------------------------------------------------------------------------------------------------
		request.setAttribute("rcnt", rcnt);
		request.setAttribute("bcnt", bcnt);
		request.setAttribute("gcnt", gcnt);
		request.setAttribute("res_arr", res_arr);
		request.setAttribute("mov_arr", mov_arr);
		request.setAttribute("gra_arr", gra_arr);
		RequestDispatcher rd = request.getRequestDispatcher("../Login/MemberInfo.jsp");
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
