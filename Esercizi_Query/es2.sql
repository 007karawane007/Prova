/*
Table: Employee

+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table indicates the ID of an employee, their name, salary, and the ID of their manager.

Write a solution to find the employees who earn more than their managers.
Return the result table in any order.
*/
-- Write your PostgreSQL query statement below
SELECT  e1.name as Employee
FROM    Employee as e1, Employee as e2
WHERE   e1.managerId = e2.id and e1.salary > e2.salary
