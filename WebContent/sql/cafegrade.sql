--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CAFEGRADE
--------------------------------------------------------

  CREATE TABLE "CAFEGRADE" 
   (	"GRADE_NUM" NUMBER, 
	"GRADE_NAME" VARCHAR2(20)
   )
REM INSERTING into CAFEGRADE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CAFEGRADE
--------------------------------------------------------

  ALTER TABLE "CAFEGRADE" ADD CONSTRAINT "CAFEGRADE_PK" PRIMARY KEY ("GRADE_NUM") ENABLE
  ALTER TABLE "CAFEGRADE" MODIFY ("GRADE_NUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEGRADE" MODIFY ("GRADE_NAME" NOT NULL ENABLE)
