-- alter table QUESTION alter column CREATOR bigint;
-- alter table COMMENT alter column COMMENTATOR bigint;
alter table QUESTION modify CREATOR bigint NOT NULL;
alter table COMMENT modify COMMENTATOR bigint NOT NULL;
