package com.mkren.building.entity;

import java.sql.Date;

public class MagazineEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;	
	
	private Integer idSmeta;

	private String location;

	private Date date;
	
	private String smena;
	
	private String weather;
	
	private String conditions;
	
	private Double volume;
	
	private String controle;
	
	private Integer idUser;

	public MagazineEntity() {
		super();
	}

	public Integer getIdSmeta() {
		return idSmeta;
	}

	public void setIdSmeta(Integer idSmeta) {
		this.idSmeta = idSmeta;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getSmena() {
		return smena;
	}

	public void setSmena(String smena) {
		this.smena = smena;
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

	public Double getVolume() {
		return volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getControle() {
		return controle;
	}

	public void setControle(String controle) {
		this.controle = controle;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((conditions == null) ? 0 : conditions.hashCode());
		result = prime * result + ((controle == null) ? 0 : controle.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idSmeta == null) ? 0 : idSmeta.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((smena == null) ? 0 : smena.hashCode());
		result = prime * result + ((volume == null) ? 0 : volume.hashCode());
		result = prime * result + ((weather == null) ? 0 : weather.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		
		if (!super.equals(obj)){
			return false;
		}
		
		if (getClass() != obj.getClass()){
			return false;
		}
		
		MagazineEntity other = (MagazineEntity) obj;
		
		if (conditions == null) {
			if (other.conditions != null){
				return false;
			}
		} else if (!conditions.equals(other.conditions)){
			return false;
		}
		
		if (controle == null) {
			if (other.controle != null){
				return false;
			}
		} else if (!controle.equals(other.controle)){
			return false;
		}
		
		if (date == null) {
			if (other.date != null){
				return false;
			}
		} else if (!date.equals(other.date)){
			return false;
		}
		
		if (idSmeta == null) {
			if (other.idSmeta != null){
				return false;
			}
		} else if (!idSmeta.equals(other.idSmeta)){
			return false;
		}
		
		if (idUser == null) {
			if (other.idUser != null){
				return false;
			}
		} else if (!idUser.equals(other.idUser)){
			return false;
		}
		
		if (location == null) {
			if (other.location != null){
				return false;
			}
		} else if (!location.equals(other.location)){
			return false;
		}
		
		if (smena == null) {
			if (other.smena != null){
				return false;
			}
		} else if (!smena.equals(other.smena)){
			return false;
		}
		
		if (volume == null) {
			if (other.volume != null){
				return false;
			}
		} else if (!volume.equals(other.volume)){
			return false;
		}
		
		if (weather == null) {
			if (other.weather != null){
				return false;
			}
		} else if (!weather.equals(other.weather)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "MagazineEntity [id=" + getId() + ", idSmeta=" + idSmeta + ", location=" + location + ", date=" + date + ", smena=" + smena
				+ ", weather=" + weather + ", conditions=" + conditions + ", volume=" + volume + ", controle="
				+ controle + ", idUser=" + idUser + "]";
	}
}