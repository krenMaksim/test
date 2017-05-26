package com.mkren.building.service;

import java.sql.Date;
import java.util.List;

import com.mkren.building.bean.MagazineBean;

public interface MagazineService {
	public List<MagazineBean> getRecords(Date dateWith, Date dateOn, List<String> surnames);
	public List<String> surnameInitials();
}
