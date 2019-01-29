package com.ManagerAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Manager.U_Age_Bean;
import com.Manager.U_Gender_Bean;
import com.Manager.U_Genre_Bean;
import com.Manager.U_ManagerDAO;

/**
 * Servlet implementation class U_GraphAction
 */
@WebServlet("/Manager/U_GraphAction.do")
public class U_GraphAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public U_GraphAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String Graph = request.getParameter("Graph");
		U_ManagerDAO dao = U_ManagerDAO.getInstance();
		U_Age_Bean uab = dao.U_Age_Graph();
		U_Gender_Bean ugb = dao.U_Gender_Graph();
		U_Genre_Bean U_Genre_Bean = dao.U_Genre_Graph();
		
		Double male = ugb.getMale();
		Double female = ugb.getFemale();
		Double T_Gender = male+female;
		Double male_percent = (male/T_Gender)*100;
		Double female_percent = (female/T_Gender)*100;
		
		Double T_Genre = U_Genre_Bean.getT_Genre();
		
		Double sport = (U_Genre_Bean.getSport()/T_Genre)*100;
		Double crime = (U_Genre_Bean.getCrime()/T_Genre)*100;
		Double drama = (U_Genre_Bean.getDrama()/T_Genre)*100;
		Double comedy = (U_Genre_Bean.getComedy()/T_Genre)*100;
		Double romance = (U_Genre_Bean.getRomance()/T_Genre)*100;
		Double thriller = (U_Genre_Bean.getThriller()/T_Genre)*100;
		Double romance_comedy = (U_Genre_Bean.getRomance_comedy()/T_Genre)*100;
		Double military = (U_Genre_Bean.getMilitary()/T_Genre)*100;
		Double family = (U_Genre_Bean.getFamily()/T_Genre)*100;
		Double fantasy = (U_Genre_Bean.getFantasy()/T_Genre)*100;
		Double action = (U_Genre_Bean.getAction()/T_Genre)*100;
		Double sf = (U_Genre_Bean.getSf()/T_Genre)*100;
		Double anime = (U_Genre_Bean.getAnime()/T_Genre)*100;
		Double documentary = (U_Genre_Bean.getDocumentary()/T_Genre)*100;
		
		request.setAttribute("uab", uab);
		
		request.setAttribute("male", male_percent);
		request.setAttribute("female", female_percent);
		
		request.setAttribute("sport", sport);
		request.setAttribute("crime", crime);
		request.setAttribute("drama", drama);
		request.setAttribute("comedy", comedy);
		request.setAttribute("romance", romance);
		request.setAttribute("thriller", thriller);
		request.setAttribute("romance_comedy", romance_comedy);
		request.setAttribute("military", military);
		request.setAttribute("family", family);
		request.setAttribute("fantasy", fantasy);
		request.setAttribute("action", action);
		request.setAttribute("sf", sf);
		request.setAttribute("anime", anime);
		request.setAttribute("documentary", documentary);
		

		if(Graph.equals("U_Graph_Genre")) {
			RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_User&source5=User_Graph&Graph=U_Graph_Genre");
			rd.forward(request, response);
		}
		else if(Graph.equals("U_Graph_Age")) {
			RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_User&source5=User_Graph&Graph=U_Graph_Age");
			rd.forward(request, response);
		}
		else {			
			RequestDispatcher rd = request.getRequestDispatcher("Manager_Main.jsp?page=Manager_User&source5=User_Graph&Graph=U_Graph_Gender");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
