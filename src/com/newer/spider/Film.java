package com.newer.spider;

public class Film implements Comparable<Film>{

	// 排名，图片，电影名，信息，评分，观众人数，评语。
	String id, poster, title, grade, audience, remark;

	String director, protagonist, type, showTime, time;

	@Override
	public String toString() {
		
		return "Film 排名:" + id + "\n电影名称:" + title + "\n导演:" + director + "\n主演:" + protagonist
				+ "\n评分:" + grade + "\n评分人数:" + audience + "\n类型:" + type + "\n上映日期:" + showTime
				+ "\n片长:" + time + "\n评语:" + remark + "\n===============================================";
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProtagonist() {
		return protagonist;
	}

	public void setProtagonist(String protagonist) {
		this.protagonist = protagonist;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public int compareTo(Film o) {
		// TODO Auto-generated method stub
		return Integer.parseInt(id)-Integer.parseInt(o.id);
	}

}
