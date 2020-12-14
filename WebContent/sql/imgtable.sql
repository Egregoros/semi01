--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table IMGTABLE
--------------------------------------------------------

  CREATE TABLE "IMGTABLE" 
   (	"IMGNUM" NUMBER, 
	"POSTNUM" NUMBER, 
	"SAVEIMGNAME" VARCHAR2(30), 
	"ORGIMGNAME" VARCHAR2(30)
   )
REM INSERTING into IMGTABLE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table IMGTABLE
--------------------------------------------------------

  ALTER TABLE "IMGTABLE" ADD CONSTRAINT "IMGNUM_PK" PRIMARY KEY ("IMGNUM") ENABLE
  ALTER TABLE "IMGTABLE" MODIFY ("ORGIMGNAME" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("SAVEIMGNAME" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("POSTNUM" NOT NULL ENABLE)
  ALTER TABLE "IMGTABLE" MODIFY ("IMGNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table IMGTABLE
--------------------------------------------------------

  ALTER TABLE "IMGTABLE" ADD CONSTRAINT "IMGTABLE_FK" FOREIGN KEY ("POSTNUM")
	  REFERENCES "POST" ("POSTNUM") ENABLE
