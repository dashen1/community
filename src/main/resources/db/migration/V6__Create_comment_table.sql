create table comment
(
	id bigint auto_increment,
	parent_id bigint not null,
	type int,
	commentator int,
	gmt_create bigint,
	gmt_modified bigint,
	like_count bigint default 0,
		primary key (id)
);