-- ============================================================
-- Library Management System - Database Setup Script
-- ============================================================
-- This script creates the complete database structure for
-- the Library Management System application
-- ============================================================

-- Create database
CREATE DATABASE IF NOT EXISTS librarymanagementsystem;
USE librarymanagementsystem;

-- ============================================================
-- Table: books
-- Description: Stores information about books in the library
-- ============================================================
CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(50) NOT NULL,
    description VARCHAR(2000),
    book_type VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================================
-- Table: authors
-- Description: Stores information about book authors
-- ============================================================
CREATE TABLE IF NOT EXISTS authors (
    auth_id INT PRIMARY KEY AUTO_INCREMENT,
    auth_name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- Table: book_auth
-- Description: Junction table linking books and authors (Many-to-Many relationship)
-- ============================================================
CREATE TABLE IF NOT EXISTS book_auth (
    book_auth_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    auth_id INT NOT NULL,
    FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE CASCADE,
    FOREIGN KEY (auth_id) REFERENCES authors (auth_id) ON DELETE CASCADE,
    UNIQUE KEY unique_book_author (book_id, auth_id)
);

-- ============================================================
-- Table: roles
-- Description: Stores user roles for role-based access control
-- ============================================================
CREATE TABLE IF NOT EXISTS roles (
    role_id INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL UNIQUE,
    role_description VARCHAR(2000)
);

-- ============================================================
-- Table: users
-- Description: Stores user account information and credentials
-- ============================================================
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role_id INT NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);

-- ============================================================
-- Table: books_issued
-- Description: Tracks book issuance and return history
-- ============================================================
CREATE TABLE IF NOT EXISTS books_issued (
    book_issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT NOT NULL,
    user_id INT NOT NULL,
    issued_at DATETIME NOT NULL,
    returned_at DATETIME NULL,
    due_date DATETIME,
    is_returned BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (book_id) REFERENCES books (book_id) ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE RESTRICT
);

-- ============================================================
-- Indexes for Performance Optimization
-- ============================================================

-- Indexes for book_auth table
CREATE INDEX IF NOT EXISTS idx_book_id ON book_auth(book_id);
CREATE INDEX IF NOT EXISTS idx_auth_id ON book_auth(auth_id);

-- Indexes for users table
CREATE INDEX IF NOT EXISTS idx_user_role ON users(role_id);
CREATE INDEX IF NOT EXISTS idx_user_email ON users(email);
CREATE INDEX IF NOT EXISTS idx_user_username ON users(user_name);

-- Indexes for books_issued table
CREATE INDEX IF NOT EXISTS idx_books_issued_user ON books_issued(user_id);
CREATE INDEX IF NOT EXISTS idx_books_issued_book ON books_issued(book_id);
CREATE INDEX IF NOT EXISTS idx_books_issued_issued_at ON books_issued(issued_at);
CREATE INDEX IF NOT EXISTS idx_books_issued_is_returned ON books_issued(is_returned);

-- ============================================================
-- Insert Initial Data
-- ============================================================

-- Insert default roles
INSERT INTO roles (role_name, role_description) VALUES
('Admin', 'Has full access to manage books, authors, and users'),
('Member', 'Can borrow books and view issued history');

-- ============================================================
-- Sample Data (Optional - Comment out if not needed)
-- ============================================================

-- Insert sample authors
INSERT INTO authors (auth_name) VALUES
('J.K. Rowling'),
('J.R.R. Tolkien'),
('George R.R. Martin'),
('Stephen King');

-- Insert sample books
INSERT INTO books (book_name, description, book_type) VALUES
('Harry Potter and the Philosopher\'s Stone', 'A young wizard discovers his magical abilities', 'Fantasy'),
('The Hobbit', 'An unexpected journey of a hobbit', 'Fantasy'),
('A Game of Thrones', 'A complex tale of power and politics', 'Fantasy'),
('The Shining', 'A psychological horror novel', 'Horror');

-- Link books with authors
INSERT INTO book_auth (book_id, auth_id) VALUES
(1, 1), -- Harry Potter by J.K. Rowling
(2, 2), -- The Hobbit by J.R.R. Tolkien
(3, 3), -- A Game of Thrones by George R.R. Martin
(4, 4); -- The Shining by Stephen King

-- Insert sample admin user
-- Password: admin123 (you should hash this in production)
INSERT INTO users (user_name, email, name, password_hash, role_id) VALUES
('admin', 'admin@library.com', 'Administrator', 'admin123', 1);

-- Insert sample member users
-- Password: member123 (you should hash this in production)
INSERT INTO users (user_name, email, name, password_hash, role_id) VALUES
('john_doe', 'john@example.com', 'John Doe', 'member123', 2),
('jane_smith', 'jane@example.com', 'Jane Smith', 'member123', 2);

-- Insert sample book issuance record
-- Note: Some fields may be NULL if book hasn't been returned yet
INSERT INTO books_issued (book_id, user_id, issued_at, due_date, returned_at, is_returned) VALUES
(1, 2, '2025-11-01 10:00:00', '2025-11-15 10:00:00', NULL, FALSE),
(2, 3, '2025-11-05 14:30:00', '2025-11-19 14:30:00', '2025-11-12 16:45:00', TRUE);

-- ============================================================
-- Verification Queries
-- ============================================================
-- Run these queries to verify the data is correctly inserted:

-- SELECT * FROM books;
-- SELECT * FROM authors;
-- SELECT * FROM book_auth;
-- SELECT * FROM roles;
-- SELECT * FROM users;
-- SELECT * FROM books_issued;

-- View books with their authors (Join query)
-- SELECT 
--     b.book_id,
--     b.book_name,
--     GROUP_CONCAT(a.auth_name SEPARATOR ', ') AS authors,
--     b.book_type
-- FROM books b
-- LEFT JOIN book_auth ba ON b.book_id = ba.book_id
-- LEFT JOIN authors a ON ba.auth_id = a.auth_id
-- GROUP BY b.book_id, b.book_name, b.book_type;

-- ============================================================
-- End of Database Setup Script
-- ============================================================
