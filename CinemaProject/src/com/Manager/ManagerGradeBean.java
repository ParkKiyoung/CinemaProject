package com.Manager;

public class ManagerGradeBean {
	int grade_num,m_num,membernum,score,good,bad;
	String reply,grade_date,ip,moiveSubject;
	
	
	
	public String getMoiveSubject() {
		return moiveSubject;
	}
	public void setMoiveSubject(String moiveSubject) {
		this.moiveSubject = moiveSubject;
	}
	public int getGrade_num() {
		return grade_num;
	}
	public int getM_num() {
		return m_num;
	}
	public int getMembernum() {
		return membernum;
	}
	public int getScore() {
		return score;
	}
	public int getGood() {
		return good;
	}
	public int getBad() {
		return bad;
	}
	public String getReply() {
		return reply == null ? "" : reply.trim();
	}
	public String getGrade_date() {
		return grade_date == null ? "" : grade_date.trim();
	}
	public String getIp() {
		return ip == null ? "" : ip.trim();
	}
	public void setGrade_num(int grade_num) {
		this.grade_num = grade_num;
	}
	public void setM_num(int m_num) {
		this.m_num = m_num;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public void setGrade_date(String grade_date) {
		this.grade_date = grade_date;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
