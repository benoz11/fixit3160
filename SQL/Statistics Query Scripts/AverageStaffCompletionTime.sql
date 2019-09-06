--Average Staff Completion Time.sql

(
    -- Gets on average how long each caseworker takes to resolve a ticket that is assigned to them
    SELECT u.name AS caseworkerName, (tcl.dateresolved - tcl.dateinprogress) AS averageCompletionTime
    FROM public.ticketchangelog AS tcl
             INNER JOIN public.tickets AS t
                        ON tcl.ticketid = t.id
             LEFT JOIN public.users AS u
                       ON t.caseworkerid = u.id
    WHERE tcl.dateresolved IS NOT NULL
      AND tcl.dateinprogress IS NOT NULL
      AND t.caseworkerid IS NOT NULL
)
UNION
(
    -- As well as the average completion time of all caseworkers
    SELECT 'All Caseworkers' AS caseworkerName, AVG(dateresolved - dateinprogress)
    FROM public.ticketchangelog
    WHERE dateresolved IS NOT NULL
      AND dateinprogress IS NOT NULL
)