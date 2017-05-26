package com.mkren.building.service.factory;

import com.mkren.building.service.AvtorizationService;
import com.mkren.building.service.MagazineService;
import com.mkren.building.service.NewRecordService;
import com.mkren.building.service.RedactorService;

public abstract class ServiceFactory {

	public abstract AvtorizationService getAvtorizationService();

	public abstract MagazineService getMagazineService();

	public abstract NewRecordService getNewRecordService();
	
	public abstract RedactorService getRedactorService();

	public static ServiceFactory getFactory() {
		return new ServiceFactoryImpl();
	}
}
