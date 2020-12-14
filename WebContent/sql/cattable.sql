--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CATTABLE
--------------------------------------------------------

  CREATE TABLE "CATTABLE" 
   (	"CATNUM_PK" NUMBER, 
	"CATNAME" VARCHAR2(20)
   )
REM INSERTING into CATTABLE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CATTABLE
--------------------------------------------------------

  ALTER TABLE "CATTABLE" MODIFY ("CATNAME" NOT NULL ENABLE)
  ALTER TABLE "CATTABLE" ADD CONSTRAINT "CATTABLE_PK" PRIMARY KEY ("CATNUM_PK") ENABLE
  ALTER TABLE "CATTABLE" MODIFY ("CATNUM_PK" NOT NULL ENABLE)
