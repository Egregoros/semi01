DROP TABLE POSTFILE;
DROP TABLE POSTIMG;
DROP TABLE POSTCOMMENT;
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
DROP TABLE CAFEMAINPIC;




--------------------------------------------------------------------------------------------

CREATE TABLE USERINFO 
	  (
	USERNUM NUMBER(3,0), 
	ID VARCHAR2(30) NOT NULL UNIQUE, 
	PWD VARCHAR2(30) NOT NULL, 
	NAME VARCHAR2(10) NOT NULL, 
	NICKNAME VARCHAR2(30) NOT NULL UNIQUE, 
	ADDR VARCHAR2(100) NOT NULL, 
	EMAIL VARCHAR2(100) NOT NULL, 
	BIRTH DATE NOT NULL, 
	PHONE VARCHAR2(30) NOT NULL UNIQUE, 
	ISLIVE NUMBER NOT NULL
   	);

  ALTER TABLE USERINFO ADD CONSTRAINT USERINFO_PK PRIMARY KEY (USERNUM);
--------------------------------------------------------------------------------------------
CREATE TABLE MESSAGE 
	  (
	MESSNUM NUMBER NOT NULL, 
	RECUSERNUM NUMBER NOT NULL, 
	SENDUSERNUM NUMBER NOT NULL, 
	MESSTITLE VARCHAR2(30) NOT NULL, 
	MESSCONTENT VARCHAR2(1000) NOT NULL, 
	SENDTIME DATE NOT NULL, 
	CHECKREAD VARCHAR2(30) NOT NULL
   	);

  ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_PK PRIMARY KEY (MESSNUM);
  
 ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_FK1 FOREIGN KEY (RECUSERNUM) REFERENCES USERINFO (USERNUM);
 ALTER TABLE MESSAGE ADD CONSTRAINT MESSAGE_FK2 FOREIGN KEY (SENDUSERNUM) REFERENCES USERINFO (USERNUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFECAT
	 (
	CATNUM NUMBER NOT NULL, 
	CATNAME VARCHAR2(30) NOT NULL UNIQUE
   	);

  ALTER TABLE CAFECAT ADD CONSTRAINT CAFECAT_PK PRIMARY KEY (CATNUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEGRADE 
   	(	
	GRADENUM NUMBER NOT NULL, 
	GRADENAME VARCHAR2(30) NOT NULL UNIQUE
  	);

ALTER TABLE CAFEGRADE ADD CONSTRAINT CAFEGRADE_PK PRIMARY KEY (GRADENUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFELIST
	(	
	CAFENUM NUMBER NOT NULL, 
	GRADENUM NUMBER NOT NULL, 
	CATNUM NUMBER NOT NULL, 
	CAFENAME VARCHAR2(30) NOT NULL UNIQUE, 
	USERNUM NUMBER NOT NULL,
	CAFEREGDATE DATE NOT NULL
   	);

ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_PK PRIMARY KEY (CAFENUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK2 FOREIGN KEY (CATNUM) REFERENCES CAFECAT(CATNUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK1 FOREIGN KEY (GRADENUM) REFERENCES CAFEGRADE(GRADENUM);
ALTER TABLE CAFELIST ADD CONSTRAINT CAFELIST_FK3 FOREIGN KEY (USERNUM) REFERENCES USERINFO(USERNUM);
--------------------------------------------------------------------------------------------
 CREATE TABLE CAFEMEMGRADE 
   	(	
	CAFENUM NUMBER NOT NULL, 
	CAFEMEMGRADENUM NUMBER NOT NULL, 
	CAFEMEMGRADENAME VARCHAR2(30) NOT NULL
   	);

  ALTER TABLE CAFEMEMGRADE ADD CONSTRAINT CAFEMEMGRADE_PK PRIMARY KEY (CAFENUM,CAFEMEMGRADENUM);

  ALTER TABLE CAFEMEMGRADE ADD CONSTRAINT CAFEMEMGRADE_FK1 FOREIGN KEY (CAFENUM) REFERENCES CAFELIST (CAFENUM);
--------------------------------------------------------------------------------------------
CREATE TABLE CAFEMEMBER 
	(
	USERNUM NUMBER NOT NULL, 
	CAFENUM NUMBER NOT NULL, 
	CAFEMEMNICK VARCHAR2(30) NOT NULL, 
	CAFEMEMGRADENUM NUMBER NOT NULL,
	CAFEINVITECOUNT NUMBER NOT NULL,
	CAFEMEMREGDATE DATE NOT NULL
   	);

  ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_PK1 PRIMARY KEY (USERNUM,CAFENUM);
  ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_FK1 FOREIGN KEY (USERNUM) REFERENCES USERINFO (USERNUM);
  ALTER TABLE CAFEMEMBER ADD CONSTRAINT CAFEMEMBER_FK3 FOREIGN KEY (CAFENUM, CAFEMEMGRADENUM) REFERENCES CAFEMEMGRADE (CAFENUM, CAFEMEMGRADENUM);
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
	BOARDNUM NUMBER NOT NULL,
	CAFEBOARDNUM NUMBER NOT NULL,
	BOARDCATNUM NUMBER NOT NULL, 
	BOARDNAME VARCHAR2(30) NOT NULL,
	USEGRADE NUMBER NOT NULL,
	ORDERNUM NUMBER NOT NULL
	);

	ALTER TABLE CAFEBOARD ADD CONSTRAINT BOARDNUM_PK PRIMARY KEY (BOARDNUM); 

  	ALTER TABLE CAFEBOARD ADD CONSTRAINT CAFEBOARD_FK1 FOREIGN KEY (BOARDCATNUM) REFERENCES CAFEBOARDCAT (BOARDCATNUM);

--------------------------------------------------------------------------------------------
 CREATE TABLE POSTCAT 
   	(	
	POSTCATNUM NUMBER NOT NULL, 
	POSTCATNAME VARCHAR2(15) NOT NULL 
   	);

  ALTER TABLE POSTCAT ADD CONSTRAINT POSTCAT_PK PRIMARY KEY (POSTCATNUM);
--------------------------------------------------------------------------------------------
  CREATE TABLE POST 
   	(	
	POSTNUM NUMBER NOT NULL,
	CAFEPOSTNUM NUMBER NOT NULL,
	BOARDNUM NUMBER NOT NULL,
	POSTTITLE VARCHAR2(100) NOT NULL, 
	POSTCONTENT VARCHAR2(1000) NOT NULL, 
	POSTDATE DATE NOT NULL, 
	USERNUM NUMBER NOT NULL, 
	POSTCATNUM NUMBER NOT NULL,
	POSTINVITECOUNT NUMBER NOT NULL
   	);

  ALTER TABLE POST ADD CONSTRAINT POST_PK PRIMARY KEY (POSTNUM);

	ALTER TABLE POST ADD CONSTRAINT POST_FK1 FOREIGN KEY (BOARDNUM) REFERENCES CAFEBOARD (BOARDNUM);
  	ALTER TABLE POST ADD CONSTRAINT POST_FK2 FOREIGN KEY (USERNUM) REFERENCES USERINFO (USERNUM);
  	ALTER TABLE POST ADD CONSTRAINT POST_FK3 FOREIGN KEY (POSTCATNUM) REFERENCES POSTCAT (POSTCATNUM);

--------------------------------------------------------------------------------------------
 CREATE TABLE POSTFILE 
   	(	
	FINENUM NUMBER NOT NULL, 
	POSTNUM NUMBER NOT NULL, 
	SAVEFILENAME VARCHAR2(100) NOT NULL, 
	ORGFILENAME VARCHAR2(100) NOT NULL
   	);

  	ALTER TABLE POSTFILE ADD CONSTRAINT POSTFILE_PK PRIMARY KEY (FINENUM);
	ALTER TABLE POSTFILE ADD CONSTRAINT POSTFILE_FK FOREIGN KEY (POSTNUM) REFERENCES POST (POSTNUM);
--------------------------------------------------------------------------------------------
  CREATE TABLE POSTIMG 
	 (	
	 IMGNUM NUMBER NOT NULL, 
	 POSTNUM NUMBER NOT NULL, 
	 SAVEIMGNAME VARCHAR2(100) NOT NULL,
	 ORGIMGNAME VARCHAR2(100) NOT NULL
   	);


  ALTER TABLE POSTIMG ADD CONSTRAINT POSTIMG_PK PRIMARY KEY (IMGNUM);

  ALTER TABLE POSTIMG ADD CONSTRAINT POSTIMG_FK FOREIGN KEY (POSTNUM) REFERENCES POST (POSTNUM);
--------------------------------------------------------------------------------------------
  CREATE TABLE POSTCOMMENT
    (	
	 COMMENTNUM NUMBER NOT NULL, 
	 POSTNUM NUMBER NOT NULL, 
	 USERNUM NUMBER NOT NULL,
	 POSTCOMMENT VARCHAR2(1000) NOT NULL,
	 COMMENTREGDATE DATE NOT NULL
   	);


  ALTER TABLE POSTCOMMENT ADD CONSTRAINT COMMENT_PK PRIMARY KEY (COMMENTNUM);

  ALTER TABLE POSTCOMMENT ADD CONSTRAINT COMMENT_FK1 FOREIGN KEY (POSTNUM) REFERENCES POST (POSTNUM);
  ALTER TABLE POSTCOMMENT ADD CONSTRAINT COMMENT_FK2 FOREIGN KEY (USERNUM) REFERENCES USERINFO (USERNUM);
--------------------------------------------------------------------------------------------
  
  create table cafeMainPic
(
	cafePicNum number primary key,
	cafenum number not null,
	orgfilename varchar2(150),
	savefilename varchar2(150),
	filesize number,
    constraint fk_cafenum foreign key (cafenum) references cafelist(cafenum)
);
create sequence fileinfo_seq;

------------------------------------------------------------------------------------------------
  
  
  
  
  
  
  
  
  
  -------------------------------------------------------------------------------------------
  INSERT INTO USERINFO VALUES(1,'TEST1','1234','테스트1','이름1','서울 종로구','TEST1@NAVER.COM','19960725',01000000001,1);
  INSERT INTO USERINFO VALUES(2,'TEST2','1234','테스트2','이름2','서울 종로구','TEST1@NAVER.COM','19960725',01000000002,1);
  INSERT INTO USERINFO VALUES(3,'TEST3','1234','테스트3','이름3','서울 종로구','TEST1@NAVER.COM','19960725',01000000003,1);
  INSERT INTO USERINFO VALUES(4,'TEST4','1234','테스트4','이름4','서울 종로구','TEST1@NAVER.COM','19960725',01000000004,1);
  INSERT INTO USERINFO VALUES(5,'TEST5','1234','테스트5','이름5','서울 종로구','TEST1@NAVER.COM','19960725',01000000005,1);
  
  INSERT INTO MESSAGE VALUES(1,1,2,'오늘','오늘 춥다',SYSDATE,0);
  INSERT INTO MESSAGE VALUES(2,1,2,'오늘','오늘 춥다2',SYSDATE,0);
  INSERT INTO MESSAGE VALUES(3,1,2,'오늘','오늘 춥다3',SYSDATE,0);
  
  INSERT INTO CAFECAT VALUES(1,'종합');
  INSERT INTO CAFECAT VALUES(2,'게임');
  INSERT INTO CAFECAT VALUES(3,'건강');
  INSERT INTO CAFECAT VALUES(4,'취미');
  
  INSERT INTO CAFEGRADE VALUES(1,'숲');
  INSERT INTO CAFEGRADE VALUES(2,'나무');
  INSERT INTO CAFEGRADE VALUES(3,'새싹');

  INSERT INTO CAFELIST VALUES(1,1,1,'테스트카페1',1,SYSDATE);
  INSERT INTO CAFELIST VALUES(2,2,2,'테스트카페2',2,SYSDATE);
  INSERT INTO CAFELIST VALUES(3,3,3,'테스트카페3',1,SYSDATE);
  
  INSERT INTO CAFEMEMGRADE VALUES(1,0,'관리자');
  INSERT INTO CAFEMEMGRADE VALUES(1,1,'회원');
  INSERT INTO CAFEMEMGRADE VALUES(1,2,'비회원');
  
  INSERT INTO CAFEMEMBER VALUES(1,1,'도도',0,10,SYSDATE);
  INSERT INTO CAFEMEMBER VALUES(2,1,'레레',1,11,SYSDATE);
  INSERT INTO CAFEMEMBER VALUES(3,1,'레레',1,12,SYSDATE);
  INSERT INTO CAFEMEMBER VALUES(4,1,'레레',1,15,SYSDATE);
  
  INSERT INTO CAFEBOARDCAT VALUES(1,1,'필독',1);
  INSERT INTO CAFEBOARDCAT VALUES(2,1,'ABOUT',3);
  INSERT INTO CAFEBOARDCAT VALUES(3,1,'DATA',4);
  INSERT INTO CAFEBOARDCAT VALUES(4,1,'서포트',2);
  
  INSERT INTO CAFEBOARD VALUES(1,0,1,'전체글보기',0,1);
  INSERT INTO CAFEBOARD VALUES(2,1,1,'공지',0,1);
  INSERT INTO CAFEBOARD VALUES(3,2,1,'가입인사',2,2);
  INSERT INTO CAFEBOARD VALUES(4,3,2,'작품',2,1);
  INSERT INTO CAFEBOARD VALUES(5,4,2,'작품후기',0,2);
  INSERT INTO CAFEBOARD VALUES(6,5,3,'사진',1,1);
  INSERT INTO CAFEBOARD VALUES(7,6,3,'영상',1,2);
  
  INSERT INTO POSTCAT VALUES(0,'공지');
  INSERT INTO POSTCAT VALUES(1,'일반');
  
  INSERT INTO POST VALUES(1,1,1,'공지사항1','공지사항 내용1',SYSDATE,1,1,10);
  INSERT INTO POST VALUES(2,2,2,'공지사항2','공지사항 내용2',SYSDATE,2,1,20);
  INSERT INTO POST VALUES(3,3,2,'공지사항3','공지사항 내용3',SYSDATE,1,1,30);
  INSERT INTO POST VALUES(4,4,1,'공지사항4','공지사항 내용4',SYSDATE,1,1,40);
  INSERT INTO POST VALUES(5,5,1,'일반글1','일반글 내용2',SYSDATE,1,0,50);
  INSERT INTO POST VALUES(6,6,2,'일반글2','일반글 내용3',SYSDATE,2,0,60);
  INSERT INTO POST VALUES(7,7,1,'일반글3','일반글 내용4',SYSDATE,1,0,10);
  
  INSERT INTO POSTCOMMENT VALUES(1,1,1,'이게 댓글이다 임마들아!!!',SYSDATE);
  INSERT INTO POSTCOMMENT VALUES(2,1,2,'이게 댓글이다 임마들아2!!!',SYSDATE);
  INSERT INTO POSTCOMMENT VALUES(3,1,1,'이게 댓글이다 임마들아3!!!',SYSDATE);
  
  commit;