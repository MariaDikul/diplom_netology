create table data
(
    filename      varchar(255) not null,
    date          timestamp(8) not null,
    file_content  bigint       not null,
    size          bigint       not null,
    user_username varchar(255),
    primary key (filename)
);