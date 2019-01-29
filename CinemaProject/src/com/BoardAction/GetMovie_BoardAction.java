package com.BoardAction;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.Board.BoardBean;
import com.Board.BoardDAO;

/**
 * Servlet implementation class GetMovie_BoardAction
 */
@WebServlet("/Main/GetMovie_BoardAction")
public class GetMovie_BoardAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMovie_BoardAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String category = request.getParameter("category");
		BoardDAO dao = BoardDAO.getInstance();
				
		if(category.equals("movie")) {
			ArrayList<BoardBean> arr = dao.Category_Board(category);
			JSONArray jarr = new JSONArray();
			for(int i = 0 ; i<arr.size() ; i++) {
				JSONObject obj = new JSONObject();
				obj.put("board_num", arr.get(i).getNum());
				obj.put("subject", arr.get(i).getSubject());
				obj.put("good", arr.get(i).getGood());
				jarr.add(obj);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jarr.toString());
		}
		else if(category.equals("actor")) {
			ArrayList<BoardBean> arr = dao.Category_Board(category);
			JSONArray jarr = new JSONArray();
			for(int i = 0 ; i<arr.size() ; i++) {
				JSONObject obj = new JSONObject();
				obj.put("board_num", arr.get(i).getNum());
				obj.put("subject", arr.get(i).getSubject());
				obj.put("good", arr.get(i).getGood());
				jarr.add(obj);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jarr.toString());
		}
		else if(category.equals("genre")) {
			ArrayList<BoardBean> arr = dao.Category_Board(category);
			JSONArray jarr = new JSONArray();
			for(int i = 0 ; i<arr.size() ; i++) {
				JSONObject obj = new JSONObject();
				obj.put("board_num", arr.get(i).getNum());
				obj.put("subject", arr.get(i).getSubject());
				obj.put("good", arr.get(i).getGood());
				jarr.add(obj);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jarr.toString());
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
