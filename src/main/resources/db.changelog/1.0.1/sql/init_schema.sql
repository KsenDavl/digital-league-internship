create sequence if not exists students_id_seq start 1;

create table students (
                          student_id INTEGER not null default nextval('students_id_seq') primary key,
                          name VARCHAR(100),
                          specialty VARCHAR(50),
                          year int check (year > 0 AND year < 6)
);

create sequence if not exists teachers_id_seq start 1;

create table teachers (
                          teacher_id INTEGER not null default nextval('teachers_id_seq') primary key,
                          name VARCHAR(100),
                          department VARCHAR(50)
);

CREATE TABLE students_teachers (
                                   student_id INTEGER references students(student_id),
                                   teacher_id INTEGER references teachers(teacher_id)
);


