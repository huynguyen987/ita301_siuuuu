-- Tạo cơ sở dữ liệu nếu chưa tồn tại
DROP DATABASE IF EXISTS mpms;
CREATE DATABASE IF NOT EXISTS mpms;

-- Sử dụng cơ sở dữ liệu
USE mpms;

-- Bảng 'user' quản lý thông tin người dùng
CREATE TABLE user (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(50) NOT NULL, -- Tên đầy đủ
    user_name VARCHAR(50) UNIQUE NOT NULL, -- Tên đăng nhập
    email VARCHAR(50) UNIQUE NOT NULL, -- Email
    password VARCHAR(255) NOT NULL, -- Mật khẩu đã được băm
    role_id INT, -- FK đến setting (vai trò người dùng)
    dept_id INT, -- FK đến setting (phòng ban)
    start_date DATE, -- Ngày bắt đầu làm việc
    status BIT DEFAULT NULL, -- Trạng thái người dùng: 1 = active, 0 = inactive, NULL = unverified
    note TEXT, -- Ghi chú
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'setting' chứa các thông tin chung như User Roles, Departments, Project Roles, v.v.
CREATE TABLE setting (
    setting_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL, -- Tên cấu hình
    value VARCHAR(50), -- Giá trị cấu hình
    type VARCHAR(50), -- Loại cấu hình (ví dụ: role, department)
    priority INT, -- Độ ưu tiên
    status BIT DEFAULT 1, -- Trạng thái cấu hình: 1 = active, 0 = inactive
    description TEXT, -- Mô tả cấu hình
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT -- FK đến user (Người cập nhật)
);

-- Bảng 'project' quản lý các dự án
CREATE TABLE project (
    project_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL, -- Tên dự án
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã dự án
    start_date DATE, -- Ngày bắt đầu
    end_date DATE, -- Ngày kết thúc
    dept_id INT, -- FK đến setting (phòng ban)
    status INT, -- FK đến setting (trạng thái dự án)
    description TEXT, -- Mô tả dự án
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Thời gian cập nhật
);

-- Bảng 'allocation' quản lý phân bổ thành viên vào dự án
CREATE TABLE allocation (
    member_id INT, -- FK đến user (thành viên)
    project_id INT, -- FK đến project (dự án)
    role_id INT, -- FK đến setting (vai trò trong dự án)
    from_date DATE, -- Ngày bắt đầu
    to_date DATE, -- Ngày kết thúc
    effort DECIMAL(5,2), -- Nỗ lực đóng góp (theo phần trăm)
    description TEXT, -- Mô tả
    status BIT DEFAULT 0, -- Trạng thái active (1) hoặc inactive (0)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
    updated_by_id INT, -- FK đến user (Người cập nhật)

    -- Khóa chính cho bảng
    PRIMARY KEY (member_id, project_id, role_id)
);

-- Bảng 'requirement' quản lý các yêu cầu của dự án
CREATE TABLE requirement (
    req_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL, -- Tiêu đề yêu cầu
    owner_id INT, -- FK đến user (chủ sở hữu yêu cầu)
    complexity_id INT, -- FK đến setting (độ phức tạp yêu cầu)
    status_id INT, -- FK đến setting (trạng thái yêu cầu)
    description TEXT, -- Mô tả yêu cầu
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Thời gian cập nhật
);

-- Bảng 'issue' quản lý các vấn đề phát sinh trong dự án
CREATE TABLE issue (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL, -- Tiêu đề vấn đề
    type_id INT, -- FK đến setting (loại vấn đề)
    req_id INT, -- FK đến requirement (yêu cầu liên quan)
    assigner_id INT, -- FK đến user (người giao vấn đề)
    assignee_id INT, -- FK đến user (người được giao vấn đề)
    deadline DATE, -- Hạn chót
    status_id INT, -- FK đến setting (trạng thái vấn đề)
    status_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày cập nhật trạng thái
    description TEXT, -- Mô tả
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
    created_by_id INT, -- FK đến user (Người tạo)
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Thời gian cập nhật
);

-- Khóa ngoại cho bảng 'user'
ALTER TABLE user
ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_user_department FOREIGN KEY (dept_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_user_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_user_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);

-- Khóa ngoại cho bảng 'setting'
ALTER TABLE setting
ADD CONSTRAINT fk_setting_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_setting_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);

-- Modify the 'project' table to add the missing 'updated_by_id' column
ALTER TABLE project
ADD COLUMN updated_by_id INT; -- Add the missing column for the foreign key

-- Now add the foreign key constraints
ALTER TABLE project
ADD CONSTRAINT fk_project_department FOREIGN KEY (dept_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_project_status FOREIGN KEY (status) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_project_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_project_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);


-- Khóa ngoại cho bảng 'allocation'
ALTER TABLE allocation
ADD CONSTRAINT fk_allocation_project_role FOREIGN KEY (role_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_allocation_project FOREIGN KEY (project_id) REFERENCES project(project_id),
ADD CONSTRAINT fk_allocation_member FOREIGN KEY (member_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_allocation_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_allocation_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);

-- Modify the 'requirement' table to add the missing 'updated_by_id' column
ALTER TABLE requirement
ADD COLUMN updated_by_id INT; -- Add the missing column for the foreign key

-- Now add the foreign key constraints
ALTER TABLE requirement
ADD CONSTRAINT fk_requirement_owner FOREIGN KEY (owner_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_requirement_complexity FOREIGN KEY (complexity_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_requirement_status FOREIGN KEY (status_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_requirement_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_requirement_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);


-- Modify the 'issue' table to add the missing 'updated_by_id' column
ALTER TABLE issue
ADD COLUMN updated_by_id INT; -- Add the missing column for the foreign key

-- Now add the foreign key constraints
ALTER TABLE issue
ADD CONSTRAINT fk_issue_type FOREIGN KEY (type_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_issue_req FOREIGN KEY (req_id) REFERENCES requirement(req_id),
ADD CONSTRAINT fk_issue_assigner FOREIGN KEY (assigner_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_issue_assignee FOREIGN KEY (assignee_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_issue_status FOREIGN KEY (status_id) REFERENCES setting(setting_id),
ADD CONSTRAINT fk_issue_created_by FOREIGN KEY (created_by_id) REFERENCES user(user_id),
ADD CONSTRAINT fk_issue_updated_by FOREIGN KEY (updated_by_id) REFERENCES user(user_id);

