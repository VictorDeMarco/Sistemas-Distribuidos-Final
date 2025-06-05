CREATE TABLE IF NOT EXISTS usuarios (
                                        `id` INT NOT NULL AUTO_INCREMENT,
                                        PRIMARY KEY (`id`),
                                        username VARCHAR(255) NOT NULL UNIQUE,
                                        password VARCHAR(255) NOT NULL
);

ALTER TABLE usuarios ADD COLUMN rol VARCHAR(50) DEFAULT 'USER';

CREATE TABLE IF NOT EXISTS productos (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL,
                                         precio DECIMAL(10, 2) NOT NULL
);

INSERT INTO productos (nombre, precio) VALUES
                                           ('Galletas', 1.99),
                                           ('Cafe', 2.50),
                                           ('Chocolate', 3.75),
                                           ('Nestea', 3.20);
