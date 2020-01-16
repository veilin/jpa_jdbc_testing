-- First add the column
ALTER TABLE employees ADD password VARCHAR(255) AFTER email;

-- Then set the value to some default for every employee that does not have this set (which should be everyone)
UPDATE employees SET password = SHA2('pass123', 256) WHERE password IS NULL;

-- Now add the NOT NULL requirement to the data type
ALTER TABLE employees MODIFY password VARCHAR (255) NOT NULL;