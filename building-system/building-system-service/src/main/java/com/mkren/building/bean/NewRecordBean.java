package com.mkren.building.bean;

import java.io.Serializable;
import java.sql.Date;

public class NewRecordBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String smena;
	private Date date;
	private Integer ppSmeta;
	private String location;
	private String weather;
	private String conditions;
	private Double kolVo;
	private String controle;
	private Integer userId;

	public NewRecordBean() {
		super();
	}

	public String getSmena() {
		return smena;
	}

	public void setSmena(String smena) {
		this.smena = smena;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPpSmeta() {
		return ppSmeta;
	}

	public void setPpSmeta(Integer ppSmeta) {
		this.ppSmeta = ppSmeta;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public Double getKolVo() {
		return kolVo;
	}

	public void setKolVo(Double kolVo) {
		this.kolVo = kolVo;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "NewRecordBean [smena=" + smena + ", date=" + date + ", ppSmeta=" + ppSmeta + ", location=" + location
				+ ", weather=" + weather + ", conditions=" + conditions + ", kolVo=" + kolVo + ", controle=" + controle
				+ ", userId=" + userId + "]";
	}
	
}
