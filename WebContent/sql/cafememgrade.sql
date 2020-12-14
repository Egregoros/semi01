--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CAFEMEMGRADE
--------------------------------------------------------

  CREATE TABLE "CAFEMEMGRADE" 
   (	"CAFENUM" NUMBER, 
	"CAFEMEM_GRADE" NUMBER, 
	"CAFEMEM_GRADE_NAME" VARCHAR2(20)
   )
REM INSERTING into CAFEMEMGRADE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CAFEMEMGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEMEMGRADE" ADD CONSTRAINT "CAFEMEMGRADE_PK" PRIMARY KEY ("CAFEMEM_GRADE") ENABLE
  ALTER TABLE "CAFEMEMGRADE" MODIFY ("CAFEMEM_GRADE" NOT NULL ENABLE)
  ALTER TABLE "CAFEMEMGRADE" MODIFY ("CAFENUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table CAFEMEMGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEMEMGRADE" ADD CONSTRAINT "CAFEMEMGRADE_FK1" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
