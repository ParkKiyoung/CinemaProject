package com.Movie;

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
import com.Grade.GradeBean;

public class MovieDAO {
	public static MovieDAO instance;

	public static MovieDAO getInstance() {

		if (instance == null) {

			instance = new MovieDAO();
		}
		return instance;
	}

	public Connection getConnection() throws Exception {

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
	}

	// 占쎈쐻占쎈윪野껉막�쐻占쎈윪占쎄땍 占쎈쐻占쎈윞筌띾�ｋ쐻占쎈윪野껁깷�쐻占쎈윪占쎄탾 �뜝�럥�렓�뜝�럥遊얕짆�쉩�쐻占쎈윥獄�占�
	public ArrayList<MovieBean> OnScreenList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
//			String sql = "select * from movie where rel_date<=sysdate order by rel_date desc";
			String sql = "select * from movie mv, (select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and mv.rel_date<=sysdate and max_date>=sysdate order by rel_date desc";
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

	// 占쎈쨬占쎈즵獒뺣냵�삕野껓옙 占쎈쐻占쎈윪�뤃�눨�쐻占쎈윪占쎌젳占쎈쐻占쎈윪占쎄탾 �뜝�럥�렓�뜝�럥遊얕짆�쉩�쐻占쎈윥獄�占�
	public ArrayList<MovieBean> RelScreenList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from movie where rel_date>sysdate order by rel_date";
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

	// 癲ル슣�돸占쎌굲占쎈쐻占쎈윞占쎈�� 占쎈쐻占쎈윞筌띾�ｋ쐻占쎈윪野껁깷�쐻占쎈윪占쎄탾 �뜝�럥�렓�뜝�럥遊얕짆�쉩�쐻占쎈윥獄�占�
	public ArrayList<MovieBean> OutScreenList() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from movie mv, (select movienum ,max(outdate) max_date from theater_Date group by movienum)dd where mv.movie_num = dd.movienum and mv.rel_date<=sysdate and max_date<=sysdate order by rel_date desc";
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

	// 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈쐻占쎈윞筌띾�ｋ쐻占쎈윞占쎈룵占쎌녃域뱄퐢堉э옙逾녑뜝占�
	public MovieBean MovieView(int num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MovieBean bean = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from movie where movie_num=" + num;
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
				bean.setSummary(rs.getString("summary"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return bean;
	}

	// 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌 占쎈쐻占쎈윪�굢占쏙옙�쐻占쎈윪占쎌죷占쎈쐻占쎈윥�뵳占쏙옙堉⑨옙癒��굲
	public void GradeInsert(GradeBean bean) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into grade values(grade_seq.nextval,?,?,?,?,sysdate,?,0,0)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, bean.getMovie_Num());
			ps.setInt(2, bean.getMember_Num());
			ps.setString(3, bean.getReply().replaceAll(System.getProperty("line.separator"), "<br>"));
			ps.setDouble(4, bean.getScore());
			ps.setString(5, bean.getIp());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, ps);
		}
	}

	// 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌 濚욌꼬�궡�꺇�뜝�럡�뀬 占쎈쐻占쎈윪�굢占쏙옙�쐻占쎈윪占쎌죷 癲ル슪�맊占쎌깙�뇦猿볦삕
	public int GradeCheck(int movienum, int membernum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int check = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from grade where m_num=" + movienum + " and membernum=" + membernum;
			rs = st.executeQuery(sql);
			if (rs.next()) {
				check = 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return check;

	}

	// 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌 占쎈섀占쎈Ŋ�굲 �뜝�럥�렓�뜝�럥遊얕짆�쉩�쐻占쎈윥獄�占�
	public ArrayList<GradeBean> GradeList(int movienum, int flag, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GradeBean> arr = new ArrayList<>();
		String sql = null;
		try {
			con = getConnection();
			st = con.createStatement();
			if (flag == 0) {
				sql = "select * from (select rownum rn, aa.* from (SELECT g.bad, g.grade_num, g.good, g.grade_date, g.ip, g.membernum, g.m_num, g.reply, g.score, mb.userid FROM grade g, member mb, movie mv WHERE g.membernum = mb.member_num AND g.m_num = mv.movie_num AND g.m_num ="
						+ movienum
						+ "AND mv.rel_date > g.grade_date ORDER BY g.grade_date DESC, g.good DESC) aa) where rn >="
						+ startRow + " and rn <=" + endRow;
			} else if (flag == 1) {
				sql = "select * from (select rownum rn, aa.* from (SELECT g.bad, g.grade_num, g.good, g.grade_date, g.ip, g.membernum, g.m_num, g.reply, g.score, mb.userid FROM grade g, member mb, movie mv WHERE g.membernum = mb.member_num AND g.m_num = mv.movie_num AND g.m_num ="
						+ movienum
						+ "AND mv.rel_date <= g.grade_date ORDER BY g.grade_date DESC, g.good DESC) aa) where rn >="
						+ startRow + " and rn <=" + endRow;
			}
			rs = st.executeQuery(sql);
			while (rs.next()) {
				GradeBean bean = new GradeBean();
				bean.setBad(rs.getInt("bad"));
				bean.setG_Num(rs.getInt("grade_num"));
				bean.setGood(rs.getInt("good"));
				bean.setGrade_Date(rs.getString("grade_date"));
				bean.setIp(rs.getString("ip"));
				bean.setMember_Num(rs.getInt("membernum"));
				bean.setMovie_Num(rs.getInt("m_num"));
				bean.setReply(rs.getString("reply"));
				bean.setScore(rs.getDouble("score"));
				bean.setUserId(rs.getString("userid"));
				arr.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return arr;
	}

	// 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌占쎈섀占쎈Ŋ�굲 占쎈쨬占쎈즸占쎌굲占쎈쐻占쎈윥占쎈묄 占쎈뇲占쎄텭占쎌깓�뜝�럥裕㏆옙�쐻占쎈윥獄�占�
	public int GradeCount(int movienum, int flag) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql = null;
		try {
			con = getConnection();
			st = con.createStatement();
			if (flag == 0) {
				sql = "SELECT count(*) cnt FROM grade g, member mb, movie mv WHERE g.membernum = mb.member_num AND g.m_num = mv.movie_num AND g.m_num ="
						+ movienum + "AND mv.rel_date > g.grade_date ORDER BY g.grade_date DESC, g.good DESC";
			} else if (flag == 1) {
				sql = "SELECT count(*) cnt FROM grade g, member mb, movie mv WHERE g.membernum = mb.member_num AND g.m_num = mv.movie_num AND g.m_num ="
						+ movienum + "AND mv.rel_date <= g.grade_date ORDER BY g.grade_date DESC, g.good DESC";
			}
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

	// 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌 占쎈쐻占쎈윥筌앸ŀ�쐺獄�袁⑹굲 占쎈쐻占쎈윪占쎄껑占쎈쐻占쎈윥筌욑옙 占쎈쐻占쎈윞占쎈�겼뜝�럥�돯占쎈쐻�뜝占� 占쎈쐻占쎈윪占쎌맗 占쎈쐻占쎈윪�굢占쏙옙�쐻占쎈윪占쎌죷
	public void UpdateRating(int num, int flag) {
		Connection con = null;
		Statement st = null;
		String sql = null;
		try {
			con = getConnection();
			st = con.createStatement();
			if (flag == 0) {
				sql = "update movie set rel_rating=(select round(avg(g.score), 2) from grade g, movie mv where g.m_num=mv.movie_num and g.m_num="
						+ num + " and rel_date>sysdate) where movie_num=" + num;
			} else if (flag == 1) {
				sql = "update movie set on_rating=(select round(avg(g.score), 2) from grade g, movie mv where g.m_num=mv.movie_num and g.m_num="
						+ num + " and rel_date<=sysdate) where movie_num=" + num;
			}
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st);
		}
	}

	// 占쎈쐻占쎈윥筌앸ŀ�쐻占쎈윪占쎌젌 占쎈쐻占쎈윞占쎈��占쎈쐻占쎈윪占쎌졆
	public void GradeDelete(int gradenum) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from grade where grade_num=" + gradenum;
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st);
		}
	}

	// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿
	public ArrayList<MovieBean> MainSearchMovie(String word) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from(select * from movie where subject like '%" + word
					+ "%' order by rel_date desc) where rownum <= 5";
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

	// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈눇�뙼�봿�뮝�뜝�럥六ｏ옙堉⑨옙癒��굲 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿
	public ArrayList<BoardBean> MainSearchBoard(String word) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<BoardBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from(select * from board where subject like '%" + word
					+ "%' order by created desc) where rownum <= 5";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("board_num"));
				bb.setUserid(rs.getString("userid"));
				bb.setMembernum(rs.getInt("membernum"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setCreated(rs.getString("created"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setUpdated(rs.getString("updated"));
				bb.setCategory(rs.getString("category"));
				arr.add(bb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeCon(con, ps, rs);
		}
		return arr;
	}

	// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿 占쎈쐻占쎈윥占쎈쭬占쎌녃域뱄퐢堉э옙逾녑뜝占�
	public ArrayList<MovieBean> MovieSearchList(String word, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select * from(select rownum rn, aa.* from (select * from movie where subject like '%" + word
					+ "%' order by rel_date desc)aa) where rn>=" + startRow + " and rn<=" + endRow;
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setImg(rs.getString("img"));
				mb.setSubject(rs.getString("subject"));
				mb.setOn_rating(rs.getDouble("on_rating"));
				mb.setRel_rating(rs.getDouble("rel_rating"));
				mb.setRel_date(rs.getDate("rel_date"));
				mb.setNum(rs.getInt("movie_num"));
				mb.setSummary(rs.getString("summary").replaceAll("<br>", " "));
				arr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return arr;
	}

	// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿 占쎈뇲占쎄텭占쎌깓�뜝�럥裕㏆옙�쐻占쎈윥獄�占�
	public int MovieSearchCount(String word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from movie where subject like '%" + word + "%' order by rel_date desc";
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
	
	// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿 占쎈쐻占쎈윥占쎈쭬占쎌녃域뱄퐢堉э옙逾녑뜝占�
		public ArrayList<BoardBean> BoardSearchList(String word, int startRow, int endRow) {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			String sql = "";
			ArrayList<BoardBean> arr = new ArrayList<>();
			try {
				con = getConnection();
				st = con.createStatement();
				sql = "select * from(select rownum rn, aa.* from (select * from board where subject like '%" + word
						+ "%' order by created desc)aa) where rn>=" + startRow + " and rn<=" + endRow;
				rs = st.executeQuery(sql);
				while (rs.next()) {
					BoardBean bb = new BoardBean();
					bb.setSubject(rs.getString("subject"));
					bb.setCategory(rs.getString("category"));
					bb.setCreated(rs.getString("created"));
					bb.setNum(rs.getInt("board_num"));
					bb.setContent(rs.getString("content").replaceAll("<br>", " "));
					arr.add(bb);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeCon(con, st, rs);
			}
			return arr;
		}

		// 癲ル슢�닪占쎈씔�뜝�럥�뜲 占쎈쐻占쎈윪野껁깷�쐻占쎈윪占쎄섀 占쎈눇�뙼蹂��굲占쎈쐻占쎈윞占쎈룿 占쎈뇲占쎄텭占쎌깓�뜝�럥裕㏆옙�쐻占쎈윥獄�占�
		public int BoardSearchCount(String word) {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			int count = 0;
			try {
				con = getConnection();
				st = con.createStatement();
				String sql = "select count(*) cnt from board where subject like '%" + word + "%' order by created desc";
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

	public ArrayList<MovieBean> MainMovieTitle(String field) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MovieBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			st = con.createStatement();
			if (field.equals("1")) {// order by showing
				sql = "select * from(select rownum rn, aa.* from (select * from movie m,theater_date td " + 
						"where m.movie_num=td.movienum and sysdate between indate and outdate " + 
						"order by m.on_rating desc)aa) where rn>=1 and rn<=5";
			} else if (field.equals("2")) {// order by soon open
				sql = "select * from(select rownum rn, aa.* from (select * from movie where rel_date>sysdate and rel_rating>7 order by rel_date)aa) where rn>=1 and rn<=5";
			} else if (field.equals("3")) {// order by reservation
				sql="select * from(select rownum rn, aa.* from " + 
						"( " + 
						"select subject,movie_num,on_rating,rel_rating,rel_date,img,count(*) from movie m, booking b, theater_date td " + 
						"where m.movie_num=td.movienum and b.movienum=m.movie_num and sysdate between indate and outdate " + 
						"group by subject,movie_num,on_rating,rel_date,rel_rating,img " + 
						"order by count(*) desc,on_rating desc " + 
						")aa) "+ 
						"where rn>=1 and rn<=5 ";

			} else if (field.equals("4")) {// order by grade
				sql = "select * from(select rownum rn, aa.* from "
						+ "(select * from movie where rel_date<sysdate order by on_rating desc)aa) "
						+ "where rn>=1 and rn<=5";
			}
			rs = st.executeQuery(sql);
			while (rs.next()) {
				MovieBean mb = new MovieBean();
				mb.setImg(rs.getString("img"));
				mb.setSubject(rs.getString("subject"));
				mb.setOn_rating(rs.getDouble("on_rating"));
				mb.setRel_rating(rs.getDouble("rel_rating"));
				mb.setRel_date(rs.getDate("rel_date"));
				mb.setNum(rs.getInt("movie_num"));
				arr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
}
