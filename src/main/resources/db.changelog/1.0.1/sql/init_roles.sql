create sequence if not exists role_id_seq start 1;

create table roles (
                       role_id INTEGER default nextval('role_id_seq') not null primary key,
                       role_name VARCHAR(50) NOT NULL UNIQUE
);

create sequence if not exists user_id_seq start 1;

create table users (
                       user_id INTEGER default nextval('user_id_seq') NOT NULL primary key,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(150) NOT NULL
);

create table users_roles (
                             user_id int not null references users(user_id),
                             role_id int not null references roles(role_id)
);

insert into roles VALUES ( nextval('role_id_seq'), 'ROLE_USER'),
                         ( nextval('role_id_seq'), 'ROLE_ADMIN');

