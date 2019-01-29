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

public class M_ManagerDAO {
	public static M_ManagerDAO instance;

	public static M_ManagerDAO getInstance() {
		if (instance == null) {
			instance = new M_ManagerDAO();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	// �깉 �쁺�솕 �엯�젰
	public void MovieInsert(MovieBean bean) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into movie values(movie_seq.nextval,?,?,?,?,?,?,?,?,?,0,0)";
			ps = con.prepareStatement(sql);
			ps.setString(1, bean.getSubject());
			ps.setString(2, bean.getGenre());
			ps.setString(3, bean.getDirector());
			ps.setString(4, bean.getActor());
			ps.setString(5, bean.getSummary().replaceAll(System.getProperty("line.separator"), "<br>")); // System.getProperty("line.separator")
			ps.setDate(6, bean.getRel_date());
			ps.setInt(7, bean.getAge_res());
			ps.setString(8, bean.getPlaytime());
			ps.setString(9, bean.getImg());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
	}

	// �닔�젙�븷 �쁺�솕 由ъ뒪�듃 蹂닿린
	public ArrayList<MovieBean> MovieCallList(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
//			String sql = "select * from movie order by rel_date desc";
//			String sql = "select * from (select rownum rn, aa.* from (select * from movie order by rel_date desc) aa) where rn >="
//					+ startRow + " and rn <=" + endRow;
			String sql = "select * from (select rownum rn, aa.* from (select * from movie mv, "
					+ "(select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and max_date>=sysdate order by rel_date desc) aa) where rn >="
					+ startRow + " and rn <=" + endRow;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNum(rs.getInt("movie_num"));
				bean.setSubject(rs.getString("subject"));
				bean.setGenre(rs.getString("genre"));
				bean.setDirector(rs.getString("director"));
				bean.setActor(rs.getString("actor"));
				bean.setSummary(rs.getString("summary").replaceAll("<br>", " "));
				bean.setRel_date(rs.getDate("rel_date"));
				bean.setAge_res(rs.getInt("age_res"));
				bean.setPlaytime(rs.getString("playtime"));
				bean.setImg(rs.getString("img"));
				bean.setRel_rating(rs.getDouble("rel_rating"));
				bean.setOn_rating(rs.getDouble("on_rating"));
				arr.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, ps, rs);
		}
		return arr;
	}

	// �럹�씠吏� - �쁺�솕 媛��닔 移댁슫�듃
	public int MovieListCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
//			String sql = "select count(*) cnt from movie";
			String sql = "select count(*) cnt from movie mv, (select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and max_date>=sysdate order by rel_date desc";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return count;
	}

	// �닔�젙�븷 �쁺�솕 �뜲�씠�꽣 遺덈윭�삤湲�
	public MovieBean MovieCallData(int movienum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MovieBean bean = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from movie where movie_num=" + movienum;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				bean = new MovieBean();
				bean.setActor(rs.getString("actor"));
				bean.setAge_res(rs.getInt("age_res"));
				bean.setDirector(rs.getString("director"));
				bean.setGenre(rs.getString("genre"));
				bean.setImg(rs.getString("img"));
				bean.setNum(rs.getInt("movie_num"));
				bean.setPlaytime(rs.getString("playtime"));
				bean.setRel_rating(rs.getDouble("rel_rating"));
				bean.setOn_rating(rs.getDouble("on_rating"));
				bean.setRel_date(rs.getDate("rel_date"));
				bean.setSubject(rs.getString("subject"));
				bean.setSummary(rs.getString("summary").replaceAll("<br>", "\r\n"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return bean;
	}

	// �쁺�솕 �닔�젙 �엯�젰�븯湲�
	public void MovieUpdate(MovieBean bean) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "update movie set subject=?, genre=?, director=?, actor=?, summary=?, rel_date=?, age_res=?, playtime=?, img=? where movie_num=? ";
			ps = con.prepareStatement(sql);
			ps.setString(1, bean.getSubject());
			ps.setString(2, bean.getGenre());
			ps.setString(3, bean.getDirector());
			ps.setString(4, bean.getActor());
			ps.setString(5, bean.getSummary().replaceAll(System.getProperty("line.separator"), "<br>"));
			ps.setDate(6, bean.getRel_date());
			ps.setInt(7, bean.getAge_res());
			ps.setString(8, bean.getPlaytime());
			ps.setString(9, bean.getImg());
			ps.setInt(10, bean.getNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
	}

	// �쁺�솕 �궘�젣�븯湲�
	public void MovieDelete(int movienum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from movie where movie_num=" + movienum;
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st);
		}
	}

	// 吏��굹媛� �쁺�솕 由ъ뒪�듃 蹂닿린 + 寃��깋
	public ArrayList<MovieBean> MovieCallHistory(String word, String field) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		String sql = null;
		try {
			con = getConnection();
			if (word == "")
				sql = "select * from movie mv, (select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and mv.rel_date<=sysdate and max_date<=sysdate order by rel_date desc";
			else
				sql = "select * from movie mv, (select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and mv.rel_date<=sysdate and max_date<=sysdate and mv."
						+ field + " like '%" + word + "%' order by rel_date desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNum(rs.getInt("movie_num"));
				bean.setSubject(rs.getString("subject"));
				bean.setGenre(rs.getString("genre"));
				bean.setDirector(rs.getString("director"));
				bean.setActor(rs.getString("actor"));
				bean.setSummary(rs.getString("summary").replaceAll("<br>", " "));
				bean.setRel_date(rs.getDate("rel_date"));
				bean.setAge_res(rs.getInt("age_res"));
				bean.setPlaytime(rs.getString("playtime"));
				bean.setImg(rs.getString("img"));
				bean.setRel_rating(rs.getDouble("rel_rating"));
				bean.setOn_rating(rs.getDouble("on_rating"));
				arr.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, ps, rs);
		}
		return arr;
	}

	// 상영 예정작 리스트
	public ArrayList<MovieBean> MovieCallPlan(int startRow, int endRow) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from (select rownum rn, aa.* from (select * from movie mv, theater_date td where mv.movie_num = td.movienum(+) and outdate is null order by rel_date) aa) where rn >="
					+ startRow + " and rn <=" + endRow;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MovieBean bean = new MovieBean();
				bean.setNum(rs.getInt("movie_num"));
				bean.setSubject(rs.getString("subject"));
				bean.setGenre(rs.getString("genre"));
				bean.setDirector(rs.getString("director"));
				bean.setActor(rs.getString("actor"));
				bean.setSummary(rs.getString("summary").replaceAll("<br>", " "));
				bean.setRel_date(rs.getDate("rel_date"));
				bean.setAge_res(rs.getInt("age_res"));
				bean.setPlaytime(rs.getString("playtime"));
				bean.setImg(rs.getString("img"));
				bean.setRel_rating(rs.getDouble("rel_rating"));
				bean.setOn_rating(rs.getDouble("on_rating"));
				arr.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, ps, rs);
		}
		return arr;
	}

	// 상영 예정작 갯수
	public int MoviePlanCount() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from movie mv, theater_date td where mv.movie_num = td.movienum(+) and outdate is null";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return count;
	}

	// closeCon
	private void closeCon(Connection con, PreparedStatement ps) {
		try {
			if (con != null)
				con.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeCon(Connection con, Statement st) {
		try {
			if (con != null)
				con.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String MovieCallData2(int movienum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String interArr = "";
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from movie where movie_num=" + movienum;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				interArr = rs.getString("genre");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return interArr;
	}
}
