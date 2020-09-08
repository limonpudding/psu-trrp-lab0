drop table if exists LESSON;
drop table if exists COURSE;
drop table if exists TEACHER;
drop table if exists CLASSROOM;
drop table if exists BUILDING;

create table if not exists BUILDING
(
    building         varchar primary key,
    building_address varchar(128)
);

create table if not exists CLASSROOM
(
    CLASSROOM_ID     serial primary key,
    CLASSROOM_NUMBER varchar(8),
    BUILDING         varchar(128),
    foreign key (BUILDING) references BUILDING (building)
);

create table if not exists TEACHER
(
    TEACHER_ID         serial primary key,
    TEACHER_LAST_NAME  varchar(32),
    TEACHER_FIRST_NAME varchar(32),
    TEACHER_PATRONYMIC varchar(32),
    TEACHER_MAIL       varchar(128),
    TEACHER_PHONE      bigint
);

create table if not exists COURSE
(
    COURSE_ID          serial primary key,
    COURSE_NAME        varchar(256),
    COURSE_TOTAL_HOURS int,
    TEACHER_ID         int,
    foreign key (TEACHER_ID) references TEACHER (TEACHER_ID)
);

create table if not exists LESSON
(
    LESSON_ID       serial primary key,
    LESSON_DATETIME timestamp,
    LESSON_HOURS    int,
    COURSE_ID       int,
    CLASSROOM_ID    int,
    foreign key (COURSE_ID) references COURSE (COURSE_ID),
    foreign key (CLASSROOM_ID) references CLASSROOM (CLASSROOM_ID)
);