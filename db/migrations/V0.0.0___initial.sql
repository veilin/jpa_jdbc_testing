CREATE TABLE IF NOT EXISTS employees (
    id INT NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    age DECIMAL(4,2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_email UNIQUE (email)
);

INSERT INTO employees(firstname, lastname, username, email, age) VALUES('john', 'doe', 'employee1', 'john@doe.com', 21.5);
INSERT INTO employees(firstname, lastname, username, email, age) VALUES('jay', 'doe', 'employee2', 'jay@doe.com', 22.4);
INSERT INTO employees(firstname, lastname, username, email, age) VALUES('jeffrey', 'doe', 'employee3', 'jeffrey@doe.com', 23.5);
INSERT INTO employees(firstname, lastname, username, email, age) VALUES('jane', 'doe', 'employee4', 'jane@doe.com', 24.4);
INSERT INTO employees(firstname, lastname, username, email, age) VALUES('joey', 'doe', 'employee5', 'joey@doe.com', 25.5);
INSERT INTO employees(firstname, lastname, username, email, age) VALUES('jordan', 'doe', 'employee6', 'jordan@doe.com', 26.4);
