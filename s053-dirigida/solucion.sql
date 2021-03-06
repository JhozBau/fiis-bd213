--SOLUCION PRACTICA DIRIGIDA

--1
SELECT E.LAST_NAME, E.HIRE_DATE, E.SALARY, D.DEPARTMENT_NAME, J.JOB_TITLE
FROM EMPLOYEES E, DEPARTMENTS D, JOBS J
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND E.JOB_ID = J.JOB_ID
AND E.SALARY >= 4500
AND E.SALARY < 15000;


--2
SELECT E.EMPLOYEE_ID, E.FIRST_NAME || ' ' || E.LAST_NAME, C.COUNTRY_NAME, J.JOB_TITLE
FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L, COUNTRIES C, JOBS J
WHERE E.JOB_ID = J.JOB_ID
AND E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND J.JOB_TITLE <> 'Accountant';

--3

SELECT E.*, L.CITY, C.COUNTRY_NAME
FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L, COUNTRIES C
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND (L.CITY = 'Mexico City' OR C.COUNTRY_NAME = 'United States of America');
--AND (L.CITY = 'Mexico City' OR C.COUNTRY_NAME LIKE 'United States%');

--4
SELECT E.*
FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L, COUNTRIES C
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND D.DEPARTMENT_NAME = 'Sales'
AND C.COUNTRY_NAME = 'United Kingdom';


--5
SELECT E.FIRST_NAME, E.LAST_NAME, C.COUNTRY_NAME, R.REGION_NAME
FROM    EMPLOYEES E, 
        DEPARTMENTS D,
        LOCATIONS L,
        COUNTRIES C,
        REGIONS R,
        (SELECT JOB_ID, AVG(SALARY) AVERAGE
        FROM EMPLOYEES
        GROUP BY JOB_ID) P
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND C.REGION_ID = R.REGION_ID
AND R.REGION_NAME = 'Americas'
AND E.JOB_ID = P.JOB_ID
AND E.SALARY < P.AVERAGE;

--6
SELECT R.REGION_NAME, C.COUNTRY_NAME, COUNT(*)
FROM EMPLOYEES E, DEPARTMENTS D, LOCATIONS L, COUNTRIES C, REGIONS R
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND C.REGION_ID = R.REGION_ID
GROUP BY R.REGION_NAME, C.COUNTRY_NAME
HAVING COUNT(*) > 3;


--7

SELECT MAX(HIRE_DATE), MIN(HIRE_DATE), MAX(HIRE_DATE) - MIN(HIRE_DATE) FROM EMPLOYEES;

SELECT MAX(HIRE_DATE) - MIN(HIRE_DATE) 
FROM EMPLOYEES E, DEPARTMENTS D
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.DEPARTMENT_NAME <> 'Executive';



select MGR.FIRST_NAME, MGR.LAST_NAME, COUNT(*) 
from employees E, EMPLOYEES MGR
where E.MANAGER_ID = MGR.EMPLOYEE_ID
group by MGR.FIRST_NAME, MGR.LAST_NAME
having COUNT(*) < 8
order by COUNT(*) DESC



--8
--SOLO MANAGERS CON SUS SUBORDINADOS
SELECT  MGR.EMPLOYEE_ID, MGR.FIRST_NAME, MGR.LAST_NAME, COUNT(*)
FROM EMPLOYEES E, EMPLOYEES MGR
WHERE E.MANAGER_ID = MGR.EMPLOYEE_ID
GROUP BY  MGR.EMPLOYEE_ID, MGR.FIRST_NAME, MGR.LAST_NAME
HAVING COUNT(*) < 8
ORDER BY COUNT(*) DESC;


--MOSTRAR TODOS LOS EMPLEADOS CON SU CANTIDAD DE SUBORDINADOS (OUTER JOIN)
SELECT E.EMPLOYEE_ID, 
       E.FIRST_NAME,
       E.LAST_NAME,
       COUNT(SUB.EMPLOYEE_ID)
FROM EMPLOYEES E 
LEFT OUTER JOIN EMPLOYEES SUB
ON E.EMPLOYEE_ID = SUB.MANAGER_ID
GROUP BY E.EMPLOYEE_ID, E.FIRST_NAME, E.LAST_NAME
HAVING COUNT(SUB.EMPLOYEE_ID) < 8
ORDER BY COUNT(SUB.EMPLOYEE_ID) DESC;

select LAST_NAME, HIRE_DATE, extract(year from AGE(CURRENT_DATE, HIRE_DATE)),
 extract(month from AGE(CURRENT_DATE, HIRE_DATE))
from EMPLOYEES


--9
SELECT  'El empleado ' ||
        E.FIRST_NAME || ' ' || E.LAST_NAME ||
        ' ha trabajado por: ' ||
        EXTRACT(YEAR FROM AGE(CURRENT_DATE, HIRE_DATE)) --ANIOS
        || ' anios, ' ||
        EXTRACT(MONTH FROM AGE(CURRENT_DATE, HIRE_DATE)) --MESES
        || ' meses y ' ||
        EXTRACT(DAY FROM AGE(CURRENT_DATE, HIRE_DATE)) --DIAS
        || ' dias'
FROM    EMPLOYEES E,        
        DEPARTMENTS D,
        LOCATIONS L,
        COUNTRIES C,
        REGIONS R
WHERE E.DEPARTMENT_ID = D.DEPARTMENT_ID
AND D.LOCATION_ID = L.LOCATION_ID
AND L.COUNTRY_ID = C.COUNTRY_ID
AND C.REGION_ID = R.REGION_ID
AND R.REGION_NAME <> 'Europe'
AND E.HIRE_DATE < TO_DATE('01/01/1997', 'DD/MM/YYYY');


--10
--OBTENER INFORMACION DE ROLES ANTERIORES (JOB HISTORY)
SELECT E.EMPLOYEE_ID, J.JOB_TITLE, JH.START_DATE, JH.END_DATE
FROM EMPLOYEES E, JOBS J, JOB_HISTORY JH
WHERE E.EMPLOYEE_ID = JH.EMPLOYEE_ID
AND JH.JOB_ID = J.JOB_ID
--UNIMOS RESULTADOS
UNION
--OBTENER INFORMACION DEL ROL ACTUAL (JOB_ID)
SELECT  E.EMPLOYEE_ID,
        J.JOB_TITLE, 
        COALESCE((   SELECT MAX(END_DATE) + 1 
                FROM JOB_HISTORY 
                WHERE EMPLOYEE_ID = E.EMPLOYEE_ID),
                E.HIRE_DATE
            ),                
        NULL --FECHA FIN NO DEFINIDA: SIGUE TRABAJANDO
FROM EMPLOYEES E, JOBS J
WHERE E.JOB_ID = J.JOB_ID;


--REVISAR LOS DATOS DEL EMPLEADO 101
SELECT * FROM (
    
    SELECT E.EMPLOYEE_ID, J.JOB_TITLE, JH.START_DATE, JH.END_DATE
    FROM EMPLOYEES E, JOBS J, JOB_HISTORY JH
    WHERE E.EMPLOYEE_ID = JH.EMPLOYEE_ID
    AND JH.JOB_ID = J.JOB_ID
    --UNIMOS RESULTADOS
    UNION
    --OBTENER INFORMACION DEL ROL ACTUAL (JOB_ID)
    SELECT  E.EMPLOYEE_ID,
            J.JOB_TITLE,
            --COALESCE: PRIMER ROL - RESULTADO SERA NULO
            COALESCE((   SELECT MAX(END_DATE) + 1 
                    FROM JOB_HISTORY 
                    WHERE EMPLOYEE_ID = E.EMPLOYEE_ID),
                    E.HIRE_DATE
                ),                
            NULL --FECHA FIN NO DEFINIDA: SIGUE TRABAJANDO
    FROM EMPLOYEES E, JOBS J
    WHERE E.JOB_ID = J.JOB_ID
) A
WHERE A.EMPLOYEE_ID = 101
ORDER BY A.START_DATE;


--11
DROP TABLE IF EXISTS EMP001;
CREATE TABLE EMP001 AS(
                        SELECT  LPAD(CAST(E.EMPLOYEE_ID AS VARCHAR), 8, '0') AS EMPLOYEE_ID , UPPER(E.FIRST_NAME) AS FIRST_NAME, 
                                UPPER(E.LAST_NAME) AS LAST_NAME, M.LAST_NAME AS MANAGER, D.DEPARTMENT_NAME AS DEPARTMENT,
                                E.SALARY, E.JOB_ID, E.COMMISSION_PCT
                        FROM    EMPLOYEES E,
                                EMPLOYEES M,
                                DEPARTMENTS D
                        WHERE E.SALARY > (SELECT AVG(SALARY) FROM EMPLOYEES)
                        AND E.MANAGER_ID = M.EMPLOYEE_ID
                        AND E.DEPARTMENT_ID = D.DEPARTMENT_ID
                    );

--12
UPDATE EMP001 E SET SALARY = 1.1 * SALARY
WHERE SALARY < ( SELECT AVG(SALARY) 
                 FROM EMP001 E1
                 WHERE E1.DEPARTMENT = E.DEPARTMENT);
                 
--13
UPDATE EMP001 E
SET SALARY = SALARY + 100
WHERE JOB_ID = (SELECT JOB_ID FROM JOBS WHERE JOB_TITLE = 'Programmer');

--14
ALTER TABLE EMP001 ADD COMMISSION NUMERIC(8, 2);
ALTER TABLE EMP001 ADD TOTAL_SALARY NUMERIC(8,2);

--15
UPDATE EMP001 SET COMMISSION = SALARY * COALESCE(COMMISSION_PCT, 0);
UPDATE EMP001 SET TOTAL_SALARY = SALARY + COMMISSION;


--16
DELETE FROM EMP001 
WHERE EMPLOYEE_ID IN(
                        SELECT E.EMPLOYEE_ID                    
                        FROM    EMP001 E,
                                DEPARTMENTS D,
                                LOCATIONS L,
                                COUNTRIES C,
                                REGIONS R
                        WHERE E.DEPARTMENT = D.DEPARTMENT_NAME
                        AND D.LOCATION_ID = L.LOCATION_ID
                        AND L.COUNTRY_ID = C.COUNTRY_ID
                        AND C.REGION_ID = R.REGION_ID
                        AND R.REGION_NAME = 'Americas'
                    );
--17
TRUNCATE TABLE EMP001;

--18
DROP TABLE IF EXISTS EMP001;
CREATE TABLE EMP001 AS(
                        SELECT  LPAD(CAST(E.EMPLOYEE_ID AS VARCHAR), 8, '0') EMPLOYEE_ID , UPPER(E.FIRST_NAME) FIRST_NAME, 
                                UPPER(E.LAST_NAME) LAST_NAME, M.LAST_NAME MANAGER, D.DEPARTMENT_NAME DEPARTMENT,
                                E.SALARY, E.JOB_ID, E.COMMISSION_PCT,
                                COALESCE(E.COMMISSION_PCT, 0) * E.SALARY AS COMMISION,
                                COALESCE(E.COMMISSION_PCT, 0) * E.SALARY + E.SALARY AS TOTAL_SALARY
                        FROM    EMPLOYEES E,
                                EMPLOYEES M,
                                DEPARTMENTS D
                        WHERE E.SALARY > (SELECT AVG(SALARY) FROM EMPLOYEES)
                        AND E.MANAGER_ID = M.EMPLOYEE_ID
                        AND E.DEPARTMENT_ID = D.DEPARTMENT_ID
                    );
