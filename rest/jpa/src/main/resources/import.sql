drop table if exists student cascade;
drop table if exists course cascade;
drop table if exists schedule cascade;
drop table if exists faculty cascade;
drop table if exists lecturer cascade;

create table lecturer
(
    id    integer not null
        constraint lecturer_pkey
            primary key,
    name  varchar(255),
    title varchar(255)
);

create table course
(
    name varchar(255) not null
        constraint courses_pk
            primary key,
    lecturer_id integer references lecturer (id)

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

insert into lecturer(id, name, title)
values (1, 'Ignacy Radliński', 'phd');
insert into lecturer(id, name, title)
VALUES (2, 'Agnieszka Miszkurka', 'prof');

insert into faculty(name)
values ('EAIIB');
insert into faculty(name)
values ('WIET');

insert into course(name, lecturer_id)
values ('SOA', 1);
insert into course(name, lecturer_id)
values ('JIMP', 2);
insert into course(name, lecturer_id)
values ('HD', 1);
insert into course(name, lecturer_id)
values ('JIMP2', 2);
insert into course(name, lecturer_id)
values ('PD', 1);