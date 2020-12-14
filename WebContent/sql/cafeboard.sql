--------------------------------------------------------
--  파일이 생성됨 - 월요일-12월-14-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table CAFEBOARD
--------------------------------------------------------

  CREATE TABLE "CAFEBOARD" 
   (	"BOARDNUM" NUMBER, 
	"CAFENUM" NUMBER, 
	"BOARD_CAT_NUM" NUMBER, 
	"BOARD_NAME" VARCHAR2(30), 
	"ORDERNUM" NUMBER
   )
REM INSERTING into CAFEBOARD
SET DEFINE OFF;
--------------------------------------------------------
--  Constraints for Table CAFEBOARD
--------------------------------------------------------

  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "BOARDNUM_PK" PRIMARY KEY ("BOARDNUM") ENABLE
  ALTER TABLE "CAFEBOARD" MODIFY ("ORDERNUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARD_NAME" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARD_CAT_NUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("CAFENUM" NOT NULL ENABLE)
  ALTER TABLE "CAFEBOARD" MODIFY ("BOARDNUM" NOT NULL ENABLE)
--------------------------------------------------------
--  Ref Constraints for Table CAFEBOARD
--------------------------------------------------------

  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "CAFEBOARD_FK1" FOREIGN KEY ("CAFENUM")
	  REFERENCES "CAFELIST" ("CAFENUM") ENABLE
  ALTER TABLE "CAFEBOARD" ADD CONSTRAINT "CAFEBOARD_FK2" FOREIGN KEY ("BOARD_CAT_NUM")
	  REFERENCES "CAFEBOARDCAT" ("BOARDCATNUM") ENABLE
