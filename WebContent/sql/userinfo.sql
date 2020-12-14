--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
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
REM INSERTING into USERINFO
SET DEFINE OFF;
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
