CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    callno VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    publisher VARCHAR(100),
    quantity INT,
    issued INT DEFAULT 0,
    added_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
