package com.Grade;

public class GradeBean {
	int G_Num, Movie_Num, Member_Num, Good, Bad;
	double score;
	String Reply,Ip,UserId,MovieTitle,Grade_Date,Genre,
			Director,Actor,On_Rating,Rel_Rating,Img,Rel_date,
			Playtime;
	
	
	
	
	
	public String getPlaytime() {
		return Playtime;
	}
	public void setPlaytime(String playtime) {
		Playtime = playtime;
	}
	public String getRel_date() {
		return Rel_date;
	}
	public void setRel_date(String rel_date) {
		Rel_date = rel_date;
	}
	public String getGenre() {
		return Genre;
	}
	public String getDirector() {
		return Director;
	}
	public String getActor() {
		return Actor;
	}
	public String getOn_Rating() {
		return On_Rating;
	}
	public String getRel_Rating() {
		return Rel_Rating;
	}
	public String getImg() {
		return Img;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public void setDirector(String director) {
		Director = director;
	}
	public void setActor(String actor) {
		Actor = actor;
	}
	public void setOn_Rating(String on_Rating) {
		On_Rating = on_Rating;
	}
	public void setRel_Rating(String rel_Rating) {
		Rel_Rating = rel_Rating;
	}
	public void setImg(String img) {
		Img = img;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public int getGood() {
		return Good;
	}
	public void setGood(int good) {
		Good = good;
	}
	public int getBad() {
		return Bad;
	}
	public void setBad(int bad) {
		Bad = bad;
	}
	public int getG_Num() {
		return G_Num;
	}
	public String getGrade_Date() {
		return Grade_Date == null ? "" : Grade_Date.trim();
	}
	public void setGrade_Date(String grade_Date) {
		Grade_Date = grade_Date;
	}
	public void setG_Num(int g_Num) {
		G_Num = g_Num;
	}
	public String getUserId() {
		return UserId == null ? "" : UserId.trim();
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getMovieTitle() {
		return MovieTitle == null ? "" : MovieTitle.trim();
	}
	public void setMovieTitle(String movieTitle) {
		MovieTitle = movieTitle;
	}
	public int getMovie_Num() {
		return Movie_Num;
	}
	public void setMovie_Num(int movie_Num) {
		Movie_Num = movie_Num;
	}
	public int getMember_Num() {
		return Member_Num;
	}
	public void setMember_Num(int member_Num) {
		Member_Num = member_Num;
	}
	public String getReply() {
		return Reply == null ? "" : Reply.trim();
	}
	public void setReply(String reply) {
		Reply = reply;
	}
	public String getIp() {
		return Ip == null ? "" : Ip.trim();
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	
}
