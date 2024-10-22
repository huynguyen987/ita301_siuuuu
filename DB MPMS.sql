DROP DATABASE IF EXISTS mpms;
CREATE DATABASE IF NOT EXISTS mpms;

-- Use the created database
USE mpms;

-- Bảng 'setting' chứa các thông tin chung như User Roles, Departments, Project Roles, v.v.
CREATE TABLE setting (
    setting_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL, -- Tên cấu hình
    value VARCHAR(255), -- Giá trị cấu hình
    type_id INT, -- Loại cấu hình
    priority INT, -- Độ ưu tiên
    status ENUM('active', 'inactive') DEFAULT 'active', -- Trạng thái cấu hình
    description TEXT, -- Mô tả cấu hình
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'user' quản lý thông tin người dùng
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL, -- Tên đầy đủ
    user_name VARCHAR(255) UNIQUE NOT NULL, -- Tên đăng nhập
    email VARCHAR(255) UNIQUE NOT NULL, -- Email
    mobile VARCHAR(15), -- Số điện thoại
    password VARCHAR(255) NOT NULL, -- Mật khẩu
    role_id INT, -- FK đến setting (vai trò người dùng)
    status ENUM('active', 'inactive', 'unverified') DEFAULT 'unverified', -- Trạng thái người dùng
    note TEXT, -- Ghi chú
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'project' quản lý các dự án
CREATE TABLE project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL, -- Tên dự án
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã dự án
    start_date DATE, -- Ngày bắt đầu
    end_date DATE, -- Ngày kết thúc
    dept_id INT, -- FK đến setting (phòng ban)
    status ENUM('pending', 'in-progress', 'closed', 'cancelled') DEFAULT 'pending', -- Trạng thái dự án
    description TEXT, -- Mô tả dự án
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'allocation' quản lý phân bổ thành viên vào dự án với vai trò cụ thể
CREATE TABLE allocation (
    member_id INT, -- FK đến user (thành viên)
    project_id INT, -- FK đến project (dự án)
    role_id INT, -- FK đến setting (vai trò trong dự án)
    from_date DATE, -- Ngày bắt đầu
    to_date DATE, -- Ngày kết thúc
    effort INT, -- Nỗ lực đóng góp (theo phần trăm)
    description TEXT, -- Mô tả
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT, -- FK đến user (Người cập nhật)
    PRIMARY KEY (member_id, project_id, role_id)
);

-- Bảng 'requirement' quản lý các yêu cầu của dự án
CREATE TABLE requirement (
    req_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL, -- Tiêu đề yêu cầu
    owner_id INT, -- FK đến user (chủ sở hữu yêu cầu)
    complexity_id INT, -- FK đến setting (độ phức tạp yêu cầu)
    status_id INT, -- FK đến setting (trạng thái yêu cầu)
    description TEXT, -- Mô tả yêu cầu
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'issue' quản lý các vấn đề phát sinh trong dự án
CREATE TABLE issue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL, -- Tiêu đề vấn đề
    type_id INT, -- FK đến setting (loại vấn đề)
    req_id INT, -- FK đến requirement (yêu cầu liên quan)
    assigner_id INT, -- FK đến user (người giao vấn đề)
    assignee_id INT, -- FK đến user (người được giao vấn đề)
    deadline DATE, -- Hạn chót
    status ENUM('pending', 'to do', 'doing', 'done', 'closed') DEFAULT 'pending', -- Trạng thái vấn đề
    status_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày cập nhật trạng thái
    description TEXT, -- Mô tả
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Liên kết các khóa ngoại

-- Liên kết user với setting (vai trò người dùng)
ALTER TABLE user
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role_id) REFERENCES setting(setting_id);

-- Liên kết project với setting (phòng ban)
ALTER TABLE project
ADD CONSTRAINT fk_project_department
FOREIGN KEY (dept_id) REFERENCES setting(setting_id);

-- Liên kết allocation với setting (vai trò trong dự án)
ALTER TABLE allocation
ADD CONSTRAINT fk_allocation_project_role
FOREIGN KEY (role_id) REFERENCES setting(setting_id);

-- Liên kết requirement với setting (độ phức tạp yêu cầu)
ALTER TABLE requirement
ADD CONSTRAINT fk_requirement_complexity
FOREIGN KEY (complexity_id) REFERENCES setting(setting_id);

-- Liên kết requirement với setting (trạng thái yêu cầu)
ALTER TABLE requirement
ADD CONSTRAINT fk_requirement_status
FOREIGN KEY (status_id) REFERENCES setting(setting_id);

-- Liên kết issue với setting (loại vấn đề)
ALTER TABLE issue
ADD CONSTRAINT fk_issue_type
FOREIGN KEY (type_id) REFERENCES setting(setting_id);

-- Chèn dữ liệu vào bảng setting cho các loại vai trò, phòng ban, độ phức tạp yêu cầu, trạng thái yêu cầu và loại vấn đề
INSERT INTO setting (name, value, type_id, priority, status, description) VALUES
('Admin', 'Administrator', 1, 1, 'active', 'User role for system administrators'),
('Developer', 'Developer', 1, 2, 'active', 'User role for software developers'),
('Project Manager', 'PM', 3, 1, 'active', 'Role for project managers in project allocation'),
('Team Lead', 'TL', 3, 2, 'active', 'Role for team leads in project allocation'),
('IT', 'Information Technology', 2, 1, 'active', 'Department of IT professionals'),
('HR', 'Human Resources', 2, 2, 'active', 'Department of human resources'),
('High Complexity', '3', 4, 1, 'active', 'Complexity level for difficult requirements'),
('Medium Complexity', '2', 4, 2, 'active', 'Complexity level for medium difficulty requirements'),
('Low Complexity', '1', 4, 3, 'active', 'Complexity level for simple requirements'),
('New', 'new', 5, 1, 'active', 'Requirement is newly created'),
('In Progress', 'in_progress', 5, 2, 'active', 'Requirement is being worked on'),
('Done', 'done', 5, 3, 'active', 'Requirement is completed'),
('Bug', 'bug', 6, 1, 'active', 'Issue type for bugs'),
('Feature Request', 'feature', 6, 2, 'active', 'Issue type for new features');

-- Chèn dữ liệu người dùng vào bảng user
INSERT INTO user (full_name, user_name, email, mobile, password, role_id, status, note) VALUES
('John Doe', 'jdoe', 'john.doe@example.com', '123456789', 'password123', 1, 'active', 'System Administrator'),
('Jane Smith', 'jsmith', 'jane.smith@example.com', '987654321', 'password456', 2, 'active', 'Lead Developer'),
('Michael Brown', 'mbrown', 'michael.brown@example.com', '456789123', 'password789', 2, 'inactive', 'Software Developer'),
('Mike Vol' ,  'miol' , 'mike.miol@example.com','172690456', 'password981', 4, 'active', 'Team Lead');

-- Chèn dữ liệu vào bảng project
INSERT INTO project (name, code, start_date, end_date, dept_id, status, description) VALUES
('Website Redesign', 'WEB-001', '2024-01-01', '2024-06-01', 5, 'in-progress', 'Redesign the company website'),
('HR System Implementation', 'HR-002', '2024-02-15', '2024-07-15', 6, 'pending', 'Implement a new HR management system');

-- Chèn dữ liệu phân bổ vào bảng allocation
INSERT INTO allocation (member_id, project_id, role_id, from_date, to_date, effort, description) VALUES
(1, 1, 3, '2024-01-01', '2024-06-01', 100, 'John Doe is the project manager for the website redesign project'),
(2, 1, 4, '2024-01-01', '2024-06-01', 80, 'Jane Smith is the team lead for the website redesign project'),
(3, 2, 4, '2024-02-15', '2024-07-15', 100, 'Michael Brown is the team lead for the HR system implementation project');

-- Chèn dữ liệu yêu cầu vào bảng requirement
INSERT INTO requirement (title, owner_id, complexity_id, status_id, description) VALUES
('Redesign Homepage', 2, 7, 10, 'Redesign the homepage to improve user experience'),
('Implement Authentication', 2, 7, 11, 'Implement a secure authentication system for the HR project');

-- Chèn dữ liệu vấn đề vào bảng issue
INSERT INTO issue (title, type_id, req_id, assigner_id, assignee_id, deadline, status, status_date, description) VALUES
('Homepage Bug', 13, 1, 2, 3, '2024-03-01', 'to do', '2024-01-15', 'Fix the layout bug on the homepage after redesign'),
('Authentication Feature Request', 14, 2, 1, 2, '2024-04-01', 'pending', '2024-02-20', 'Add a 2-factor authentication feature');

