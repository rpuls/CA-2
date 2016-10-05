INSERT INTO CITYINFO (ZIP, CITY) VALUES ('2950', 'Vedbæk');
INSERT INTO CITYINFO (ZIP, CITY) VALUES ('1006', 'København K');
INSERT INTO CITYINFO (ZIP, CITY) VALUES ('1021', 'København K');
INSERT INTO CITYINFO (ZIP, CITY) VALUES ('1215', 'København K');	

INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (1,'Sofievej','2950','abc');
INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (2,'Poulsvej','1006','def');
INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (3,'Norgaardsvej','1021','ghi');
INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (4,'Strandvej','1215','klm');
INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (5,'Ghetto','1215','Non');
INSERT INTO address (id,Street,CityInfo_id,AdditionalInfo) VALUES (6,'HighWay','1215','Non');

INSERT INTO INFOENTITY (EMAIL,DTYPE, ADRESS_id) VALUES ('cjs@email.dk','Person',1);
INSERT INTO INFOENTITY (EMAIL,DTYPE, ADRESS_id) VALUES ('w@email.dk','Person',2);
INSERT INTO INFOENTITY (EMAIL,DTYPE, ADRESS_id) VALUES ('dh@email.dk','Person', 3);
INSERT INTO INFOENTITY (EMAIL,DTYPE ,ADRESS_id ) VALUES ('r@email.dk','Person', 4);
INSERT INTO INFOENTITY (EMAIL,DTYPE ,ADRESS_id ) VALUES ('CompanyA@gmail.dk','Company', 5);
INSERT INTO INFOENTITY (EMAIL,DTYPE ,ADRESS_id ) VALUES ('CompanyB@email.dk','Company', 6);

INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME) VALUES (1,'cherry','aa');
INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME) VALUES (2,'daniel','bb');
INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME) VALUES (3,'rasmus','cc');
INSERT INTO PERSON (ID,FIRSTNAME,LASTNAME) VALUES (4,'waqas','ddd');

INSERT INTO COMPANY (ID,NAME,DESCRIPTION,CVR,NUMEMPLOYEES,MARKETVALUE) VALUES (5,'Company A','a company','1234','2','123');
INSERT INTO COMPANY (ID,NAME,DESCRIPTION,CVR,NUMEMPLOYEES,MARKETVALUE) VALUES (6,'Company B','a firm','4567','2','456');

INSERT INTO hobby (id,description,name) VALUES (1,'board games','playing chess');
INSERT INTO hobby (id,description,name) VALUES (2,'computer games','race');
INSERT INTO phone (id,numer,description,infoentity_ID) VALUES (1,'123456789','home',1);
INSERT INTO phone (id,numer,description,infoentity_ID) VALUES (2,'987654321','office',2);
INSERT INTO phone (id,numer,description,infoentity_ID) VALUES (3,'012345678','home',3);
INSERT INTO phone (id,numer,description,infoentity_ID) VALUES (4,'234567890','home',4);