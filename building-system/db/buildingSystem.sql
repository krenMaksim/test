DROP SCHEMA IF EXISTS building_system;

CREATE SCHEMA IF NOT EXISTS building_system
CHARACTER SET 'utf8';

USE building_system;

/*создаем таблицу пользователей*/

CREATE TABLE users(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,  
	login VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL,
	surname_initials VARCHAR(150) NOT NULL,
	role ENUM('заказчик', 'гл.инженер', 'прораб', 'тех.надзор', 'гость') NOT NULL,
	del_status ENUM('активен', 'банн', 'удален') default 'активен'
);

INSERT INTO building_system.users (login, password, surname_initials, role) VALUES ('Petrov','dfssf35','Петров И.И.','заказчик');
INSERT INTO building_system.users (login, password, surname_initials, role) VALUES ('Sidor','lkljsdf95','Сидоров В.К.','гл.инженер');
INSERT INTO building_system.users (login, password, surname_initials, role) VALUES ('Gleb','dfsdflj','Глебов М.М.','прораб');
INSERT INTO building_system.users (login, password, surname_initials, role) VALUES ('Krivor','df56kl','Криворуков И.Г.','прораб');

/*создаем таблицу смет*/

CREATE TABLE smeta(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,  
	pp INTEGER NOT NULL,
	obosnovanie VARCHAR(30) NOT NULL,
	naimenovanie VARCHAR(500) NOT NULL,
	ed_izm VARCHAR(30) NOT NULL,
	kol_vo DOUBLE NOT NULL
);

INSERT INTO building_system.smeta (pp, obosnovanie, naimenovanie, ed_izm, kol_vo) VALUES (2,'Е11-2-1','УСТРОЙСТВО УПЛОТНЯЕМЫХ ТРАМБОВКАМИ ПОДСТИЛАЮЩИХ СЛОЕВ ПЕСЧАНЫХ','М3',188.8);
INSERT INTO building_system.smeta (pp, obosnovanie, naimenovanie, ed_izm, kol_vo) VALUES (3,'Е12-2-122','УСТРОЙСТВО ЛЕНТОЧНЫХ ФУНДАМЕНТОВ ЖЕЛЕЗОБЕТОННЫХ ИЗ БЕТОНА КЛАССА С12/15','М3',444.3);
INSERT INTO building_system.smeta (pp, obosnovanie, naimenovanie, ed_izm, kol_vo) VALUES (22,'Е11-3-1','УСТРОЙСТВО БЕТОННОЙ ПОДГОТОВКИ ИЗ БЕТОНА КЛАССА С10.12','М3',10.5);

/*создаем таблицу журнала работ*/

CREATE TABLE magazine(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_smeta INTEGER default NULL,
	location VARCHAR(100) default NULL,
	date_ DATE NOT NULL,
	smena ENUM('1', '2', '3') default '1',
	weather VARCHAR(100) default NULL,	
	conditions VARCHAR(100) default NULL,	
	volume DOUBLE default NULL,
	controle VARCHAR(150) default NULL,
	id_user INTEGER NOT NULL,
	
	CONSTRAINT smeta_id_fk FOREIGN KEY (id_smeta) REFERENCES smeta (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,

	CONSTRAINT users_id_fk FOREIGN KEY (id_user) REFERENCES users (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT		
);

INSERT INTO building_system.magazine (id_smeta, location, date_, weather, volume, controle, id_user) VALUES (2, 'в/о А-Б;1-5 на отм. +5.000','2017-03-21', 'ясно 25 С', 50, 'соответствует', 3);
INSERT INTO building_system.magazine (id_smeta, location, date_, weather, volume, controle, id_user) VALUES (2, 'в/о С-Д;2-3 на отм. 0.000','2017-02-21', 'дождь 5 С', 25, 'соответствует', 4);
INSERT INTO building_system.magazine (id_smeta, location, date_, weather, volume, controle, id_user) VALUES (3, 'в/о С-Д;6-7 на отм. +2.000','2017-01-19', 'снег -5 С', 5.5, 'соответствует', 4);

/*создаем таблицу архив записей*/

CREATE TABLE records_archive(
	id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_mag INTEGER NOT NULL,
	date_ DATE NOT NULL,
	name_column ENUM('id_smeta', 'location', 'obosnovanie', 'date_', 'smena', 'weather', 'conditions', 'volume', 'controle', 'id_user') NOT NULL,
	old_record VARCHAR(500) default NULL,
	id_user INTEGER NOT NULL,
	
	CONSTRAINT magazine_id_fk FOREIGN KEY (id_mag) REFERENCES magazine (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,

	CONSTRAINT user_id_fk FOREIGN KEY (id_user) REFERENCES users (id)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT	
);

INSERT INTO building_system.records_archive (id_mag, date_, name_column, old_record, id_user) VALUES (1, '2017-03-25','location', 'в/о С-Д;2-3 на отм. 0.000', 2);
INSERT INTO building_system.records_archive (id_mag, date_, name_column, old_record, id_user) VALUES (2, '2017-04-05','date_', '2017-01-15', 2);
INSERT INTO building_system.records_archive (id_mag, date_, name_column, old_record, id_user) VALUES (2, '2017-03-30','volume', '15', 2);