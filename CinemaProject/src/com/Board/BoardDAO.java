package com.Board;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	public static BoardDAO instance;

	public static BoardDAO getInstance() {

		if (instance == null) {

			instance = new BoardDAO();
		}
		return instance;
	}
	public Connection getConnection() throws Exception{

		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/jsp");
		return ds.getConnection();
		}
	//媛� 寃뚯떆湲� �떦 異붿쿇 媛쒖닔
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
	
	//媛� 寃뚯떆湲� �떦 鍮꾩텛泥� 媛쒖닔
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
	//占쎌젫筌뤴뫕占� 占쎌겳占쎈퓠 () 占쎈툧占쎈퓠 占쎈떭占쎌쁽
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
	//異붿쿇/鍮꾩텛泥� 以묐났寃��궗
	public int boardGoodBadCheck(int membernum, int boardnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int check = 1;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from board_like where membernum = "+membernum;
			rs = st.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("boardnum") == boardnum) {
					check =  0;
					return check;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return check;
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥諭�
	public ArrayList<BoardBean> boardList(int startRow,int endRow,String field,String word,String category,String sorting){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<BoardBean> arr = new ArrayList<>();
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			if(sorting != null) {
				if(word == null) {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥堉볟뜝�럥�닡�뜝�럥鍮쒎뜝�럥�뿼�뜝�럩援�
					if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from (select * from board b, (select boardnum,sum(good) good,sum(bad) bad from board_like group by boardnum) bl where b.board_num = bl.boardnum(+)) order by "+sorting+" desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow;
					}else {//�뇖�궠�뼠占쎌맶�뜝�럥�냺占썩벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�룞�삕�뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from (select * from board b, (select boardnum,sum(good) good,sum(bad) bad from board_like group by boardnum(+)) bl where b.board_num = bl.boardnum(+)) where category = '"+category+"' order by "+sorting+" desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}
				}else {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
					if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from (select * from board b, (select boardnum,sum(good) good,sum(bad) bad from board_like group by boardnum(+)) bl where b.board_num = bl.boardnum(+)) where "+field+" like '%"+word+"%' order by "+sorting+" desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}else{//�뇖�궠�뼠占쎌맶�뜝�럥�냺占썩벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�룞�삕�뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from (select * from board b, (select boardnum,sum(good) good,sum(bad) bad from board_like group by boardnum(+)) bl where b.board_num = bl.boardnum(+)) where "+field+" like '%"+word+"%' and category = '"+category+"' order by "+sorting+" desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}
				}
			}else{
				if(word == null) {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥堉볟뜝�럥�닡�뜝�럥鍮쒎뜝�럥�뿼�뜝�럩援�
					if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from board order by board_num desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow;
					}else {//�뇖�궠�뼠占쎌맶�뜝�럥�냺占썩벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�룞�삕�뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from board where category = '"+category+"' order by board_num desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}
				}else {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
					if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from board where "+field+" like '%"+word+"%' order by board_num desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}else{//�뇖�궠�뼠占쎌맶�뜝�럥�냺占썩벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�룞�삕�뜝�럩逾쎾뜝�럩援�
						sql = "select * from"
								+ "(select rownum rn, aa.* from(select * from board where "+field+" like '%"+word+"%' and category = '"+category+"' order by board_num desc nulls last)aa)"
								+ " where rn >="+startRow+" and rn <="+endRow+"";
					}
				}
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
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�넍�뜝�럥�꽔�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	public void boardInsert(BoardBean bb) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into board values(board_movie_seq.nextval,?,?,?,?,to_char(sysdate,'yyyy/mm/dd'),?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, bb.getUserid());
			ps.setInt(2, bb.getMembernum());
			ps.setString(3, bb.getSubject());
			ps.setString(4, bb.getContent().replaceAll(System.getProperty("line.separator"), "<br>"));
			ps.setInt(5, 0);
			ps.setString(6, bb.getIp());
			ps.setString(7, "");
			ps.setString(8, bb.getCategory());
			ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥�끍�뜝�럥六사뼨�먯삕�뜝�럥�떚�뜝�럩援�
	public int boardCount(String field,String word,String category) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int count = 0;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			if(word == null) {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥�넍�뜝�럥�닡�뜝�럥鍮쒎뜝�럩逾쎾뜝�럩援�
				if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
					sql = "select count(*) cnt from board";
				}else {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
					sql = "select count(*) cnt from board  where category = '"+category+"'";
				}
			}else {//�뜝�럥�맶�뜝�럥堉묈뜝�럡�븫�뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
				if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
					sql = "select count(*) cnt from board where "+field+" like '%"+word+"%'";
				}else {
					sql = "select count(*) cnt from board where "+field+" like '%"+word+"%' and category = '"+category+"'";
				}
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
	
	//�뜝�럥�맶�뜝�럥堉⑶뇦猿딆뒩占쎈뻣�뼨�먯삕�뜝�럥�떚�뜝�럩援�
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
	
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥竊뗥뜝�럡�돪占쎈눀占쎈튂占쎄뎡�뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	public BoardBean boardView(int num,int checknum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		BoardBean bb = null;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "update board set readcount = readcount+1 where board_num = "+num;
			rs = st.executeQuery(sql);
			sql = "select * from board where board_num = "+num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				bb = new BoardBean();
				bb.setNum(rs.getInt("board_num"));
				bb.setUserid(rs.getString("userid"));
				bb.setMembernum(rs.getInt("membernum"));
				bb.setSubject(rs.getString("subject"));
				if(checknum == 1) {
					bb.setContent(rs.getString("content").replaceAll("<br>", "\r\n"));
				}else {
					bb.setContent(rs.getString("content"));
				}
				bb.setCreated(rs.getString("created"));
				bb.setGood(goodCount(rs.getInt("board_num")));
				bb.setBad(badCount(rs.getInt("board_num")));
				bb.setReadcount(rs.getInt("readcount"));
				bb.setIp(rs.getString("ip"));
				bb.setUpdated(rs.getString("updated"));
				bb.setCategory(rs.getString("category"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return bb;
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援뀐옙�렡占쎄섈占쎌맶�뜝�럥堉랃옙�쐻�뜝占� 嶺뚳퐢�샑野껓옙	
	public int boardPasscheck(String passcheck, int membernum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int check = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from member where member_num = "+membernum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				if(passcheck.equals(rs.getString("password"))) {
					check = 1;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return check;
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥諭�
	public void boardUpdate(BoardBean bb) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update board set subject = '"+bb.getSubject()+"', content = '"+bb.getContent().replaceAll(System.getProperty("line.separator"), "<br>")+"', updated = to_char(sysdate,'yyyy/mm/dd') where board_num = "+bb.getNum();
			 st.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st);
		}
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥六사뭐癒��뵰占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
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
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥�넰占썩벀�걠占쎄뎡
	public void bardReplyInsert(BoardReplyBean brb) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = getConnection();
			String sql = "insert into board_reply values(board_reply_seq.nextval,?,?,?,?,?,?,TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS'),?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setInt(1, brb.getBoardnum());
			ps.setInt(2, brb.getMembernum());
			ps.setInt(3, 0);
			ps.setInt(4, 0);
			ps.setInt(5, 0);
			ps.setString(6, brb.getContent());
			ps.setString(7, "");
			ps.setString(8, brb.getIp());
			ps.setString(9, brb.getUserid());
			ps.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, ps);
		}
		
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥諭�
	public ArrayList<BoardReplyBean> boardReplyList(int startRow, int endRow,int boardnum){
		Connection con = null;
		Statement st =  null;
		ResultSet rs = null;
		ArrayList<BoardReplyBean> arr = new ArrayList<>();
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select * from"
					+ "(select rownum rn, aa.* from(select * from board_reply where boardnum = "+boardnum+" order by board_reply_num desc)aa)"
					+ " where rn >="+startRow+" and rn <="+endRow+"";
			rs = st.executeQuery(sql);
			while(rs.next()) {
				BoardReplyBean brb = new BoardReplyBean();
				brb.setNum(rs.getInt("board_reply_num"));
				brb.setBoardnum(rs.getInt("boardnum"));
				brb.setMembernum(rs.getInt("membernum"));
				brb.setRef(rs.getInt("ref"));
				brb.setRe_step(rs.getInt("re_step"));
				brb.setRe_level(rs.getInt("re_level"));
				brb.setContent(rs.getString("content"));
				brb.setCreated(rs.getString("created"));
				brb.setUpdated(rs.getString("updated"));
				brb.setIp(rs.getString("ip"));
				brb.setUserid(rs.getString("userid"));
				arr.add(brb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥堉⑶뇦猿딆뒩占쎈뻣�뼨�먯삕�뜝�럥�떚�뜝�럩援�
	public int boardReplyCount(int boardnum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int recount = 0;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "select count(*) cnt from board_reply where boardnum ="+boardnum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				recount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return recount;
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	public void boardReplyUpdate(int num, String content) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "update board_reply set content = '"+content+"',updated = TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS') where board_reply_num ="+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st);
		}
		
	}
	//�뜝�럥�맶�뜝�럥�끍�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶占쎈쐻�뜝占� �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援�
	public void boardReplyDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from board_reply where board_reply_num ="+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st);
		}
	}
	
	//野껊슣�뻻占쎈솇 占쎄맒占쎄쉭癰귣떯由� 占쎈뼄占쎌벉疫뀐옙,占쎌뵠占쎌읈疫뀐옙
	public int boardViewNext(int boardnum,int checknum) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int rownum = 0;
		int board_num = 0;
		int flag = 0;
		
		
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select * from (select rownum rn, aa.* from (select * from board order by board_num desc)aa) where board_num = "+boardnum;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				rownum = rs.getInt("rn");
			}
			int totcount = boardTotCount();
			if(checknum==1) {//checknum占쎌뵠 占쎈뼄占쎌벉疫뀐옙占쎌뵠筌롳옙
				if((rownum+1)>totcount) {
					flag = -1;
					return flag;
				}
			}
			if(checknum==-1) {//checknum占쎌뵠 占쎌뵠占쎌읈疫뀐옙占쎌뵠筌롳옙
				if((rownum-1)<1) {
					flag = -2;
					return flag;
				}
			}
			if(checknum == 1) {
				sql = "select * from (select rownum rn, aa.* from (select * from board order by board_num desc)aa) where rn = "+(rownum+1);
			}else {
				sql = "select * from (select rownum rn, aa.* from (select * from board order by board_num desc)aa) where rn = "+(rownum-1);
			}
			
			rs = st.executeQuery(sql);
			if(rs.next()) {
				board_num = rs.getInt("board_num");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return board_num;
	}
	//野껊슣�뻻占쎈솇 �빊遺우퓝�뜮袁⑺뀤筌ｏ옙 占쎄깻�뵳占�
	public void boardGoodBadClick(int checknum,int boardnum,int membernum) {
		Connection con = null;
		Statement st = null;
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			if(checknum == 1) {
				sql = "insert into board_like values("+membernum+","+boardnum+",1,0,BOARD_LIKE_SEQ.nextval)";
			}
			if(checknum == -1) {
				sql = "insert into board_like values("+membernum+","+boardnum+",0,1,BOARD_LIKE_SEQ.nextval)";
			}
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st);
		}
	}

	//�뜝�럥�맶�뜝�럥�넦�뜝�럥�냷�뜝�럩援꿨뜝�럥�맶�뜝�럥�끍�뜝�럥�떚�뜝�럩援�
	   private void closeCon(Connection con, Statement st, ResultSet rs) {
	      try {
	      if(con!=null)con.close();
	      if(st!=null)st.close();
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

	private void closeCon(Connection con, Statement st) {
	      try {
	      if(con!=null)con.close();
	      if(st!=null)st.close();
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	   }
	
	   public ArrayList<BoardBean> Category_Board(String category) {
		      Connection con = null;
		      Statement st = null;
		      ResultSet rs = null;
		      ArrayList<BoardBean> arr = new ArrayList<>();
		      try {
		         con = getConnection();
		         String sql = "select * from(select rownum rn, aa.* from(select board_num,subject,count(good) from board b,board_like bl where b.board_num=bl.boardnum(+) and category='"+category+"' group by board_num,subject order by  count(good) desc )aa) where rn>=1 and rn<=5";
		         st = con.createStatement();
		         rs = st.executeQuery(sql);
		         while(rs.next()) {
		            BoardBean bb = new BoardBean();
		            bb.setNum(rs.getInt("board_num"));
		            bb.setSubject(rs.getString("subject"));
		            bb.setGood(rs.getInt("count(good)"));
		            arr.add(bb);
		         }
		      }catch(Exception e) {
		         e.printStackTrace();
		      }finally {
		         closeCon(con, st, rs);
		      }
		      return arr;
		   }
}
