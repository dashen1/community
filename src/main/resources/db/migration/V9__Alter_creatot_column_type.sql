-- alter table QUESTION alter column CREATOR bigint auto_increment;
-- alter table COMMENT alter column COMMENTATOR bigint auto_increment;
alter table QUESTION modify CREATOR bigint auto_increment NOT NULL;
alter table COMMENT modify COMMENTATOR bigint auto_increment NOT NULL;
