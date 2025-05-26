/*
Table: ActorDirector

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| actor_id    | int     |
| director_id | int     |
| timestamp   | int     |
+-------------+---------+
timestamp is the primary key (column with unique values) for this table.
 

Write a solution to find all the pairs (actor_id, director_id) where the actor has cooperated with the director at least three times.
*/
-- Write your PostgreSQL query statement below
SELECT ACTOR_ID , DIRECTOR_ID 
FROM ACTORDIRECTOR
GROUP BY ACTOR_ID,DIRECTOR_ID 
HAVING COUNT(DIRECTOR_ID) >= 3