CREATE SCHEMA MYSCHEMA;

CREATE TABLE MYSCHEMA.EMPLOYEES (
	EmployeeID INT GENERATED ALWAYS as IDENTITY,
	Name VARCHAR(50) NOT NULL,
	Salary DOUBLE NOT NULL,
	Region VARCHAR(50) NOT NULL,
	PRIMARY KEY (EmployeeID)
);

INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Andy', 25000.00, 'South Wales');
	
INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Nigel', 52000.00, 'Home Counties');
	
INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Claire', 37000.00, 'Kent');
	
INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Mary', 42000.00, 'London');
	
INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Mungo', 47000.00, 'Cumbria');
	
INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Midge', 72000.00, 'Scotland');

INSERT 
	INTO MYSCHEMA.EMPLOYEES (Name, Salary, Region)
	VALUES('Hayley', 69000.00, 'Northern Ireland');

