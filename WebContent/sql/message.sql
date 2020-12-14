--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
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
REM INSERTING into MESSAGE
SET DEFINE OFF;
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
--  Ref Constraints for Table MESSAGE
--------------------------------------------------------

  ALTER TABLE "MESSAGE" ADD CONSTRAINT "MESSAGE_FK1" FOREIGN KEY ("REC_USER")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
