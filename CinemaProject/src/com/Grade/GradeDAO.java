package com.Grade;

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

public class GradeDAO {
	private static GradeDAO instance;
	public static GradeDAO getInstance() {
		if(instance==null) {
			instance=new GradeDAO();
		}
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}
	// �쁺�솕�쓽 媛쒕큺 �쟾 �룊�젏 遺덈윭�삤湲�
	public ArrayList<GradeBean> SelectedBeforeGrade(String SelectedValue, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<GradeBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			if(SelectedValue!=null) {
				sql = "select * from (select rownum rn,aa.* from "
						+ "(select * from (select mv.playtime,mv.rel_date,mv.genre,mv.director,mv.actor,mv.on_rating,mv.rel_rating,mv.img,"
						+ "g.grade_num,g.score,g.reply,mb.userid,mv.subject,g.grade_date "
						+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
						+ "and g.m_num = mv.movie_num and mv.rel_date > g.grade_date "
						+ "and mv.subject = '" + SelectedValue + "' order by g.grade_num desc ) "
						+ "order by grade_num desc) aa) where rn >= ? and rn <= ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, startRow);
				ps.setInt(2, endRow);
				rs = ps.executeQuery();
			}	else {
				sql = "select * from (select rownum rn,aa.* from "
						+ "(select * from (select mv.playtime,mv.rel_date,mv.genre,mv.director,mv.actor,mv.on_rating,mv.rel_rating,mv.img,"
						+ "g.grade_num,g.score,g.reply,mb.userid,mv.subject,g.grade_date "
						+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
						+ "and g.m_num = mv.movie_num and mv.rel_date > g.grade_date " 
						+ "order by g.grade_num desc ) "
						+ "order by grade_num desc) aa) where rn >= ? and rn <= ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, startRow);
				ps.setInt(2, endRow);
				rs = ps.executeQuery();
			}
			while(rs.next()) {
				GradeBean gb = new GradeBean();
				gb.setGenre(rs.getString("genre"));
				gb.setDirector(rs.getString("director"));
				gb.setActor(rs.getString("actor"));
				gb.setOn_Rating(rs.getString("on_rating"));
				gb.setRel_Rating(rs.getString("rel_rating"));
				gb.setImg(rs.getString("img"));
				gb.setG_Num(rs.getInt("grade_num"));
				gb.setScore(rs.getDouble("score"));
				gb.setReply(rs.getString("reply"));
				gb.setUserId(rs.getString("userid"));
				gb.setMovieTitle(rs.getString("subject"));
				gb.setGrade_Date(rs.getString("grade_date"));
				gb.setRel_date(rs.getString("rel_date"));
				gb.setPlaytime(rs.getString("playtime"));
				arr.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
		return arr;
	}
	// �쁺�솕�쓽 媛쒕큺 �썑 �룊�젏 遺덈윭�삤湲�
	public ArrayList<GradeBean> SelectedAfterGrade(String SelectedValue, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<GradeBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			if(SelectedValue!=null) {
				sql = "select * from (select rownum rn,aa.* from "
						+ "(select * from (select mv.playtime,mv.rel_date,mv.genre,mv.director,mv.actor,mv.on_rating,mv.rel_rating,mv.img,"
						+ "g.grade_num,g.score,g.reply,mb.userid,mv.subject,g.grade_date "
						+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
						+ "and g.m_num = mv.movie_num and mv.rel_date <= g.grade_date "
						+ "and mv.subject = '" + SelectedValue + "' order by g.grade_num desc ) "
						+ "order by grade_num desc) aa) where rn >= ? and rn <= ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, startRow);
				ps.setInt(2, endRow);
				rs = ps.executeQuery();
			} else {
				sql = "select * from (select rownum rn,aa.* from "
						+ "(select * from (select mv.playtime,mv.rel_date,mv.genre,mv.director,mv.actor,mv.on_rating,mv.rel_rating,mv.img,"
						+ "g.grade_num,g.score,g.reply,mb.userid,mv.subject,g.grade_date "
						+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
						+ "and g.m_num = mv.movie_num and mv.rel_date <= g.grade_date " 
						+ "order by g.grade_num desc ) "
						+ "order by grade_num desc) aa) where rn >= ? and rn <= ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, startRow);
				ps.setInt(2, endRow);
				rs = ps.executeQuery();
			}
			while(rs.next()) {
				GradeBean gb = new GradeBean();
				gb.setGenre(rs.getString("genre"));
				gb.setDirector(rs.getString("director"));
				gb.setActor(rs.getString("actor"));
				gb.setOn_Rating(rs.getString("on_rating"));
				gb.setRel_Rating(rs.getString("rel_rating"));
				gb.setImg(rs.getString("img"));
				gb.setG_Num(rs.getInt("grade_num"));
				gb.setScore(rs.getDouble("score"));
				gb.setReply(rs.getString("reply"));
				gb.setUserId(rs.getString("userid"));
				gb.setMovieTitle(rs.getString("subject"));
				gb.setGrade_Date(rs.getString("grade_date"));
				gb.setRel_date(rs.getString("rel_date"));
				gb.setPlaytime(rs.getString("playtime"));
				arr.add(gb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
		return arr;
	}
	// �쁺�솕 媛쒕큺 �쟾 �룊�젏 �뙎湲� 移댁슫�듃
	public int SelectedBeforeGradeCount(String SelectedValue) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			if(SelectedValue != null) {
				sql = "select count(g.grade_num) "
								+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
								+ "and g.m_num = mv.movie_num and mv.rel_date > g.grade_date and mv.subject = '"+SelectedValue+"' "
								+ "order by g.grade_num desc";
			} else {
				sql = "select count(g.grade_num) "
						+ "from grade g, member mb, movie mv "
						+ "where g.membernum = mb.member_num and g.m_num = mv.movie_num and mv.rel_date > g.grade_date "
						+ "order by g.grade_num desc";
			}
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return count;
	}
	// �쁺�솕 媛쒕큺�썑 �룊�젏 �뙎湲� 移댁슫�듃
	public int SelectedAfterGradeCount(String SelectedValue) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			if(SelectedValue !=null) {
				sql = "select count(g.grade_num) "
								+ "from grade g, member mb, movie mv where g.membernum = mb.member_num "
								+ "and g.m_num = mv.movie_num and mv.rel_date <= g.grade_date and mv.subject = '"+SelectedValue+"' "
								+ "order by g.grade_num desc";
			} else {
				sql = "select count(g.grade_num) "
						+ "from grade g, member mb, movie mv "
						+ "where g.membernum = mb.member_num and g.m_num = mv.movie_num and mv.rel_date <= g.grade_date "
						+ "order by g.grade_num desc";
			}
			rs = st.executeQuery(sql);
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return count;
	}
	// �쁺�솕 �쁽�옱 �긽�쁺�옉 select option �씫�뼱�삤湲�
	public ArrayList<MovieBean> OSMovieSubject() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MovieBean> OMarr = null;
		try {
			OMarr = new ArrayList();
			con = getConnection();
			st = con.createStatement();
			sql = "select subject,rel_date from movie where rel_date <= sysdate";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setSubject(rs.getString("subject"));
				mb.setRel_date(rs.getDate("rel_date"));
				OMarr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return OMarr;
	}
	// �쁺�솕 媛쒕큺 �삁�젙�옉 select option �씫�뼱�삤湲�
	public ArrayList<MovieBean> RelMovieSubject() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MovieBean> RMarr = null;
		try {
			RMarr = new ArrayList();
			con = getConnection();
			st = con.createStatement();
			sql = "select subject,rel_date from movie where rel_date > sysdate";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setSubject(rs.getString("subject"));
				mb.setRel_date(rs.getDate("rel_date"));
				RMarr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return RMarr;
	}
	
	public ArrayList<GradeBean> MyGradeAction(String num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GradeBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql ="select * " + 
					"from grade g, movie m " + 
					"where g.m_num=m.movie_num and membernum="+num;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				GradeBean gb = new GradeBean();
				gb.setG_Num(rs.getInt("grade_num"));
				gb.setMovieTitle(rs.getString("subject"));
				gb.setReply(rs.getString("reply"));
				gb.setScore(rs.getDouble("score"));
				gb.setGrade_Date(rs.getString("grade_date"));
				gb.setGood(rs.getInt("good"));
				gb.setBad(rs.getInt("bad"));
				arr.add(gb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
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
	public MovieBean MainOnScreenSubject(String movie_num) {
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      MovieBean mb = new MovieBean();
	      try {
	         con = getConnection();
	         String sql = "select * from movie where movie_num="+movie_num;
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         if(rs.next()) {
	            mb.setImg(rs.getString("img"));
	            mb.setNum(rs.getInt("movie_num"));
	            mb.setSubject(rs.getString("subject"));
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }

	      return mb;
	   }
	   public ArrayList<GradeBean> MainOnScreenGrade(String movie_num) {//상영중 영화 최신글 호출
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      ArrayList<GradeBean> arr = new ArrayList<>();
	      try {
	         con = getConnection();
	         String sql = "select * from (" + 
	               "select rownum rn, aa.* from (" + 
	               "select * from grade where m_num="+movie_num+" order by grade_date desc" + 
	               ")aa)" + 
	               "where rn>=1 and rn<=5";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         while(rs.next()) {
	            GradeBean gb = new GradeBean();
	         
	            gb.setReply(rs.getString("reply"));
	            gb.setScore(rs.getDouble("score"));
	            gb.setGrade_Date(rs.getString("grade_date"));
	            arr.add(gb);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }
	      return arr;
	   }
	   public int getGradeShowingNum() {//개봉중 평점 높은 영화
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      int movie_num = 0;
	      try {
	         con = getConnection();
	         String sql = "select * from movie where rel_date<sysdate order by on_rating desc";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         if(rs.next()) {
	          movie_num = rs.getInt("movie_num");
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }

	      return movie_num;
	   }
	   public int getGradeWaitingNum() {//미개봉 평점 높은 영화
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      int movie_num = 0;
	      try {
	         con = getConnection();
	         String sql = "select * from movie where rel_date>sysdate order by rel_rating desc";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         if(rs.next()) {
	          movie_num = rs.getInt("movie_num");
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }

	      return movie_num;
	   }
	   public int getManyGradeWatingNum() {//미개봉 평가글 갯수가 많은 영화
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      int movie_num = 0;
	      try {
	         con = getConnection();
	         String sql = "select m.movie_num,count(*) from movie m, grade g where m.movie_num=g.m_num and rel_date>sysdate group by m.movie_num order by count(*) desc";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         if(rs.next()) {
	          movie_num = rs.getInt("movie_num");
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }

	      return movie_num;
	   }
	   public ArrayList<MovieBean> getMovieRanking() {//영화 순위
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      ArrayList<MovieBean> arr = new ArrayList<>();
	      try {
	         con = getConnection();
	         String sql ="select aa.* from (select * from movie where rel_date<sysdate order by on_rating desc)aa where rownum <=5";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         while(rs.next()) {
	            MovieBean mb = new MovieBean();
	            mb.setNum(rs.getInt("movie_num"));
	            mb.setImg(rs.getString("img"));
	            mb.setSubject(rs.getString("subject"));
	            mb.setOn_rating(rs.getDouble("on_rating"));
	            arr.add(mb);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }
	      return arr;
	   }
	   public ArrayList<GradeBean> MainWatingGrade(String movie_num) {//미개봉 영화 최신글 호출
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      ArrayList<GradeBean> arr = new ArrayList<>();
	      try {
	         con = getConnection();
	         String sql = "select * from (" + 
	               "select rownum rn, aa.* from (" + 
	               "select * from grade where m_num="+movie_num+" order by grade_date desc" + 
	               ")aa)" + 
	               "where rn>=1 and rn<=5";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         while(rs.next()) {
	            GradeBean gb = new GradeBean();
	         
	            gb.setReply(rs.getString("reply"));
	            gb.setScore(rs.getDouble("score"));
	            gb.setGrade_Date(rs.getString("grade_date"));
	            arr.add(gb);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }
	      return arr;
	   }
	   public ArrayList<MovieBean> getWatingRanking() {//미개봉 영화 평가글순 랭킹
	      Connection con = null;
	      Statement st = null;
	      ResultSet rs = null;
	      ArrayList<MovieBean> arr = new ArrayList<>();
	      try {
	         con = getConnection();
	         String sql ="select m.movie_num,m.subject,m.img,count(*) from movie m, grade g where m.movie_num=g.m_num and rel_date>sysdate group by m.movie_num,m.subject,m.img order by count(*) desc";
	         st = con.createStatement();
	         rs = st.executeQuery(sql);
	         while(rs.next()) {
	            MovieBean mb = new MovieBean();
	            mb.setNum(rs.getInt("movie_num"));
	            mb.setImg(rs.getString("img"));
	            mb.setSubject(rs.getString("subject"));
	            mb.setCommentCount(rs.getInt("count(*)"));
	            arr.add(mb);
	         }
	      }catch(Exception e) {
	         e.printStackTrace();
	      }finally {
	         closeCon(con, st, rs);
	      }
	      return arr;
	   }
	   
	   
}
