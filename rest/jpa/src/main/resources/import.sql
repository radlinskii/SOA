drop table if exists student cascade;
drop table if exists course cascade;
drop table if exists schedule cascade;
drop table if exists faculty cascade;

create table course
(
    name varchar(255) not null
        constraint courses_pk
            primary key

);

create table faculty
(
    name varchar(255) not null
        constraint faculty_pk
            primary key

);


create table student
(
    studentcardid integer not null
        constraint student_pkey
            primary key,
    name          varchar(255),
    faculty       varchar(255) references faculty (name),
    semester      integer,
    avatar        text
);

create table schedule
(
    studentcardid int references student (studentcardid),
    course_name   varchar(255) references course (name)
);

insert into faculty(name)
values ('EAIIB');
insert into faculty(name)
values ('WIET');

insert into course(name)
values ('SOA');
insert into course(name)
values ('JIMP');
insert into course(name)
values ('HD');
insert into course(name)
values ('JIMP2');
insert into course(name)
values ('PD');