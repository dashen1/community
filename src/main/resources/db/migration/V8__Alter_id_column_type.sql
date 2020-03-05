-- alter table QUESTION alter column ID bigint auto_increment;
-- alter table "USER" alter column ID bigint auto_increment;
alter table QUESTION modify ID bigint auto_increment NOT NULL;
alter table USER modify ID bigint auto_increment NOT NULL;
