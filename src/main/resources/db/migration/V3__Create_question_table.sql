create table PUBLIC.question
(
    id int auto_increment primary key ,
    title varchar(50),
    description text,
    create_time timestamp,
    update_time timestamp,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    tag varchar(256)
);

