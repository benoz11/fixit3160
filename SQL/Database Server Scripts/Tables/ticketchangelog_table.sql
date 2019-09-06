-- ticketchangelog_table.sql

-- Stores for each ticket the last date that the ticket experienced a state change for each respective state.
-- This table is used only for gathering data to be used for the statistics graphing feature of the application.
create table ticketchangelog
(
    id                integer generated always as identity
        constraint ticketchangelog_pkey
            primary key,
    ticketid          integer not null
        constraint "Ticket FK"
            references tickets
            on update cascade on delete cascade,
    dateopen          date,     -- When the ticket was first opened/submitted
    dateinprogress    date,     -- When the ticket was assigned last
    datecompleted     date,     -- When the ticket was last marked as competed
    dateresolved      date,     -- When the ticket was last marked as resolved
    dateclosed        date,     -- When the ticket was last closed
    dateknowledgebase date      -- When the ticket was added to the knowledge base
);

alter table ticketchangelog
    owner to qbjosxuxuthptx;
