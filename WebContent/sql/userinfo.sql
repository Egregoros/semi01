create table userinfo
(
	id varchar2(20) primary key,
	pwd varchar2(20) not null,
	name varchar2(10) not null,
	nickname varchar2(10) not null,
	addr varchar2(50),
	email varchar2(20),
	birth varchar2(10),
	phone varchar2(20) not null
);