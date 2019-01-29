package com.Board;

public class BoardBean {
	private int num;
	private String userid;
	private int membernum;
	private String subject;
	private String content;
	private String created;
	private int good;
	private int bad;
	private int readcount;
	private String ip;
	private String img;
	private String updated;
	private String category;
	private int subjectcount;
	private int goodcount;
	private int badcount;
	
	public int getSubjectcount() {
		return subjectcount;
	}
	public void setSubjectcount(int subjectcount) {
		this.subjectcount = subjectcount;
	}
	public int getGoodcount() {
		return goodcount;
	}
	public void setGoodcount(int goodcount) {
		this.goodcount = goodcount;
	}
	public int getBadcount() {
		return badcount;
	}
	public void setBadcount(int badcount) {
		this.badcount = badcount;
	}
	public String getCategory() {
		return category == null ? "" : category.trim();
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUpdated() {
		return updated == null ? "" : updated.trim();
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public int getNum() {
		return num;
	}
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public int getMembernum() {
		return membernum;
	}
	public String getSubject() {
		return subject == null ? "" : subject.trim();
	}
	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public String getCreated() {
		return created == null ? "" : created.trim();
	}
	public int getGood() {
		return good;
	}
	public int getBad() {
		return bad;
	}
	public int getReadcount() {
		return readcount;
	}
	public String getIp() {
		return ip == null ? "" : ip.trim();
	}
	public String getImg() {
		return img == null ? "" : img.trim();
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
}
