--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
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
REM INSERTING into CAFELIST
SET DEFINE OFF;
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
--  Ref Constraints for Table CAFELIST
--------------------------------------------------------

  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK1" FOREIGN KEY ("GRADE_NUM")
	  REFERENCES "CAFEGRADE" ("GRADE_NUM") ENABLE
  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK2" FOREIGN KEY ("CATNUM")
	  REFERENCES "CATTABLE" ("CATNUM_PK") ENABLE
  ALTER TABLE "CAFELIST" ADD CONSTRAINT "CAFELIST_FK3" FOREIGN KEY ("USERNUM")
	  REFERENCES "USERINFO" ("USERNUM") ENABLE
