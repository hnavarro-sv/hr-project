DROP TYPE type_dept;
DROP TYPE type_emps;
DROP TYPE type_emp;

CREATE OR REPLACE TYPE type_emp
AS
  OBJECT
  (
    firstName VARCHAR2(20 BYTE),
    lastName  VARCHAR2(25 BYTE),
    email     VARCHAR2(25 BYTE),
    hireDate  DATE,
    salary    NUMBER(16,2)
  );
/
CREATE OR REPLACE TYPE type_emps AS VARRAY(150) OF type_emp;
/  
CREATE OR REPLACE TYPE type_dept
AS
  OBJECT
  (
    departmentId   NUMBER(4),
    departmentName VARCHAR2(30 BYTE),
    emps type_emps 
  );
/
CREATE OR REPLACE
PACKAGE PKG_HR
AS
PROCEDURE PROC_DEPT_EMPS(IN_DEPT_ID IN NUMBER, OUT_DEPT_NAME OUT VARCHAR2, OUT_DEPT_EMPS OUT TYPE_EMPS);
END PKG_HR;
/
CREATE OR REPLACE
PACKAGE BODY PKG_HR
AS
PROCEDURE proc_dept_emps(in_dept_id IN NUMBER, out_dept_name OUT VARCHAR2, out_dept_emps OUT type_emps)
AS
  CURSOR cur_dept_emps (id NUMBER)
  IS
    SELECT e.first_name,
      e.last_name,
      e.email,
      e.hire_date,
      e.salary
    FROM employees e
    WHERE e.department_id = id;
BEGIN
  out_dept_emps := type_emps() ;

  FOR rec IN cur_dept_emps(in_dept_id)
  LOOP
    out_dept_emps.EXTEND;
    out_dept_emps(out_dept_emps.COUNT) := type_emp(rec.first_name,rec.last_name,rec.email,rec.hire_date,rec.salary);
  END LOOP;
  
  SELECT d.department_name 
    INTO out_dept_name
  FROM departments d 
  WHERE d.department_id = in_dept_id;
END proc_dept_emps;

END PKG_HR;
/