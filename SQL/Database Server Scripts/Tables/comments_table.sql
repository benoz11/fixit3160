-- comments_table.sql

-- Stores the comments that users can make on tickets.
create table comments
(
    posterid integer                                            not null
        constraint posterid
            references users,
    ticketid integer                                            not null
        constraint ticketid
            references tickets,
    contents varchar(300)                                       not null,
    id       integer generated always as identity
        constraint "Comment_pkey"
            primary key,
    created  timestamp with time zone default CURRENT_TIMESTAMP not null
);

comment on table comments is 'Comments on a Ticket (Thread)';

alter table comments
    owner to qbjosxuxuthptx;
