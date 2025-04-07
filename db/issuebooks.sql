CREATE TABLE issuebooks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    book_callno VARCHAR(50) NOT NULL,
    student_id VARCHAR(20),
    student_name VARCHAR(100),
    student_mobile VARCHAR(20),
    issued_date DATE DEFAULT CURRENT_DATE,
    return_status VARCHAR(20) DEFAULT 'no'
);
