drop table if exists member CASCADE; -- 07.05 테이블 생성쿼리
create table member
(
 id bigint generated by default as identity,
 name varchar(255),
 primary key (id)
);