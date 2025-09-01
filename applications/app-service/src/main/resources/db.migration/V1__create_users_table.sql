CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(50) PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    address VARCHAR(200),
    phone_number VARCHAR(20),
    email VARCHAR(150) UNIQUE,
    base_salary DECIMAL(10,2)
);