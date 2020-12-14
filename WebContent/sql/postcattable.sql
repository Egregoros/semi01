--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table POSTCATTABLE
--------------------------------------------------------

  CREATE TABLE "POSTCATTABLE" 
   (	"POST_CAT_NUM" NUMBER, 
	"CAFE_NUM" NUMBER, 
	"POST_CAT_NAME" VARCHAR2(15)
   )
REM INSERTING into POSTCATTABLE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table POSTCATTABLE
--------------------------------------------------------

  ALTER TABLE "POSTCATTABLE" ADD CONSTRAINT "POSTCATTABLE_PK" PRIMARY KEY ("POST_CAT_NUM") ENABLE
  ALTER TABLE "POSTCATTABLE" MODIFY ("POST_CAT_NAME" NOT NULL ENABLE)
  ALTER TABLE "POSTCATTABLE" MODIFY ("CAFE_NUM" NOT NULL ENABLE)
  ALTER TABLE "POSTCATTABLE" MODIFY ("POST_CAT_NUM" NOT NULL ENABLE)
