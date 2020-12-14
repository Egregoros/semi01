--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
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
REM INSERTING into POST
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table POST
--------------------------------------------------------

  ALTER TABLE "POST" MODIFY ("PST_CAT_NUM" NOT NULL ENABLE)
  ALTER TABLE "POST" ADD CONSTRAINT "POST_PK" PRIMARY KEY ("POSTNUM") ENABLE
  ALTER TABLE "POST" MODIFY ("POSTNUM" NOT NULL ENABLE)
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
