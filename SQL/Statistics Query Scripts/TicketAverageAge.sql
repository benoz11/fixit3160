--TicketAverageAge

-- Shows the average age of tickets that are currently in progress. This gives management another POV to check if their team is falling behind the workload.
SELECT u.name, AVG(NOW() - tcl.dateinprogress) AS ticketAge
FROM ticketchangelog AS tcl
         INNER JOIN public.tickets AS t
                    ON t.id = tcl.ticketid
         LEFT JOIN public.users AS u
                   ON t.caseworkerid = u.id
WHERE t.state = 'In Progress'
GROUP BY u.name;
