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

import com.Movie.MovieBean;
import com.Reservation.ReservationBean;

public class R_ManagerDAO {
	private static R_ManagerDAO instance;

	public static R_ManagerDAO getInstance() {
		if (instance == null) {
			instance = new R_ManagerDAO();
		}
		return instance;
	}

	private Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	// closeCon
	private void closeCon(Connection con, Statement st) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeCon(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeCon(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void closeCon(Connection con, PreparedStatement ps, Statement st, ResultSet rs) {
		try {
			if (ps != null)
				ps.close();
			if (con != null)
				con.close();
			if (st != null)
				st.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int createNewTheater(String cityName, String theaterName) {
		Connection con = null;
		PreparedStatement ps = null;
		Statement st = null;
		ResultSet rs = null;
		int theaterNum = 0;
		try {
			con = getConnection();
			String sql = "insert into theater values(theater_seq.nextval,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, cityName);
			ps.setString(2, theaterName);
			ps.executeUpdate();

			sql = "select * from theater where city='" + cityName + "' and theatername='" + theaterName + "'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				theaterNum = rs.getInt("theater_num");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
		return theaterNum;
	}

	public void createNewRoom(int theaterNum, ArrayList<String> seat_x, ArrayList<String> seat_y,ArrayList<String> roomArr) {
		Connection con = null;
		PreparedStatement ps = null;
		
		ArrayList<ReservationBean> inputRoomArr = new ArrayList<>();
		try {
			con = getConnection();
		for(int i =0 ; i<roomArr.size();i++) {
			String sql = "insert into room values(room_seq.nextval,?,'',?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, theaterNum);
			ps.setString(2, seat_x.get(i));
			ps.setString(3, seat_y.get(i));
			ps.setString(4, roomArr.get(i));
			ps.executeQuery();
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}

	}

	public ArrayList<ReservationBean> roomCallBack(int theaterNum) {
		Connection con = null;
		Statement st= null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from room where theaternum="+theaterNum+" order by room_name";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setRoom_Name(rs.getString("room_name"));
				rb.setRoomNum(rs.getInt("room_num"));
				arr.add(rb);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeCon(con, st, rs);
		}
		return arr;
	}

	public ReservationBean roomSeatCall(int roomNum) {
		Connection con = null;
		Statement st= null;
		ResultSet rs = null;
		ReservationBean rb = new ReservationBean();
		try {
			con = getConnection();
			String sql = "select * from room where room_Num="+roomNum;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				rb.setStyle(rs.getString("style"));
				rb.setSeat_x(rs.getInt("seat_x"));
				rb.setSeat_y(rs.getInt("seat_y"));		
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeCon(con, st, rs);
		}
		return rb;
	}

	public void updateRoomInfo(String room_Num, String blockSeat, String changedSeat_x, String changedSeat_y) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "update room set style=? , seat_x=? , seat_y=? where room_num=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, blockSeat);
			ps.setString(2, changedSeat_x);
			ps.setString(3, changedSeat_y);
			ps.setString(4, room_Num);
			ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}

	public ArrayList<MovieBean> SearchMovieSubject(String movieSubject) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from movie where subject like '%"+movieSubject+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setNum(rs.getInt("movie_num"));
				mb.setSubject(rs.getString("subject"));
				mb.setAge_res(rs.getInt("age_res"));
				mb.setRel_date(rs.getDate("rel_date"));
				mb.setPlaytime(rs.getString("playtime"));
				arr.add(mb);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}

	public void theater_dateInsert(String movie_num, String indate, String outdate) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into theater_date values(theater_date_seq.nextval,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, movie_num);
			ps.setString(2, indate);
			ps.setString(3, outdate);
			ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}

	public ArrayList<MovieBean> dateMovieList(String thisDate) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			String sql = "select * from theater_date td, movie m where to_date('"+thisDate+"','yyyy-mm-dd') BETWEEN indate and outdate and td.movienum=m.movie_num";
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setNum(rs.getInt("movie_num"));
				mb.setSubject(rs.getString("subject"));
				mb.setPlaytime(rs.getString("playtime"));
				arr.add(mb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}

	public void Theater_timeInsert(ArrayList<String> ontime, String movieDate, String roomnum, String movienum) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			for(int i = 0; i<ontime.size(); i++) {
			String sql = "insert into theater_time values(theater_time_seq.nextval,?,to_date(?,'yyyy-mm-dd'),?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, ontime.get(i));
			ps.setString(2, movieDate);
			ps.setString(3, roomnum);
			ps.setString(4, movienum);
			ps.executeQuery();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}

}
