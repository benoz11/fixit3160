-- users_table.sql

-- Table storing user information, roles and logon credentials.
create table users
(
    username varchar(20)                                        not null
        constraint "Unique usernames"
            unique,
    role     varchar(10)                                        not null,
    password varchar(30)                                        not null,
    name     varchar(50)                                        not null,
    id       integer generated always as identity
        constraint "Users_pkey"
            primary key,
    created  timestamp with time zone default CURRENT_TIMESTAMP not null
);

comment on table users is 'Authenticated users list';

comment on constraint "Unique usernames" on users is 'Users cannot have the same username';

alter table users
    owner to qbjosxuxuthptx;
