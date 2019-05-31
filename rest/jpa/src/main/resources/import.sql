drop table if exists student cascade;

drop table if exists courses cascade;

drop table if exists schedule cascade;

create table courses
(
    name varchar(255) not null
        constraint courses_pk
            primary key

);

create table student
(
    studentcardid integer not null
        constraint student_pkey
            primary key,
    name          varchar(255),
    faculty       varchar(255),
    semester      integer,
    avatar        text
);

create table schedule
(
    studentcardid int references student (studentcardid),
    course_name   varchar(255) references courses (name)
);

insert into courses(name)
values ('SOA');
insert into courses(name)
values ('JIMP');
insert into courses(name)
values ('HD');
insert into courses(name)
values ('JIMP2');
insert into courses(name)
values ('PD');