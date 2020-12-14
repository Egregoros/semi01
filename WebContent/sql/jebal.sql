--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CAFEBOARDCAT
--------------------------------------------------------

  CREATE TABLE "CAFEBOARDCAT" 
   (	"BOARDCATNUM" NUMBER, 
	"CAFENUM_FK" NUMBER, 
	"CATNAME" VARCHAR2(30), 
	"CATORDER" NUMBER
   )
--------------------------------------------------------
--  DDL for Table CAFEBOARD
--------------------------------------------------------

  CREATE TABLE "CAFEBOARD" 
   (	"BOARDNUM" NUMBER, 
	"CAFENUM" NUMBER, 
	"BOARD_CAT_NUM" NUMBER, 
	"BOARD_NAME" VARCHAR2(30), 
	"ORDERNUM" NUMBER
   )
--------------------------------------------------------
--  DDL for Table CAFEGRADE
--------------------------------------------------------

  CREATE TABLE "CAFEGRADE" 
   (	"GRADE_NUM" NUMBER, 
	"GRADE_NAME" VARCHAR2(20)
   )
--------------------------------------------------------
--  DDL for Table CAFELIST
--------------------------------------------------------

  CREATE TABLE "CAFELIST" 
   (	"CAFENUM" NUMBER, 
	"GRADE_NUM" NUMBER, 
	"CATNUM" NUMBER, 
	"CAFENAME" VARCHAR2(20), 
	"USERNUM" NUMBER
   )
--------------------------------------------------------
--  DDL for Table CAFEMEMBER
--------------------------------------------------------

  CREATE TABLE "CAFEMEMBER" 
   (	"USERNUM" NUMBER, 
	"CAFENUM" NUMBER, 
	"CAFEMEMNICK" VARCHAR2(20), 
	"CAFEMEMGRADE" NUMBER
   )
--------------------------------------------------------
--  DDL for Table CAFEMEMGRADE
--------------------------------------------------------

  CREATE TABLE "CAFEMEMGRADE" 
   (	"CAFENUM" NUMBER, 
	"CAFEMEM_GRADE" NUMBER, 
	"CAFEMEM_GRADE_NAME" VARCHAR2(20)
   )
--------------------------------------------------------
--  DDL for Table CATTABLE
--------------------------------------------------------

  CREATE TABLE "CATTABLE" 
   (	"CATNUM_PK" NUMBER, 
	"CATNAME" VARCHAR2(20)
   )
--------------------------------------------------------
--  DDL for Table FILETABLE
--------------------------------------------------------

  CREATE TABLE "FILETABLE" 
   (	"FINENUM" NUMBER, 
	"POSTNUM" NUMBER, 
	"SAVEFILENAME" VARCHAR2(40), 
	"ORGFILENAME" VARCHAR2(30)
   )
--------------------------------------------------------
--  DDL for Table IMGTABLE
--------------------------------------------------------

  CREATE TABLE "IMGTABLE" 
   (	"IMGNUM" NUMBER, 
	"POSTNUM" NUMBER, 
	"SAVEIMGNAME" VARCHAR2(30), 
	"ORGIMGNAME" VARCHAR2(30)
   )
--------------------------------------------------------
--  DDL for Table MESSAGE
--------------------------------------------------------

  CREATE TABLE "MESSAGE" 
   (	"MESS_NUM" NUMBER, 
	"REC_USER" NUMBER, 
	"SEND_USERNUM" NUMBER, 
	"MESS_TITLE" VARCHAR2(30), 
	"MESS_CONTENT" VARCHAR2(100), 
	"SEND_TIME" DATE, 
	"CHECK_READ" VARCHAR2(20)
   )
--------------------------------------------------------
--  DDL for Table POST
--------------------------------------------------------

  CREATE TABLE "POST" 
   (	"POSTNUM" NUMBER, 
	"BOARDNUM" NUMBER, 
	"CAFENUM" NUMBER, 
	"POST_TITLE" VARCHAR2(30), 
	"POST_CONTENT" VARCHAR2(100), 
	"POST_DATE" DATE, 
	"USER_NUM" VARCHAR2(20), 
	"PST_CAT_NUM" NUMBER
   )
--------------------------------------------------------
--  DDL for Table POSTCATTABLE
--------------------------------------------------------

  CREATE TABLE "POSTCATTABLE" 
   (	"POST_CAT_NUM" NUMBER, 
	"CAFE_NUM" NUMBER, 
	"POST_CAT_NAME" VARCHAR2(15)
   )
--------------------------------------------------------
--  DDL for Table USERINFO
--------------------------------------------------------

  CREATE TABLE "USERINFO" 
   (	"USERNUM" NUMBER(3,0), 
	"ID" VARCHAR2(20), 
	"PWD" VARCHAR2(20), 
	"NAME" VARCHAR2(10), 
	"NICKNAME" VARCHAR2(10), 
	"ADDR" VARCHAR2(50), 
	"EMAIL" VARCHAR2(20), 
	"BIRTH" VARCHAR2(10), 
	"PHONE" VARCHAR2(20), 
	"ISLIVE" NUMBER
   )
REM INSERTING into CAFEBOARDCAT
SET DEFINE OFF;
REM INSERTING into CAFEBOARD
SET DEFINE OFF;
REM INSERTING into CAFEGRADE
SET DEFINE OFF;
REM INSERTING into CAFELIST
SET DEFINE OFF;
REM INSERTING into CAFEMEMBER
SET DEFINE OFF;
REM INSERTING into CAFEMEMGRADE
SET DEFINE OFF;
REM INSERTING into CATTABLE
SET DEFINE OFF;
REM INSERTING into FILETABLE
SET DEFINE OFF;
REM INSERTING into IMGTABLE
SET DEFINE OFF;
REM INSERTING into MESSAGE
SET DEFINE OFF;
REM INSERTING into POST
SET DEFINE OFF;
REM INSERTING into POSTCATTABLE
SET DEFINE OFF;
REM INSERTING into USERINFO
SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Index CAFEBOARDCAT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAFEBOARDCAT_PK" ON "CAFEBOARDCAT" ("BOARDCATNUM")
--------------------------------------------------------
--  DDL for Index BOARDNUM_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "BOARDNUM_PK" ON "CAFEBOARD" ("BOARDNUM")
--------------------------------------------------------
--  DDL for Index CAFEGRADE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAFEGRADE_PK" ON "CAFEGRADE" ("GRADE_NUM")
--------------------------------------------------------
--  DDL for Index CAFELIST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAFELIST_PK" ON "CAFELIST" ("CAFENUM")
--------------------------------------------------------
--  DDL for Index CAFEMEMBER_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAFEMEMBER_PK1" ON "CAFEMEMBER" ("USERNUM")
--------------------------------------------------------
--  DDL for Index CAFEMEMGRADE_PK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "CAFEMEMGRADE_PK1" ON "CAFEMEMGRADE" ("CAFEMEM_GRADE")
--------------------------------------------------------
--  DDL for Index CATTABLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CATTABLE_PK" ON "CATTABLE" ("CATNUM_PK")
--------------------------------------------------------
--  DDL for Index FILETABLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FILETABLE_PK" ON "FILETABLE" ("FINENUM")
--------------------------------------------------------
--  DDL for Index IMGNUM
--------------------------------------------------------

  CREATE UNIQUE INDEX "IMGNUM" ON "IMGTABLE" ("IMGNUM")
--------------------------------------------------------
--  DDL for Index MESSAGE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MESSAGE_PK" ON "MESSAGE" ("MESS_NUM")
--------------------------------------------------------
--  DDL for Index POST_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POST_PK" ON "POST" ("POSTNUM")
--------------------------------------------------------
--  DDL for Index POSTCATTABLE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "POSTCATTABLE_PK" ON "POSTCATTABLE" ("POST_CAT_NUM")
--------------------------------------------------------
--  DDL for Index SYS_C007014
--------------------------------------------------------

  CREATE UNIQUE INDEX "SYS_C007014" ON "USERINFO" ("USERNUM")
--------------------------------------------------------
--  Constraints for Table CAFEBOARDCAT
--------------------------------------------------------

  ALTER TABLE "CAFEBOARDCAT" ADD CONSTRAINT "CAFEBOARDCAT_PK" PRIMARY KEY ("BOARDCATNUM") ENABLE
  ALTER TABLE "CAFEBOARDCAT" MODIFY ("BOARDCATNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table CAFEBOARD
--------------------------------------------------------

  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "BOARDNUM_PK" PRIMARY KEY ("BOARDNUM") ENABLE
  ALTER TABLE "CAFEBOARD" MODIFY ("ORDERNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARD_NAME" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARD_CAT_NUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("CAFENUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARDNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table CAFEGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEGRADE" ADD CONSTRAINT "CAFEGRADE_PK" PRIMARY KEY ("GRADE_NUM") ENABLE
  ALTER TABLE "CAFEGRADE" MODIFY ("GRADE_NUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEGRADE" MODIFY ("GRADE_NAME" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table CAFELIST
--------------------------------------------------------

  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_PK" PRIMARY KEY ("CAFENUM") ENABLE
  ALTER TABLE "CAFELIST" MODIFY ("USERNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFELIST" MODIFY ("CAFENAME" NOT NULL ENABLE)
  ALTER TABLE "CAFELIST" MODIFY ("CATNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFELIST" MODIFY ("GRADE_NUM" NOT NULL ENABLE)
  ALTER TABLE "CAFELIST" MODIFY ("CAFENUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table CAFEMEMBER
--------------------------------------------------------

  ALTER TABLE "CAFEMEMBER" MODIFY ("CAFENUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMBER" MODIFY ("USERNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_PK1" PRIMARY KEY ("USERNUM") ENABLE
--------------------------------------------------------
--  Constraints for Table CAFEMEMGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEMEMGRADE" ADD CONSTRAINT "CAFEMEMGRADE_PK" PRIMARY KEY ("CAFEMEM_GRADE") ENABLE
  ALTER TABLE "CAFEMEMGRADE" MODIFY ("CAFEMEM_GRADE" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMGRADE" MODIFY ("CAFENUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table CATTABLE
--------------------------------------------------------

  ALTER TABLE "CATTABLE" MODIFY ("CATNAME" NOT NULL ENABLE)
  ALTER TABLE "CATTABLE" ADD CONSTRAINT "CATTABLE_PK" PRIMARY KEY ("CATNUM_PK") ENABLE
  ALTER TABLE "CATTABLE" MODIFY ("CATNUM_PK" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table FILETABLE
--------------------------------------------------------

  ALTER TABLE "FILETABLE" ADD CONSTRAINT "FILENUM_PK" PRIMARY KEY ("FINENUM") ENABLE
  ALTER TABLE "FILETABLE" MODIFY ("ORGFILENAME" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("SAVEFILENAME" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("POSTNUM" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("FINENUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table IMGTABLE
--------------------------------------------------------

  ALTER TABLE "IMGTABLE" ADD CONSTRAINT "IMGNUM_PK" PRIMARY KEY ("IMGNUM") ENABLE
  ALTER TABLE "IMGTABLE" MODIFY ("ORGIMGNAME" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("SAVEIMGNAME" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("POSTNUM" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("IMGNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "MESSAGE" ADD CONSTRAINT "MESSAGE_PK" PRIMARY KEY ("MESS_NUM") ENABLE
  ALTER TABLE "MESSAGE" MODIFY ("SEND_TIME" NOT NULL ENABLE)
  ALTER TABLE "MESSAGE" MODIFY ("MESS_CONTENT" NOT NULL ENABLE)
  ALTER TABLE "MESSAGE" MODIFY ("MESS_TITLE" NOT NULL ENABLE)
  ALTER TABLE "MESSAGE" MODIFY ("SEND_USERNUM" NOT NULL ENABLE)
  ALTER TABLE "MESSAGE" MODIFY ("REC_USER" NOT NULL ENABLE)
  ALTER TABLE "MESSAGE" MODIFY ("MESS_NUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table POST
--------------------------------------------------------

  ALTER TABLE "POST" MODIFY ("PST_CAT_NUM" NOT NULL ENABLE)
  ALTER TABLE "POST" ADD CONSTRAINT "POST_PK" PRIMARY KEY ("POSTNUM") ENABLE
  ALTER TABLE "POST" MODIFY ("POSTNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table POSTCATTABLE
--------------------------------------------------------

  ALTER TABLE "POSTCATTABLE" ADD CONSTRAINT "POSTCATTABLE_PK" PRIMARY KEY ("POST_CAT_NUM") ENABLE
  ALTER TABLE "POSTCATTABLE" MODIFY ("POST_CAT_NAME" NOT NULL ENABLE)
  ALTER TABLE "POSTCATTABLE" MODIFY ("CAFE_NUM" NOT NULL ENABLE)
  ALTER TABLE "POSTCATTABLE" MODIFY ("POST_CAT_NUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Constraints for Table USERINFO
--------------------------------------------------------

  ALTER TABLE "USERINFO" ADD CONSTRAINT "SYS_C007014" PRIMARY KEY ("USERNUM") ENABLE
  ALTER TABLE "USERINFO" MODIFY ("USERNUM" NOT NULL ENABLE)
  ALTER TABLE "USERINFO" MODIFY ("NICKNAME" NOT NULL ENABLE)
  ALTER TABLE "USERINFO" MODIFY ("NAME" NOT NULL ENABLE)
  ALTER TABLE "USERINFO" MODIFY ("PWD" NOT NULL ENABLE)
  ALTER TABLE "USERINFO" MODIFY ("ID" NOT NULL ENABLE)
  ALTER TABLE "USERINFO" MODIFY ("ISLIVE" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table CAFEBOARDCAT
--------------------------------------------------------

  ALTER TABLE "CAFEBOARDCAT" ADD CONSTRAINT "CAFEBOARDCAT_FK1" FOREIGN KEY ("CAFENUM_FK")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CAFEBOARD
--------------------------------------------------------

  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "CAFEBOARD_FK1" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "CAFEBOARD_FK2" FOREIGN KEY ("BOARD_CAT_NUM")
	  REFERENCES "CAFEBOARDCAT" ("BOARDCATNUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CAFELIST
--------------------------------------------------------

  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK1" FOREIGN KEY ("GRADE_NUM")
	  REFERENCES "CAFEGRADE" ("GRADE_NUM") ENABLE
  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK2" FOREIGN KEY ("CATNUM")
	  REFERENCES "CATTABLE" ("CATNUM_PK") ENABLE
  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK3" FOREIGN KEY ("USERNUM")
	  REFERENCES "USERINFO" ("USERNUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CAFEMEMBER
--------------------------------------------------------

  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK1" FOREIGN KEY ("USERNUM")
	  REFERENCES "USERINFO" ("USERNUM") ENABLE
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK2" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK3" FOREIGN KEY ("CAFEMEMGRADE")
	  REFERENCES "CAFEMEMGRADE" ("CAFEMEM_GRADE") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CAFEMEMGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEMEMGRADE" ADD CONSTRAINT "CAFEMEMGRADE_FK1" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table FILETABLE
--------------------------------------------------------

  ALTER TABLE "FILETABLE" ADD CONSTRAINT "POSTNUM_FK" FOREIGN KEY ("POSTNUM")
	  REFERENCES "POST" ("POSTNUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table IMGTABLE
--------------------------------------------------------

  ALTER TABLE "IMGTABLE" ADD CONSTRAINT "IMGTABLE_FK" FOREIGN KEY ("POSTNUM")
	  REFERENCES "POST" ("POSTNUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "MESSAGE" ADD CONSTRAINT "MESSAGE_FK1" FOREIGN KEY ("REC_USER")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table POST
--------------------------------------------------------

  ALTER TABLE "POST" ADD CONSTRAINT "POST_FK1" FOREIGN KEY ("BOARDNUM")
	  REFERENCES "CAFEBOARD" ("BOARDNUM") ENABLE
  ALTER TABLE "POST" ADD CONSTRAINT "POST_FK2" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
  ALTER TABLE "POST" ADD CONSTRAINT "POST_FK3" FOREIGN KEY ("POSTNUM")
	  REFERENCES "USERINFO" ("USERNUM") ENABLE
  ALTER TABLE "POST" ADD CONSTRAINT "POST_FK4" FOREIGN KEY ("PST_CAT_NUM")
	  REFERENCES "POSTCATTABLE" ("POST_CAT_NUM") ENABLE
