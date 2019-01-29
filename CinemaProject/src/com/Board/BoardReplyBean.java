package com.Board;

public class BoardReplyBean {
	private int num;
	private int boardnum;
	private int membernum;
	private int ref;
	private int re_step;
	private int re_level;
	private String content;
	private String created;
	private String updated;
	private String ip;
	private String userid;
	public String getUserid() {
		return userid == null ? "" : userid.trim();
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getNum() {
		return num;
	}
	public int getBoardnum() {
		return boardnum;
	}
	public int getMembernum() {
		return membernum;
	}
	public int getRef() {
		return ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public String getContent() {
		return content == null ? "" : content.trim();
	}
	public String getCreated() {
		return created == null ? "" : created.trim();
	}
	public String getUpdated() {
		return updated == null ? "" : updated.trim();
	}
	public String getIp() {
		return ip == null ? "" : ip.trim();
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public void setMembernum(int membernum) {
		this.membernum = membernum;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
