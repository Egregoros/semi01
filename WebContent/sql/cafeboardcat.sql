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
REM INSERTING into CAFEBOARDCAT
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CAFEBOARDCAT
--------------------------------------------------------

  ALTER TABLE "CAFEBOARDCAT" ADD CONSTRAINT "CAFEBOARDCAT_PK" PRIMARY KEY ("BOARDCATNUM") ENABLE
  ALTER TABLE "CAFEBOARDCAT" MODIFY ("BOARDCATNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table CAFEBOARDCAT
--------------------------------------------------------

  ALTER TABLE "CAFEBOARDCAT" ADD CONSTRAINT "CAFEBOARDCAT_FK1" FOREIGN KEY ("CAFENUM_FK")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
