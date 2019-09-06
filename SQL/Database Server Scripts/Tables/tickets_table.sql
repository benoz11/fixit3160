-- tickets_table.sql

-- Stores submitted tickets, contained information, user IDs of people associated with it,
--      current ticket state (like Open, Close, etc.), and ticket priority.
create table tickets
(
    name           varchar(50)                                        not null,
    description    varchar(300)                                       not null,
    state          varchar(15)                                        not null,
    posterid       integer                                            not null
        constraint posterid
            references users,
    caseworkerid   integer
        constraint caseworkerid
            references users,
    id             integer generated always as identity
        constraint "Ticket_pkey"
            primary key,
    created        timestamp with time zone default CURRENT_TIMESTAMP not null,
    prioritypoints integer                                            not null,
    prioritylevel  varchar(10)                                        not null
);

comment on table tickets is 'Contains data relevant to an IT Support Ticket';

comment on constraint posterid on tickets is 'posterid refers to a user in the users table';

comment on constraint caseworkerid on tickets is 'caseworker id refers to a user in the users table';

alter table tickets
    owner to qbjosxuxuthptx;

create trigger update_ticketchangelog
    after insert or update
    on tickets
    for each row
execute procedure update_ticketchangelog();

create trigger "Create ticketchangelog row on insert"
    after insert
    on tickets
    for each row
execute procedure create_ticketchangelog_row();

comment on trigger "Create ticketchangelog row on insert" on tickets is 'Creates a row in the ticketchangelog table when there is a row created in the tickets table.';

