-- alter table USER alter column AVATAR rename to AVATAR_URL;
alter table USER change column  AVATAR AVATAR_URL;
