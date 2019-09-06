--OpenVsClosed.sql

(
    -- Gets the dates and count for tickets being resolved on respective dates
    SELECT dateresolved AS date, count(*) AS count, 'Resolved' AS key
    FROM ticketchangelog
    WHERE dateresolved IS NOT NULL
    GROUP BY dateresolved
)
UNION ALL
(
    -- Gets the dates and count for tickets being opened/lodged on respective dates
    SELECT dateopen AS date, count(*) as count, 'Opened' AS key
    FROM ticketchangelog
    WHERE dateopen IS NOT NULL
    GROUP BY dateopen
)
ORDER BY key, date
