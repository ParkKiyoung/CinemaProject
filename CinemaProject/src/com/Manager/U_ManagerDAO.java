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

import com.Grade.GradeBean;
import com.Member.MemberBean;
import com.Member.ZipcodeBEAN;

public class U_ManagerDAO {
	private static U_ManagerDAO instance;
	public static U_ManagerDAO getInstance() {
		if(instance==null) {
			instance=new U_ManagerDAO();
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
	public void U_Insert(MemberBean mb) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = getConnection();
			sql = "insert into member values(member_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
	}
	public int U_IdCheck(String U_Id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int flag = 0;
		try {
			con = getConnection();
			String sql = "select * from member where userid ='"+U_Id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				flag=1;
				return flag;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con,st,rs);
		}
		return flag;
	}
	public ArrayList<ZipcodeBEAN> U_ZipSearch(String dong){
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
	public ArrayList<MemberBean> U_Update_List(int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MemberBean> arr = new ArrayList();
		try {
			con = getConnection();
			sql = "select * from (select rownum rn, aa.* from "
					+ "(select * from (select * from member order by member_num desc)) aa) "
					+ "where rn >= ? and rn <= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();

			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setNum(rs.getInt("member_num"));
				mb.setUserid(rs.getString("userid"));
				mb.setName(rs.getString("name"));
				mb.setPassword(rs.getString("password"));
				mb.setPnum(rs.getString("pnum"));
				mb.setPhone(rs.getString("phone"));
				mb.setEmail(rs.getString("email"));
				arr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
		return arr;
	}
	public MemberBean U_Update_window(int U_Num){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		MemberBean mb = null;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select * from member where member_num="+U_Num;
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mb = new MemberBean();
				mb.setNum(rs.getInt("member_num"));
				mb.setUserid(rs.getString("userid"));
				mb.setName(rs.getString("name"));
				mb.setPassword(rs.getString("password"));
				mb.setPnum(rs.getString("pnum"));
				mb.setPhone(rs.getString("phone"));
				mb.setEmail(rs.getString("email"));
				mb.setZipcode(rs.getString("zipcode"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setAddr2(rs.getString("addr2"));
				mb.setGender(rs.getString("gender"));
				mb.setInter1(rs.getString("inter1"));
				mb.setInter2(rs.getString("inter2"));
				mb.setInter3(rs.getString("inter3"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return mb;
	}
	public void U_Update_Go(MemberBean mb, int U_Num) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			con = getConnection();
			sql = "update member set userid=?, password=?, name=?, pnum=?, phone=?, "
					+ "email=?, zipcode=?, addr1=?, addr2=?, gender=?, inter1=?,"
					+ "inter2=?, inter3=? where member_num="+U_Num;
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
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
	}
	public int U_Update_List_Count() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select count(*) from member";
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
	public ArrayList<MemberBean> U_Search_Do(String U_Id, int startRow, int endRow){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList<MemberBean> arr = new ArrayList();
		try {
			con = getConnection();
			sql = "select * from (select rownum rn, aa.* from "
					+ "(select * from (select * from member where userid like '%" + U_Id + "%' "
					+ "order by member_num desc)) aa) "
					+ "where rn >= ? and rn <= ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, startRow);
			ps.setInt(2, endRow);
			rs = ps.executeQuery();

			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setNum(rs.getInt("member_num"));
				mb.setUserid(rs.getString("userid"));
				mb.setName(rs.getString("name"));
				mb.setPassword(rs.getString("password"));
				mb.setPnum(rs.getString("pnum"));
				mb.setPhone(rs.getString("phone"));
				mb.setEmail(rs.getString("email"));
				mb.setZipcode(rs.getString("zipcode"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setAddr2(rs.getString("addr2"));
				mb.setGender(rs.getString("gender"));
				mb.setInter1(rs.getString("inter1"));
				mb.setInter2(rs.getString("inter2"));
				mb.setInter3(rs.getString("inter3"));
				arr.add(mb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,ps,rs);
		}
		return arr;
	}
	public int U_Search_List_Count(String U_Id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select count(*) from member where userid like '%" + U_Id + "%'";
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
	public void U_Delete(int U_Num, String U_Id) {
		Connection con = null;
		Statement st = null;
		String sql = "";
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "delete from member where member_num=" + U_Num + " and userid='" + U_Id + "'";
			st.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st);
		}
	}
	public int U_Id_Info_Grade(String U_NUM){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int Gcnt = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select count(g.reply) from grade g, member m where g.membernum=m.member_num and m.member_num='"+U_NUM+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				Gcnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return Gcnt;
	}
	public int U_Id_Info_Board(String U_NUM){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		int Bcnt = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select count(b.content) from board b, member m where b.membernum=m.member_num and m.member_num='"+U_NUM+"'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				Bcnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return Bcnt;
	}
	public double U_Info_Avg_Score(String U_NUM){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		double Avg_Score = 0;
		try {
			con = getConnection();
			st = con.createStatement();
			sql = "select avg(g.score) from grade g, member m "
					+ "where m.member_num=g.membernum and m.member_num='" + U_NUM + "'";
			rs = st.executeQuery(sql);
			if(rs.next()) {
				Avg_Score = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return Avg_Score;
	}
	public U_Age_Bean U_Age_Graph() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		U_Age_Bean uab = null;
		int i = 1;
		try {
			con = getConnection();
			st = con.createStatement();
			uab = new U_Age_Bean();
			for(i=1;i<10;i++) {
				sql = "select count(*) "
						+ "from member "
						+ "where (substr(((substr(sysdate,1,2)-substr(pnum,3,2))+101),1,1)) = " + i + " "
						+ "group by (substr(((substr(sysdate,1,2)-substr(pnum,3,2))+101),1,1)) "
						+ "order by (substr(((substr(sysdate,1,2)-substr(pnum,3,2))+101),1,1))";
				rs = st.executeQuery(sql);
				if(rs.next()) {
					if(i==1) {
						uab.setAge10(rs.getInt(1));
					}
					else if(i==2) {
						uab.setAge20(rs.getInt(1));
					}
					else if(i==3) {
						uab.setAge30(rs.getInt(1));
					}
					else if(i==4) {
						uab.setAge40(rs.getInt(1));
					}
					else if(i==5) {
						uab.setAge50(rs.getInt(1));
					}
					else if(i==6) {
						uab.setAge60(rs.getInt(1));
					}
					else if(i==7) {
						uab.setAge70(rs.getInt(1));
					}
					else if(i==8) {
						uab.setAge80(rs.getInt(1));
					}
					else if(i==9) {
						uab.setAge90(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return uab;
	}
	public U_Gender_Bean U_Gender_Graph() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		U_Gender_Bean ugb = null;
		try {
			ugb = new U_Gender_Bean();
			con = getConnection();
			st = con.createStatement();
			for(int i=0;i<2;i++) {
				if(i==0) {
					sql = "select count(*) from member where gender = 'male'";
					rs = st.executeQuery(sql);
					if(rs.next()) {
						ugb.setMale(rs.getInt(1));
					}
				}
				else if(i==1) {
					sql = "select count(*) from member where gender = 'female'";
					rs = st.executeQuery(sql);
					if(rs.next()) {
						ugb.setFemale(rs.getInt(1));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return ugb;
	}
	public U_Genre_Bean U_Genre_Graph() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = "";
		U_Genre_Bean U_Genre_Bean = null;
		try {
			U_Genre_Bean = new U_Genre_Bean();
			con = getConnection();
			st = con.createStatement();
			for (int i=1;i<16;i++) {
				if(i==1) {
					sql = "select count(inter1)+count(inter2)+count(inter3) " + 
							"from member " + 
							"where inter1 is not null and inter2 is not null and inter3 is not null";
					rs = st.executeQuery(sql);
					if(rs.next()) {
						U_Genre_Bean.setT_Genre(rs.getInt(1));
					}
				}
				if(i==2) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='스포츠'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setSport(inter);
				}
				if(i==3) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='범죄'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setCrime(inter);
				}
				if(i==4) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='드라마'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setDrama(inter);
				}
				if(i==5) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='다큐멘터리'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setComedy(inter);
				}
				if(i==6) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='코미디'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setRomance(inter);
				}
				if(i==7) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='스릴러'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setThriller(inter);
				}
				if(i==8) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='전쟁'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setRomance_comedy(inter);
				}
				if(i==9) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='애니메이션'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setMilitary(inter);
				}
				if(i==10) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='가족'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setFamily(inter);
				}
				if(i==11) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='판타지'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setFantasy(inter);
				}
				if(i==12) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='액션'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setAction(inter);
				}
				if(i==13) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='SF'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setSf(inter);
				}
				if(i==14) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='로맨스/멜로'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setAnime(inter);
				}
				if(i==15) {
					double inter = 0;
					for(int j=1;j<4;j++) {
						sql = "select count(inter"+j+") " + 
								"from member " + 
								"where inter"+j+" is not null " + 
								"and inter"+j+"='로맨스/코미디'";
						rs = st.executeQuery(sql);
						if(rs.next()) {
							inter += rs.getDouble(1);
						}
					}
					U_Genre_Bean.setDocumentary(inter);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con,st,rs);
		}
		return U_Genre_Bean;
	}
	public MemberBean UserActDetail(String u_Id) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		try {
			con = getConnection();
			String sql= "select * from member where member_num='"+u_Id+"'";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()) {
				mb.setUserid(rs.getString("userid"));
				mb.setName(rs.getString("name"));
				mb.setZipcode(rs.getString("zipcode"));
				mb.setAddr1(rs.getString("addr1"));
				mb.setAddr2(rs.getString("addr2"));
				mb.setInter1(rs.getString("inter1"));
				mb.setInter2(rs.getString("inter2"));
				mb.setInter3(rs.getString("inter3"));
				mb.setNum(rs.getInt("member_num"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeCon(con, st, rs);
		}
		return mb;
	}
	public int U_Info_Res_Count(String U_NUM) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		int cnt = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from booking where membernum="+U_NUM;
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
}
