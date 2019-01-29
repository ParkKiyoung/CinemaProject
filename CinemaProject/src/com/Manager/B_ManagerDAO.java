package com.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.Board.BoardBean;
import com.Board.BoardDAO;
import com.Grade.GradeBean;
import com.Member.MemberBean;
import com.Member.ZipcodeBEAN;

public class B_ManagerDAO {
	private static B_ManagerDAO instance;
	public static B_ManagerDAO getInstance() {
		if(instance==null) {
			instance=new B_ManagerDAO();
		}
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	// closeCon
	private void closeCon(Connection con, Statement st) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeCon(Connection con, PreparedStatement ps) {
		try {
			if(con!=null) con.close();
			if(ps!=null) ps.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeCon(Connection con, Statement st, ResultSet rs) {
		try {
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void closeCon(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
			if(st!=null) st.close();
			if(rs!=null) rs.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//게시판리스트
	public ArrayList<BoardBean> boardList(){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardBean> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from board";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				BoardDAO dao = new BoardDAO();
				bb.setNum(rs.getInt("board_num"));
				bb.setUserid(rs.getString("userid"));
				bb.setMembernum(rs.getInt("membernum"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setCreated(rs.getString("created"));
				bb.setGood(dao.goodCount(rs.getInt("board_num")));
				bb.setBad(dao.badCount(rs.getInt("board_num")));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setIp(rs.getString("ip"));
				bb.setUpdated(rs.getString("updated"));
				bb.setCategory(rs.getString("category"));
				bb.setSubjectcount(dao.commentCount(rs.getInt("board_num")));
				arr.add(bb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	//게시판삭제
	public void boardDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from board where board_num = "+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//게시판개수
	public int boardCount(String category) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
				if(category.equals("all")) {//占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뵛占쎌굲
					sql = "select count(*) cnt from board";
				}else {//占쎈쐻占쎈뼑占쎄땔占쎌굲占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뵛占쎌굲
					sql = "select count(*) cnt from board  where category = '"+category+"'";
				}
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return count;
	}
	//게시판총개수
	public int boardTotCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from board";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return count;
	}
	public ArrayList<BoardBean> boardList(int startRow,int endRow,String category){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardBean> arr = new ArrayList<>();
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
				if(category.equals("all")) {//占쎈쐻占쎈짗占쎌굲筌ｋ떣�쐻占쎈짗占쎌굲占쎈쐻占쎈뼣占쎌뵛占쎌굲
					sql = "select * from"
							+ "(select rownum rn, aa.* from(select * from board order by board_num desc)aa)"
							+ " where rn >="+startRow+" and rn <="+endRow;
				}else {//燁삳떣�쐻占쎈솊�ⓦ끉�굲 占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈짗占쎌굲 占쎈쐻占쏙옙占쎌뵛占쎌굲
					sql = "select * from"
							+ "(select rownum rn, aa.* from(select * from board where category = '"+category+"' order by board_num desc)aa)"
							+ " where rn >="+startRow+" and rn <="+endRow+"";
				}
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("board_num"));
				bb.setUserid(rs.getString("userid"));
				bb.setMembernum(rs.getInt("membernum"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setCreated(rs.getString("created"));
				bb.setGood(goodCount(rs.getInt("board_num")));
				bb.setBad(badCount(rs.getInt("board_num")));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setIp(rs.getString("ip"));
				bb.setUpdated(rs.getString("updated"));
				bb.setCategory(rs.getString("category"));
				bb.setSubjectcount(commentCount(rs.getInt("board_num")));
				arr.add(bb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
		
	}
	//각 게시글 당 추천 개수
		public int goodCount(int board_num) {
			Connection con  = null;
			Statement st = null;
			ResultSet rs = null;
			int goodCount=0;
			try {
				con = getConnection();
				String sql = "select sum(good) from board b, board_like bl where b.board_num = bl.boardnum and b.board_num = "+board_num+" group by good";
				st = con.createStatement();
				rs = st.executeQuery(sql);
				if(rs.next()) {
					goodCount = rs.getInt("sum(good)");
					return goodCount;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeCon(con, st, rs);
			}
			return goodCount;
		}
		
		//각 게시글 당 비추천 개수
			public int badCount(int board_num) {
				Connection con  = null;
				Statement st = null;
				ResultSet rs = null;
				int badCount=0;
				try {
					con = getConnection();
					String sql = "select sum(bad) from board b, board_like bl where b.board_num = bl.boardnum and b.board_num = "+board_num+" group by bad";
					st = con.createStatement();
					rs = st.executeQuery(sql);
					if(rs.next()) {
						badCount = rs.getInt("sum(bad)");
						return badCount;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					closeCon(con, st, rs);
				}
				return badCount;
			}
		//�젣紐⑷� �쁿�뿉 () �븞�뿉 �닽�옄
		public int commentCount(int board_num) {
			Connection con  = null;
			Statement st = null;
			ResultSet rs = null;
			int commentCount=0;
			try {
				con = getConnection();
				String sql = "select count(*) from board b, board_reply br where b.board_num=br.boardnum and b.board_num="+board_num;
				st = con.createStatement();
				rs = st.executeQuery(sql);
				if(rs.next()) {
					commentCount = rs.getInt("count(*)");
					return commentCount;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				closeCon(con, st, rs);
			}
			return commentCount;
		}
}
