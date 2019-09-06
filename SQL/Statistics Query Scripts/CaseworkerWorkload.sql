--CaseworkerWorkload.sql

-- Gets how many tickets are assigned to a caseworker currently.
SELECT COUNT(t.id) as numberOfTickets, u.name
FROM public.tickets AS t
         LEFT JOIN public.users AS u
                   ON u.id = t.caseworkerid
GROUP BY u.name
ORDER BY numberOfTickets;