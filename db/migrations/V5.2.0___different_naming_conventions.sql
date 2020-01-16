-- firstname -> givenName + datatype changes
ALTER TABLE employees CHANGE firstname givenName VARCHAR(128);
ALTER TABLE employees CHANGE lastname familyName VARCHAR(128);
ALTER TABLE employees CHANGE username handle VARCHAR(128);

-- Prepare age for change from decimal to int
UPDATE employees SET age = ROUND(age);

-- Change the age column from decimal to int
ALTER TABLE employees MODIFY age INTEGER NOT NULL;