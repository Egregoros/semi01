DROP TABLE POSTFILE;
DROP TABLE POSTIMG;
DROP TABLE POST;
DROP TABLE POSTCAT;
DROP TABLE CAFEBOARD;
DROP TABLE CAFEBOARDCAT;
DROP TABLE CAFEMEMBER;
DROP TABLE CAFEMEMGRADE;
DROP TABLE CAFELIST;
DROP TABLE CAFEGRADE;
DROP TABLE CAFECAT;
DROP TABLE MESSAGE;
DROP TABLE USERINFO;




--------------------------------------------------------------------------------------------

CREATE TABLE USERINFO 
	  (
	USERNUM NUMBER(3,0), 
	ID VARCHAR2(20) NOT NULL, 
	PWD VARCHAR2(20) NOT NULL, 
	NAME VARCHAR2(10) NOT NULL, 
	NICKNAME VARCHAR2(10) NOT NULL, 
	ADDR VARCHAR2(50), 
	EMAIL VARCHAR2(20), 
	BIRTH VARCHAR2(10), 
	PHONE VARCHAR2(20), 
	ISLIVE NUMBER NOT NULL
   	);

  ALTER TABLE USERINFO ADD CONSTRAINT USERINFO_PK PRIMARY KEY (USERNUM);
--------------------------------------------------------------------------------------------
CREATE TABLE MESSAGE 
	  (
	MESSNUM NUMBER, 
	RECUSERNUM NUMBER NOT NULL, 
	SENDUSERNUM NUMBER NOT NULL, 
	MESSTITLE VARCHAR2(30) NOT NULL, 
	MESSCONTENT VARCHAR2(100) NOT NULL, 
	SENDTIME DATE NOT NULL, 
	CHECKREAD VARCHAR2(20)
   	);

  ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_PK PRIMARY KEY (MESSNUM);
  
 ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_FK1 FOREIGN KEY (RECUSERNUM) REFERENCES USERINFO (USERNUM);
 ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_FK2 FOREIGN KEY (SENDUSERNUM) REFERENCES USERINFO (USERNUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFECAT
	 (
	CATNUM NUMBER, 
	CATNAME VARCHAR2(20) NOT NULL
   	);

  ALTER TABLE CAFECAT ADD CONSTRAINT "CAT_PK" PRIMARY KEY ("CATNUM");
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEGRADE 
   	(	
	GRADENUM NUMBER, 
	GRADENAME VARCHAR2(20) NOT NULL
  	);

ALTER TABLE CAFEGRADE ADD CONSTRAINT CAFEGRADE_PK PRIMARY KEY (GRADENUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFELIST
	(	
	CAFENUM NUMBER, 
	GRADENUM NUMBER NOT NULL, 
	CATNUM NUMBER NOT NULL, 
	CAFENAME VARCHAR2(20) NOT NULL , 
	USERNUM NUMBER NOT NULL,
	CAFEREGDATE DATE
   	);

ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_PK PRIMARY KEY (CAFENUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK2 FOREIGN KEY (CATNUM) REFERENCES CAFECAT(CATNUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK1 FOREIGN KEY (GRADENUM) REFERENCES CAFEGRADE(GRADENUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK3 FOREIGN KEY (USERNUM) REFERENCES USERINFO(USERNUM);
--------------------------------------------------------------------------------------------
 CREATE TABLE CAFEMEMGRADE 
   	(	
	CAFENUM NUMBER, 
	CAFEMEMGRADE NUMBER NOT NULL, 
	CAFEMEMGRADENAME VARCHAR2(20)
   	);

  ALTER TABLE CAFEMEMGRADE ADD CONSTRAINT CAFEMEMGRADE_PK PRIMARY KEY (CAFEMEMGRADE);

  ALTER TABLE CAFEMEMGRADE ADD CONSTRAINT CAFEMEMGRADE_FK1 FOREIGN KEY (CAFENUM) REFERENCES CAFELIST (CAFENUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEMEMBER 
	(
	USERNUM NUMBER, 
	CAFENUM NUMBER NOT NULL, 
	CAFEMEMNICK VARCHAR2(20), 
	CAFEMEMGRADE NUMBER,
	CAFEMEMREGDATE DATE
   	);

  ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_PK1 PRIMARY KEY (USERNUM);

 ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_FK1 FOREIGN KEY (USERNUM) REFERENCES USERINFO (USERNUM);
 ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_FK2 FOREIGN KEY (CAFENUM) REFERENCES CAFELIST (CAFENUM);
  ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_FK3 FOREIGN KEY (CAFEMEMGRADE) REFERENCES CAFEMEMGRADE (CAFEMEMGRADE);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEBOARDCAT 
	(
	BOARDCATNUM NUMBER, 
	CAFENUM NUMBER NOT NULL, 
	CATNAME VARCHAR2(30) NOT NULL, 
	CATORDER NUMBER NOT NULL
   	);

ALTER TABLE CAFEBOARDCAT ADD CONSTRAINT CAFEBOARDCAR_PK PRIMARY KEY (BOARDCATNUM);

ALTER TABLE CAFEBOARDCAT ADD CONSTRAINT CAFEBOARDCAT_FK1 FOREIGN KEY (CAFENUM) REFERENCES CAFELIST (CAFENUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEBOARD 
	(
	BOARDNUM NUMBER, 
	BOARDCATNUM NUMBER NOT NULL, 
	BOARDNAME VARCHAR2(30) NOT NULL, 
	ORDERNUM NUMBER NOT NULL
	);

	ALTER TABLE CAFEBOARD ADD CONSTRAINT BOARDNUM_PK PRIMARY KEY (BOARDNUM); 

  	ALTER TABLE CAFEBOARD ADD CONSTRAINT CAFEBOARD_FK1 FOREIGN KEY (BOARDCATNUM) REFERENCES CAFEBOARDCAT (BOARDCATNUM);

--------------------------------------------------------------------------------------------
 CREATE TABLE POSTCAT 
   	(	
	POSTCATNUM NUMBER, 
	POSTCATNAME VARCHAR2(15) NOT NULL
   	);

  ALTER TABLE POSTCAT ADD CONSTRAINT POSTCAT_PK PRIMARY KEY (POSTCATNUM);
--------------------------------------------------------------------------------------------
  CREATE TABLE POST 
   	(	
	POSTNUM NUMBER, 
	BOARDNUM NUMBER, 
	POSTTITLE VARCHAR2(30), 
	POSTCONTENT VARCHAR2(100), 
	POSTDATE DATE, 
	USERNUM NUMBER, 
	POSTCATNUM NUMBER NOT NULL
   	);

  ALTER TABLE POST ADD CONSTRAINT POST_PK PRIMARY KEY (POSTNUM);

	ALTER TABLE POST ADD CONSTRAINT POST_FK1 FOREIGN KEY (BOARDNUM) REFERENCES CAFEBOARD (BOARDNUM);
  	ALTER TABLE POST ADD CONSTRAINT POST_FK2 FOREIGN KEY (USERNUM) REFERENCES USERINFO (USERNUM);
  	ALTER TABLE POST ADD CONSTRAINT POST_FK3 FOREIGN KEY (POSTCATNUM) REFERENCES POSTCAT (POSTCATNUM);

--------------------------------------------------------------------------------------------
 CREATE TABLE POSTFILE 
   	(	
	FINENUM NUMBER, 
	POSTNUM NUMBER NOT NULL, 
	SAVEFILENAME VARCHAR2(40) NOT NULL, 
	ORGFILENAME VARCHAR2(30) NOT NULL
   	);

  	ALTER TABLE POSTFILE ADD CONSTRAINT POSTFILE_PK PRIMARY KEY (FINENUM);
	ALTER TABLE POSTFILE ADD CONSTRAINT POSTFILE_FK FOREIGN KEY (POSTNUM) REFERENCES POST (POSTNUM);
--------------------------------------------------------------------------------------------
  CREATE TABLE POSTIMG 
	 (	
	 IMGNUM NUMBER, 
	 POSTNUM NUMBER NOT NULL, 
	 SAVEIMGNAME VARCHAR2(30) NOT NULL,
	 ORGIMGNAME VARCHAR2(30) NOT NULL
   	);


  ALTER TABLE POSTIMG ADD CONSTRAINT POSTIMG_PK PRIMARY KEY (IMGNUM);

  ALTER TABLE POSTIMG ADD CONSTRAINT POSTIMG_FK FOREIGN KEY (POSTNUM) REFERENCES POST (POSTNUM);
--------------------------------------------------------------------------------------------
  
  
  
  
  
  
  
  
  
  
  
  -------------------------------------------------------------------------------------------
  insert into userinfo values(1,'test1','1234','테스트1','이름1','서울 종로구','test1@naver.com',20200725,01000000000,1);
  insert into userinfo values(2,'test2','1234','테스트2','이름2','서울 종로구','test1@naver.com',20200725,01000000000,1);
  insert into userinfo values(3,'test3','1234','테스트3','이름3','서울 종로구','test1@naver.com',20200725,01000000000,1);
  insert into userinfo values(4,'test4','1234','테스트4','이름4','서울 종로구','test1@naver.com',20200725,01000000000,1);
  insert into userinfo values(5,'test5','1234','테스트5','이름5','서울 종로구','test1@naver.com',20200725,01000000000,1);
  
  insert into message values(1,1,2,'오늘','오늘 춥다',sysdate,0);
  insert into message values(2,1,2,'오늘','오늘 춥다2',sysdate,0);
  insert into message values(3,1,2,'오늘','오늘 춥다3',sysdate,0);
  
  insert into cafecat values(1,'종합');
  insert into cafecat values(2,'게임');
  insert into cafecat values(3,'건강');
  insert into cafecat values(4,'취미');
  
  insert into cafegrade values(1,'새싹');
  insert into cafegrade values(2,'나무');
  insert into cafegrade values(3,'숲');

  insert into cafelist values(1,1,1,'테스트카페1',1,sysdate);
  insert into cafelist values(2,2,2,'테스트카페2',2,sysdate);
  insert into cafelist values(3,3,3,'테스트카페3',1,sysdate);
  
  insert into cafememgrade values(1,0,'비회원');
  insert into cafememgrade values(1,1,'관리자');
  insert into cafememgrade values(1,2,'회원');
  
  insert into cafemember values(1,1,'도도',1,sysdate);
  insert into cafemember values(2,1,'레레',2,sysdate);
  insert into cafemember values(3,1,'레레',2,sysdate);
  insert into cafemember values(4,1,'레레',2,sysdate);
  
  insert into cafeboardcat values(1,1,'필독',1);
  insert into cafeboardcat values(2,1,'ABOUT',3);
  insert into cafeboardcat values(3,1,'DATA',4);
  insert into cafeboardcat values(4,1,'서포트',2);
  
  insert into cafeboard values(1,1,'공지',1);
  insert into cafeboard values(2,1,'가입인사',2);
  insert into cafeboard values(3,2,'작품',1);
  insert into cafeboard values(4,2,'작품후기',2);
  insert into cafeboard values(5,3,'사진',1);
  insert into cafeboard values(6,3,'영상',2);
  
  insert into postcat values(0,'공지');
  insert into postcat values(1,'일반');
  
  insert into post values(1,1,'공지사항1','공지사항 내용1',sysdate,1,0);
  insert into post values(2,1,'공지사항2','공지사항 내용2',sysdate,1,0);
  insert into post values(3,1,'공지사항3','공지사항 내용3',sysdate,1,0);
  insert into post values(4,1,'공지사항4','공지사항 내용4',sysdate,1,0);
  insert into post values(5,1,'일반글1','일반글 내용2',sysdate,1,0);
  insert into post values(6,1,'일반글2','일반글 내용3',sysdate,1,0);
  insert into post values(7,1,'일반글3','일반글 내용4',sysdate,1,0);