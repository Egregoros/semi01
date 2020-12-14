--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table FILETABLE
--------------------------------------------------------

  CREATE TABLE "FILETABLE" 
   (	"FINENUM" NUMBER, 
	"POSTNUM" NUMBER, 
	"SAVEFILENAME" VARCHAR2(40), 
	"ORGFILENAME" VARCHAR2(30)
   )
REM INSERTING into FILETABLE
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table FILETABLE
--------------------------------------------------------

  ALTER TABLE "FILETABLE" ADD CONSTRAINT "FILENUM_PK" PRIMARY KEY ("FINENUM") ENABLE
  ALTER TABLE "FILETABLE" MODIFY ("ORGFILENAME" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("SAVEFILENAME" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("POSTNUM" NOT NULL ENABLE)
  ALTER TABLE "FILETABLE" MODIFY ("FINENUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table FILETABLE
--------------------------------------------------------

  ALTER TABLE "FILETABLE" ADD CONSTRAINT "POSTNUM_FK" FOREIGN KEY ("POSTNUM")
	  REFERENCES "POST" ("POSTNUM") ENABLE
