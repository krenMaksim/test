package com.mkren.building.service.factory;

import com.mkren.building.service.AvtorizationService;
import com.mkren.building.service.MagazineService;
import com.mkren.building.service.NewRecordService;
import com.mkren.building.service.RedactorService;
import com.mkren.building.service.impl.AvtorizationServiceImpl;
import com.mkren.building.service.impl.MagazineServiceImpl;
import com.mkren.building.service.impl.NewRecordServiceImpl;
import com.mkren.building.service.impl.RedactorServiceImpl;

public class ServiceFactoryImpl extends ServiceFactory {

	@Override
	public AvtorizationService getAvtorizationService() {
		return new AvtorizationServiceImpl();
	}

	@Override
	public MagazineService getMagazineService() {
		return new MagazineServiceImpl();
	}

	@Override
	public NewRecordService getNewRecordService() {
		return new NewRecordServiceImpl();
	}

	@Override
	public RedactorService getRedactorService() {
		return new RedactorServiceImpl();
	}
}
