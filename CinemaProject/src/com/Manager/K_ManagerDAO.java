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

public class K_ManagerDAO {
	private static K_ManagerDAO instance;
	public static K_ManagerDAO getInstance() {
		if(instance==null) {
			instance=new K_ManagerDAO();
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
	
	//게시?��?��?��
	public void bookingDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from booking where booking_num = "+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeCon(con, st);
		}
		
	}
	//게시?��총개?��
	public int bookingTotCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from booking";
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
	
	public String member_userid(int membernum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String userid = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where member_num = "+membernum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				userid = rs.getString("userid");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return userid;
	}
	public String theater_time_ontime(int theatertimenum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String ontime = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from theater_time where theater_time_num = "+theatertimenum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				ontime += rs.getString("moviedate").substring(0, 10);
				ontime += "/";
				ontime += rs.getString("ontime");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return ontime;
	}
	
	public String movie_subject(int movienum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String subject = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from movie where movie_num = "+movienum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				subject = rs.getString("subject");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return subject;
	}
	
	public String room_roomname(int roomnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String roomname = "";
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select * from room r, theater t where r.theaternum = t.theater_num and r.room_num = "+roomnum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				roomname += rs.getString("theatername");
			}
			roomname += "/";
			sql = "select * from room where room_num = "+roomnum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				roomname += rs.getString("room_name");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return roomname;
	}
	
	public ArrayList<BookingBean> bookingList(int startRow,int endRow){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BookingBean> arr = new ArrayList<>();
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
					sql = "select * from"
							+ "(select rownum rn, aa.* from(select * from booking order by booking_num desc)aa)"
							+ " where rn >="+startRow+" and rn <="+endRow;
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BookingBean bb = new BookingBean();
				bb.setBooking_num(rs.getInt("booking_num"));
				bb.setMember_userid(member_userid(rs.getInt("membernum")));
				bb.setSeat(rs.getString("seat"));
				bb.setAdult(rs.getInt("adult"));
				bb.setYouth(rs.getInt("youth"));
				bb.setPrice(rs.getString("price"));
				bb.setTheater_time_ontime(theater_time_ontime(rs.getInt("theater_time_num")));
				bb.setBookingdate(rs.getString("bookingdate"));
				bb.setMovie_subject(movie_subject(rs.getInt("movienum")));
				bb.setRoom_roomname(room_roomname(rs.getInt("roomnum")));
				arr.add(bb);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
		
	}
	
}
