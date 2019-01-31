package com.Member;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.Board.BoardBean;
import com.Grade.GradeBean;
import com.Reservation.ReservationBean;

public class MemberDAO {
	public static MemberDAO instance;

	public static MemberDAO getInstance() {

		if (instance == null) {

			instance = new MemberDAO();
		}
		return instance;
	}
	public Connection getConnection() throws Exception{

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
		}
	
	//占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥�젆蹂잙쐻占쎈윥�뜎�뛼�쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎈꽣�뜝�럥���뜝�럥�뒄�뜝�럡�렊 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥�젆臾덈쐻占쎈윞占쎈릊占쎈쐻占쎈윪�뤃占�
	public int IdCheck(String userid) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = 0;
		try {
			con = getConnection();
			String sql = "select * from member where userid ='"+userid+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				flag=1;//占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎈꽣�뜝�럥���뜝�럥�뒄�뜝�럡�렊
				return flag;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,st,rs);
		}
		return flag;
	}
	private void closeCon(Connection con, Statement st, ResultSet rs) {
		try {
		if(con!=null)con.close();
		if(st!=null)st.close();
		if(rs!=null)rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void closeCon(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
		if(con!=null)con.close();
		if(ps!=null)ps.close();
		if(rs!=null)rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private void closeCon(Connection con, PreparedStatement ps) {
		try {
		if(con!=null)con.close();
		if(ps!=null)ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃占�
	public void MemberJoin(MemberBean mb) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			String sql = "insert into member values (member_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, mb.getUserid());
			ps.setString(2, mb.getPassword());
			ps.setString(3, mb.getName());
			ps.setString(4, mb.getPnum());
			ps.setString(5, mb.getPhone());
			ps.setString(6, mb.getEmail());
			ps.setString(7, mb.getZipcode());
			ps.setString(8, mb.getAddr1());
			ps.setString(9, mb.getAddr2());
			ps.setString(10, mb.getGender());
			ps.setString(11, mb.getInter1());
			ps.setString(12, mb.getInter2());
			ps.setString(13, mb.getInter3());
			ps.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeCon(con,ps);
		}
		
	}

	//占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윪繹먲옙 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥�젆臾덈쐻占쎈윞占쎈릊占쎈쐻占쎈윪�뤃占�
	public ArrayList<ZipcodeBEAN> zipSearch(String dong) {
		Connection con = null;
		Statement st =null;
		ResultSet rs = null;
		ArrayList<ZipcodeBEAN> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql = "select * from zipcode where dong like '%"+dong+"%'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ZipcodeBEAN zb = new ZipcodeBEAN();
				zb.setBunji(rs.getString("bunji"));
				zb.setDong(rs.getString("dong"));
				zb.setGugun(rs.getString("gugun"));
				zb.setSido(rs.getString("sido"));
				zb.setZipcode(rs.getString("zipcode"));
				arr.add(zb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	public int MemberLogin(String id, String password) {//占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥�젆짰萸먪솒占쏙옙逾겼뜝�럡�렊占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃占�
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = 3;
		try {
			con=getConnection();
			String sql = "select * from member where userid = '"+id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(password.equals(rs.getString("password"))) {
					flag = 1;
					return flag;
				}
				flag=2;
				return flag;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public MemberBean getMember(String id) {//占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃占� 占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍�뜝�럥�맶占쎈쐻�뜝占�
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		try {
			con = getConnection();
			String sql = "select * from member where userid = '"+id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mb.setUserid(rs.getString("userid"));
				mb.setPassword(rs.getString("password"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setAddr2(rs.getString("addr2"));
				mb.setEmail(rs.getString("email"));
				mb.setGender(rs.getString("gender"));
				mb.setName(rs.getString("name"));
				mb.setPhone(rs.getString("phone"));
				mb.setPnum(rs.getString("pnum"));
				mb.setZipcode(rs.getString("zipcode"));
				mb.setNum(rs.getInt("member_num"));
				mb.setInter1(rs.getString("inter1"));
				mb.setInter2(rs.getString("inter2"));
				mb.setInter3(rs.getString("inter3"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return mb;
	};
	
	   
	public ArrayList<BoardBean> MyBoardAction(String num, String board_field, String board_word, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardBean> arr = new ArrayList<>();
		String sql = "";
		try {
			con = getConnection();
			if(board_word.equals("")||board_word=="") {
				sql = "select * from(select rownum rn, aa.* from (select * from board where membernum="+num+" order by created desc)aa) "
						+ "where rn >= "+startRow+" and rn <="+endRow;
			}else {
				sql = "select * from(select rownum rn, aa.* from (select * from board where "+board_field+" like '%"+board_word+"%' and membernum="+num+" order by created desc)aa) "
						+ "where rn >= "+startRow+" and rn <="+endRow;
			};
			 
			st = con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				BoardBean bb = new BoardBean();
				bb.setNum(rs.getInt("board_num"));
				bb.setUserid(rs.getString("userid"));
				bb.setMembernum(rs.getInt("membernum"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setCreated(rs.getString("created"));
				bb.setGood(rs.getInt("good"));
				bb.setBad(rs.getInt("bad"));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setIp(rs.getString("ip"));
				bb.setImg(rs.getString("img"));
				bb.setCategory(rs.getString("category"));
				arr.add(bb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	//占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윪占쎌맱占쎈쐻占쎈윪占쎌젳占쎌녃域밟뫁�굲  占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳
	public void MemberUpdate(MemberBean mb) {
		Connection con = null;
		PreparedStatement ps =null;
		try {
			con = getConnection();
			String sql ="update member set "
					+ "password=?,pnum=?,phone=?,email=?,zipcode=?,addr1=?,addr2=?,inter1=?,inter2=?,inter3=? where userid='"+mb.getUserid()+"'";
			ps =con.prepareStatement(sql);
			ps.setString(1, mb.getPassword());
			ps.setString(2, mb.getPnum());
			ps.setString(3, mb.getPhone());
			ps.setString(4, mb.getEmail());
			ps.setString(5, mb.getZipcode());
			ps.setString(6, mb.getAddr1());
			ps.setString(7, mb.getAddr2());
			ps.setString(8, mb.getInter1());
			ps.setString(9, mb.getInter2());
			ps.setString(10, mb.getInter3());
			ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	public int MemberDelete(MemberBean mb) {//占쎈쐻占쎈윪占쎈쨧占쎈쐻占쎈윪占쎌맱占쎈쐻占쎈윞占쎈룼占쎈쐻占쎈윥占쎈뻹
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int res = 0;//�뜝�럥�몡�넭怨ｋ쳛占쎈쾺 �뜝�럡猿��뜝�럥�맆�뜝�럥�럡占쎈뇲占쎄땔占쎌굲
		try {
			con = getConnection();
			 sql ="select * from member where member_num = "+mb.getNum();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(mb.getPassword().equals(rs.getString("password"))) {
					//�뜝�럥�몡�넭怨ｋ쳛占쎈쾺 占쎈쐻占쎈윪�앗낅뇲占쎄땔占쎌굲
					sql ="delete member where member_num="+mb.getNum();
					st.execute(sql);
					res = 1;
					return res;
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return res;
		
	}
	public ArrayList<GradeBean> MyGradeAction(String num, String movie_subject, int startRow, int endRow) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<GradeBean> arr = new ArrayList<>();
		String sql = "";
		try {
			con = getConnection();
			if(movie_subject==""||movie_subject.equals("")) {
				 sql =  "select * from(select rownum rn, aa.* from (select "
						+ "g.grade_num, m.subject,movie_num,g.reply,g.score,g.grade_date,g.good,g.bad"
						+ " from grade g, movie m where g.m_num=m.movie_num and membernum="+num+" order by grade_date desc)aa) "
						+ "where rn >= "+startRow+" and rn <="+endRow;
			}else {
				 sql =  "select * from(select rownum rn, aa.* from (select "
						+ "g.grade_num, m.subject,movie_num,g.reply,g.score,g.grade_date,g.good,g.bad"
						+ " from grade g, movie m where g.m_num=m.movie_num and membernum="+num+"and m.subject like '%"+movie_subject+"%' order by grade_date desc)aa) "
						+ "where rn >= "+startRow+" and rn <="+endRow;
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				GradeBean gb = new GradeBean();
				gb.setG_Num(rs.getInt("grade_num"));
				gb.setMovie_Num(rs.getInt("movie_num"));
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
	public int MyBoardCount(String num, String board_field, String board_word) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int bcnt = 0;
		String sql = "";
		try {
			con = getConnection();
			if(board_word.equals("")||board_word=="") {
				sql = "select count(*) from Board where membernum ="+num;
			}else {
				sql = "select count(*) from board where "+board_field+" like '%"+board_word+"%' and membernum ="+num;
			}
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				bcnt = rs.getInt("count(*)");
				return bcnt;
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bcnt;
	}
	public int MyGradeCount(String num, String movie_subject) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int gcnt = 0;
		String sql ="";
		try {
			con = getConnection();
			if(movie_subject==""||movie_subject.equals("")) {
				sql = "select count(*) from grade where membernum ="+num;
				
			}else {
				sql = "select count(*) "+
						"from grade g, movie m "+
						"where g.m_num=m.movie_num and m.subject like '%"+movie_subject+"%' and membernum ="+num;
			}
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				gcnt = rs.getInt("count(*)");
				return gcnt;
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		return gcnt;
	}
	public MemberBean FindId(MemberBean mb) {//�뜝�럥�닡�뜝�럩逾졾뜝�럥�꺏 嶺뚢돦堉먪뵳占�
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberBean findMb = new MemberBean();
		try {
			con=getConnection();
			String sql = "select * from member where name=? and pnum=? and phone=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, mb.getName());
			ps.setString(2, mb.getPnum());
			ps.setString(3, mb.getPhone());
			rs = ps.executeQuery();
			if(rs.next()) {
				findMb.setUserid(rs.getString("userid"));
				return findMb;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,ps,rs);
		}
		findMb.setUserid("null");
		return findMb;
	}
	public MemberBean FindPwd(MemberBean mb) {//占쎈쑏熬곣뫁�벐 嶺뚢돦堉먪뵳占�
		//占쎈쑏熬곣뫁�벐 �뜝�럩逾х뇖�궪�삕 1, 占쎄껀占쎈쐠占쎈뎄�뇖�궪�삕 0
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MemberBean findPwd = new MemberBean();
		try {
			con=getConnection();
			String sql = "select * from member where userid=? and pnum=? and phone=?";
			ps= con.prepareStatement(sql);
			ps.setString(1, mb.getUserid());
			ps.setString(2, mb.getPnum());
			ps.setString(3, mb.getPhone());
			rs = ps.executeQuery();
			if(rs.next()) {
				findPwd.setNum(rs.getInt("member_num"));
				findPwd.setPassword("1");
				return findPwd;
			}	
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,ps,rs);
		}
		findPwd.setPassword("0");
		return findPwd;
	}
	public void changePwd(String member_num, String pwd) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "update member set password='"+pwd+"' where member_num="+member_num;
			ps = con.prepareStatement(sql);
			ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	public int MyReservaionCount(String num) {
		Connection con = null;
		Statement st = null;
		ResultSet rs  =null;
		int cnt = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from booking where membernum="+num;
			st = con.createStatement();
			rs= st.executeQuery(sql);
			while(rs.next()) {
				 cnt = rs.getInt("count(*)");
				 return cnt;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return cnt;
	}
	public ArrayList<ReservationBean> MyReservationAction(String num, int rstartRow, int rendRow) {
		//Reservation RECORD Return
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ReservationBean> arr = new ArrayList<>();
		try {
			con = getConnection();
			String sql ="select * from(select rownum rn, aa.* from"
					+ " (select 	* 	from booking b, movie m where b.movienum=m.movie_num and "
					+ "membernum="+num+" order by bookingdate desc)aa) where rn >="+rstartRow+"  and rn <="+rendRow;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ReservationBean rb = new ReservationBean();
				rb.setResSeat(rs.getString("seat"));
				rb.setMovieSubject(rs.getString("subject"));
				rb.setAdultCount(rs.getInt("adult"));
				rb.setBookingDate(rs.getString("bookingdate"));
				rb.setBookingNum(rs.getInt("booking_num"));
				rb.setPrice(rs.getInt("price"));
				rb.setYouthCount(rs.getInt("youth"));
				rb.setTheatherName(searchTheaterName(rs.getString("roomnum")));
				rb.setMovieNum(rs.getInt("movienum"));
				rb.setResDateAndTime(ReservatedTime(rs.getString("theater_time_num")));
				arr.add(rb);
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	private String searchTheaterName(String roomnum) {//Theater Name return
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String theatername = "";
		try {
			con = getConnection();
			String sql = "select * from room r,theater t where r.theaternum=t.theater_num and room_num="+roomnum;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				theatername = rs.getString("theatername");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return theatername;
	}
	private String ReservatedTime(String theater_time_num_) {//Return Reservated Time and Date
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String theaterTime = "";
		try {
			con = getConnection();
			String sql = "select * from theater_time where theater_time_num="+theater_time_num_;
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				theaterTime ="상영날짜 : "+rs.getString("moviedate").replace("00:00:00","")+"시간 :"+rs.getString("ontime");;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return theaterTime;
	}
	public int ResCancel(MemberBean mb) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int res = 0;//Result of CancelReserVation
		try {
			con = getConnection();
			 sql ="select * from member where member_num = "+mb.getNum();
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(mb.getPassword().equals(rs.getString("password"))) {
					
					sql ="delete booking where booking_num="+mb.getBookingNum();
					//cancel reservation SQL
					st.execute(sql);
					res = 1;
					return res;
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return res;
		
	}
	      
}

