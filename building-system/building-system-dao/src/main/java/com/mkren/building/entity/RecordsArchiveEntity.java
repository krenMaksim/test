package com.mkren.building.entity;

import java.sql.Date;

public class RecordsArchiveEntity extends AbstractEntity {

	private static final long serialVersionUID = 1L;	

	private Integer idMag;

	private Date date;

	private String nameColumn;
	
	private String oldRecord;
	
	private Integer idUser;

	public RecordsArchiveEntity() {
		super();
	}

	public Integer getIdMag() {
		return idMag;
	}

	public void setIdMag(Integer idMag) {
		this.idMag = idMag;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNameColumn() {
		return nameColumn;
	}

	public void setNameColumn(String nameColumn) {
		this.nameColumn = nameColumn;
	}

	public String getOldRecord() {
		return oldRecord;
	}

	public void setOldRecord(String oldRecord) {
		this.oldRecord = oldRecord;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idMag == null) ? 0 : idMag.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((nameColumn == null) ? 0 : nameColumn.hashCode());
		result = prime * result + ((oldRecord == null) ? 0 : oldRecord.hashCode());
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
		
		RecordsArchiveEntity other = (RecordsArchiveEntity) obj;
		
		if (date == null) {
			if (other.date != null){
				return false;
			}
		} else if (!date.equals(other.date)){
			return false;
		}
		
		if (idMag == null) {
			if (other.idMag != null){
				return false;
			}
		} else if (!idMag.equals(other.idMag)){
			return false;
		}
		
		if (idUser == null) {
			if (other.idUser != null){
				return false;
			}
		} else if (!idUser.equals(other.idUser)){
			return false;
		}
		
		if (nameColumn == null) {
			if (other.nameColumn != null){
				return false;
			}
		} else if (!nameColumn.equals(other.nameColumn)){
			return false;
		}
		
		if (oldRecord == null) {
			if (other.oldRecord != null){
				return false;
			}
		} else if (!oldRecord.equals(other.oldRecord)){
			return false;
		}
		
		return true;
	}
}