package com.Movie;

import java.sql.Date;

public class MovieBean {
	private int num;
	private String subject;
	private String genre;
	private String director;
	private String actor;
	private String summary;
	private Date rel_date;
	private int age_res;
	private String playtime;
	private String img;
	private Double rel_rating;
	private Double on_rating;
	private int commentCount;
	

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Double getRel_rating() {
		return rel_rating;
	}

	public void setRel_rating(Double rel_rating) {
		this.rel_rating = rel_rating;
	}

	public Double getOn_rating() {
		return on_rating;
	}

	public void setOn_rating(Double on_rating) {
		this.on_rating = on_rating;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getRel_date() {
		return rel_date;
	}

	public void setRel_date(Date rel_date) {
		this.rel_date = rel_date;
	}

	public int getAge_res() {
		return age_res;
	}

	public void setAge_res(int age_res) {
		this.age_res = age_res;
	}

	public String getPlaytime() {
		return playtime;
	}

	public void setPlaytime(String playtime) {
		this.playtime = playtime;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
