create table users
(
    id    int auto_increment primary key ,
    email varchar(255)
);

create table courses
(
    id          int auto_increment primary key ,
    title       varchar,
    description varchar,
    price       smallint
);

create table purchases
(
    user_id   int,
    course_id int,
    purchased_at timestamp,
    foreign key (user_id) references users(id),
    foreign key (course_id) references courses(id)
);


insert into users (email) values ( 'marco@marcobehler.com' );
insert into courses (title, description, price) values ( 'Learning Jooq', 'What a great description', 5000);
insert into purchases values ( 1, 1, now());
commit;

select * from courses;
select * from users;
select * from purchases;