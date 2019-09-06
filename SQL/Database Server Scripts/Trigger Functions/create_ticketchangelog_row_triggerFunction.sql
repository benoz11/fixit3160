-- create_ticketchangelog_row_triggerFunction.sql

-- This function created a row in the ticketchangelog table when a new ticket is inserted into the tickets table.
create function create_ticketchangelog_row() returns trigger
    language plpgsql
as $$
BEGIN
    INSERT INTO public.ticketchangelog (ticketid)
    VALUES (NEW.id);    -- Ticketchangelog foreign key

    RETURN NEW;
END;
$$;

alter function create_ticketchangelog_row() owner to qbjosxuxuthptx;
