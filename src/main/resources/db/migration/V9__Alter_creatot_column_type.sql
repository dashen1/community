-- alter table QUESTION alter column CREATOR bigint auto_increment;
-- alter table COMMENT alter column COMMENTATOR bigint auto_increment;
alter table QUESTION modified CREATOR bigint auto_increment;
alter table COMMENT modified COMMENTATOR bigint auto_increment;
