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

public class G_ManagerDAO {
	private static G_ManagerDAO instance;
	public static G_ManagerDAO getInstance() {
		if(instance==null) {
			instance=new G_ManagerDAO();
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
	
	//�룊�젙 由ъ뒪�듃
	public ArrayList<ManagerGradeBean> gradeList(int startRow,int endRow,String category){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<ManagerGradeBean> arr = new ArrayList<>();
		String sql = "";
		
		try {
			con = getConnection();
			st = con.createStatement();
			if(category.equals("all")) {//�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿰춯節뗫뼠占쎌맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥堉ｅ뜝�럩逾쎾뜝�럩援�
				sql = "select * from (select rownum rn, aa.* from(select * from grade g,movie m where g.m_num=m.movie_num order by grade_num desc)aa) where rn >="+startRow+" and rn <="+endRow;
			}else {//�뇖�궠�뼠占쎌맶�뜝�럥�냺占썩벀�걠占쎄뎡 �뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥吏쀥뜝�럩援� �뜝�럥�맶�뜝�룞�삕�뜝�럩逾쎾뜝�럩援�
				sql = "select * from (select rownum rn, aa.* from(select * from grade g,movie m where g.m_num=m.movie_num and category = '"+category+"' order by grade_num desc)aa) where rn >="+startRow+" and rn <="+endRow;
				
			}
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ManagerGradeBean mgb = new ManagerGradeBean();
				mgb.setMoiveSubject(rs.getString("subject"));
				mgb.setGrade_num(rs.getInt("grade_num"));
				mgb.setM_num(rs.getInt("m_num"));
				mgb.setMembernum(rs.getInt("membernum"));
				mgb.setReply(rs.getString("reply"));
				mgb.setScore(rs.getInt("score"));
				mgb.setGrade_date(rs.getString("grade_date"));
				mgb.setIp(rs.getString("ip"));
				mgb.setGood(rs.getInt("good"));
				mgb.setBad(rs.getInt("bad"));
				arr.add(mgb);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return arr;
	}
	//�룊�젏由ъ뒪�듃 �궘�젣
	public void gradeDelete(int num) {
		Connection con = null;
		Statement st = null;
		
		try {
			con = getConnection();
			st = con.createStatement();
			String sql = "delete from grade where grade_num = "+num;
			st.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeCon(con, st);
		}
		
	}
	//�룊�젏寃뚯떆�뙋珥앷컻�닔
		public int gradeTotCount() {
			Connection con = null;
			Statement st = null;
			ResultSet rs = null;
			int count = 0;
			
			try {
				con = getConnection();
				st = con.createStatement();
				String sql = "select count(*) cnt from grade";
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
	
}
