CREATE TABLE IF NOT EXISTS auth_users (
    id VARCHAR(50) PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE,
    address VARCHAR(200),
    phone_number VARCHAR(20),
    email VARCHAR(150) UNIQUE,
    base_salary DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);