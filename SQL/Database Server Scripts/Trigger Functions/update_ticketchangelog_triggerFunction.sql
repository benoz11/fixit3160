-- update_ticketchangelog_triggerFunction.sql

-- When the state of a ticket changes, the corresponding record is updated in ticketchangelog.
create function update_ticketchangelog() returns trigger
    language plpgsql
as
$$
BEGIN
    --Proceed only if there is a state change
    IF (TG_OP = 'INSERT' OR NEW.state <> OLD.state) THEN

        BEGIN

            -- Ticket state it now Open
            IF (NEW.state = 'Open') THEN

                UPDATE public.ticketchangelog
                    SET dateopen = NOW()
                    WHERE ticketid = NEW.id;

            -- Ticket state it now In Progress (has been assigned to caseworker)
            ELSIF (NEW.state = 'In Progress') THEN

                UPDATE public.ticketchangelog
                    SET dateinprogress = NOW()
                    WHERE ticketid = NEW.id;

            -- Ticket state it now Resolved
            ELSIF (NEW.state = 'Resolved') THEN

                UPDATE public.ticketchangelog
                    SET dateresolved = NOW()
                    WHERE ticketid = NEW.id;

                -- Ticket state it now Completed
            ELSIF (NEW.state = 'Completed') THEN

                UPDATE public.ticketchangelog
                    SET datecompleted = NOW()
                    WHERE ticketid = NEW.id;

            -- Ticket state it now Closed
            ELSIF (NEW.state = 'Closed') THEN

                UPDATE public.ticketchangelog
                    SET dateclosed = NOW()
                    WHERE ticketid = NEW.id;

                -- Ticket state it now Knowledge Base
            ELSIF (NEW.state = 'Knowledge Base') THEN

                UPDATE public.ticketchangelog
                    SET dateknowledgebase = NOW()
                    WHERE ticketid = NEW.id;

            END IF;

        END;

    END IF;

    RETURN NEW;

END;

$$;

alter function update_ticketchangelog() owner to qbjosxuxuthptx;