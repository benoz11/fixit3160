--HeatMapAgeForPerformance.sql

-- Shows how long tickets of different priorities take to live out their full lifecycle, from initial opening to completion (not just resolution).
SELECT t.prioritylevel AS priority, (tcl.datecompleted - tcl.dateopen) as birthToDeath
FROM public.ticketchangelog as tcl
         INNER JOIN public.tickets AS t
                    ON tcl.ticketid = t.id
WHERE dateopen IS NOT NULL
  AND datecompleted IS NOT NULL
GROUP BY priority, birthToDeath
ORDER BY priority, birthToDeath;

