/*
Table: Triangle

+-------------+------+
| Column Name | Type |
+-------------+------+
| x           | int  |
| y           | int  |
| z           | int  |
+-------------+------+
In SQL, (x, y, z) is the primary key column for this table.
Each row of this table contains the lengths of three line segments.
 

Report for every three line segments whether they can form a triangle
*/
-- Write your PostgreSQL query statement below
SELECT  *, 'Yes' as triangle
FROM    Triangle
WHERE   x + y > z and x + z > y and y + z > x
UNION
SELECT  *, 'No' as triangle
FROM    Triangle
WHERE   x + y <= z or x + z <= y or y + z <= x