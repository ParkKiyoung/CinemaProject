package com.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReservationDAO {
	public static ReservationDAO instance;

	public static ReservationDAO getInstance() {

		if (instance == null) {

			instance = new ReservationDAO();
		}
		return instance;
	}
	public Connection getConnection() throws Exception{

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
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

	public ArrayList<ReservationBean> CallCity() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select distinct(city) from theater order by city";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setCity(rs.getString("city"));
				arr.add(rb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	public ArrayList<ReservationBean> CallTheater(String city) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from theater  where city='"+city+"' order by theatername";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setTheatherName(rs.getString("theatername"));
				rb.setTheaterNum(rs.getInt("theater_num"));
				arr.add(rb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	public ArrayList<ReservationBean> searchMovie(String theater, String resDate) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select room_name,room_num,m.subject,ontime,m.movie_num,tt.theater_time_num, rank()over (partition by m.movie_num order by ontime) "+ 
					"from room r, theater t, theater_date td, theater_time tt, movie m "+ 
					"where r.theaternum=t.theater_num and tt.movienum=m.movie_num and tt.roomnum=r.room_num and td.movienum=m.movie_num and "+ 
					"t.theater_num="+theater+" and to_date('"+resDate+"','yyyy-mm-dd')<td.outdate ";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setTheaterTimeNum(rs.getInt("theater_time_num"));
				rb.setRoom_Name(rs.getString("ROOM_NAME"));//�󿵰� �̸�
				rb.setRoomNum(rs.getInt("ROOM_NUM"));//�󿵰� ��ȣ
				rb.setMovieSubject(rs.getString("subject"));
				rb.setOnTime(rs.getString("ontime"));
				rb.setMovieNum(rs.getInt("movie_num"));
				rb.setShowingMovieCnt(MovieCnt(rs.getString("movie_num"), theater, resDate));
				arr.add(rb);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	public int MovieCnt(String movie_num, String theater, String resDate) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			con = getConnection();
			String sql ="select m.movie_num,count(*)  "+ 
					"from room r, theater t, theater_date td, theater_time tt, movie m "+ 
					"where r.theaternum=t.theater_num and tt.movienum=m.movie_num and tt.roomnum=r.room_num and td.movienum=m.movie_num and "+ 
					"t.theater_num="+theater+"and to_date('"+resDate+"','yyyy-mm-dd')<td.outdate and movie_num="+movie_num
					+ "group by m.movie_num";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				 cnt = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return cnt;
	}
	public ReservationBean CallRoom(String moive_num, String room_num, String theater_time_num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ReservationBean rb = new ReservationBean();
		try {
			con = getConnection();
			String sql = "select * from room r, theater_time tt where " + 
					"r.room_num=tt.roomnum and r.room_num="+room_num+" and tt.theater_time_num="+theater_time_num;
			st = con.createStatement();
			
			rs = st.executeQuery(sql);
			if(rs.next()) {
				rb.setResSeat(CallReservatedSeat(moive_num, room_num, theater_time_num));
				rb.setStyle(rs.getString("style"));
				rb.setSeat_x(rs.getInt("seat_x"));
				rb.setSeat_y(rs.getInt("seat_y"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return rb;
	}
	public void InsertReservation(ReservationBean rb) {//�������� ���
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into booking values(booking_seq.nextval,?,?,?,?,?,?,sysdate,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, rb.getMemberNum());
			ps.setString(2, rb.getResSeat());
			ps.setInt(3, rb.getAdultCount());
			ps.setInt(4, rb.getYouthCount());
			int price=(12000*rb.getAdultCount())+(9000*rb.getYouthCount());
			ps.setInt(5, price);
			ps.setInt(6, rb.getTheaterTimeNum());
			ps.setInt(7, rb.getMovieNum());
			ps.setInt(8, rb.getRoomNum());
			ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	public String CallReservatedSeat(String movie_num, String room_num, String theater_time_num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		String ResSeat ="";
		try {
			con = getConnection();
			String sql = "select seat from booking where movienum="+movie_num+" and roomnum="+room_num+" and theater_time_num="+theater_time_num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setResSeat(rs.getString("seat"));
				arr.add(rb);
			}
			for(int i =0 ; i<arr.size(); i++) {
				if(i==0) {
					ResSeat +=arr.get(i).getResSeat();
				}else {
					ResSeat +=","+arr.get(i).getResSeat();
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return ResSeat;
	}
}
