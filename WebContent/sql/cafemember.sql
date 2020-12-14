--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CAFEMEMBER
--------------------------------------------------------

  CREATE TABLE "CAFEMEMBER" 
   (	"USERNUM" NUMBER, 
	"CAFENUM" NUMBER, 
	"CAFEMEMNICK" VARCHAR2(20), 
	"CAFEMEMGRADE" NUMBER
   )
REM INSERTING into CAFEMEMBER
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CAFEMEMBER
--------------------------------------------------------

  ALTER TABLE "CAFEMEMBER" MODIFY ("CAFENUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMBER" MODIFY ("USERNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_PK1" PRIMARY KEY ("USERNUM") ENABLE
--------------------------------------------------------
--  Ref Constraints for Table CAFEMEMBER
--------------------------------------------------------

  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK1" FOREIGN KEY ("USERNUM")
	  REFERENCES "USERINFO" ("USERNUM") ENABLE
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK2" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
  ALTER TABLE "CAFEMEMBER" ADD CONSTRAINT "CAFEMEMBER_FK3" FOREIGN KEY ("CAFEMEMGRADE")
	  REFERENCES "CAFEMEMGRADE" ("CAFEMEM_GRADE") ENABLE
