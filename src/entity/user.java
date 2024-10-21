package entity;

public class user {
//    CREATE TABLE user (
//                      user_id INT PRIMARY KEY AUTO_INCREMENT,
//                      full_name VARCHAR(50) NOT NULL, -- Tên đầy đủ
//                      user_name VARCHAR(50) UNIQUE NOT NULL, -- Tên đăng nhập
//                      email VARCHAR(50) UNIQUE NOT NULL, -- Email
//                      password VARCHAR(255) NOT NULL, -- Mật khẩu đã được băm
//                      role_id INT, -- FK đến setting (vai trò người dùng)
//                      dept_id INT, -- FK đến setting (phòng ban)
//                      start_date DATE, -- Ngày bắt đầu làm việc
//                      status BIT DEFAULT NULL, -- Trạng thái người dùng: 1 = active, 0 = inactive, NULL = unverified
//                      note TEXT, -- Ghi chú
//                      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
//                      created_by_id INT, -- FK đến user (Người tạo)
//                      updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
//                      updated_by_id INT -- FK đến user (Người cập nhật)
//);
    private int user_id;
    private String full_name;
    private String user_name;
    private String email;
    private String password;
    private int role_id;
    private int dept_id;
    private String start_date;
    private int status;
    private String note;
    private String created_at;
    private int created_by_id;
    private String updated_at;
    private int updated_by_id;

    public user() {
    }

    public user(int user_id, String full_name, String user_name, String email, String password, int role_id, int dept_id, String start_date, int status, String note, String created_at, int created_by_id, String updated_at, int updated_by_id) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
        this.dept_id = dept_id;
        this.start_date = start_date;
        this.status = status;
        this.note = note;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {

        this.created_by_id = created_by_id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(int updated_by_id) {
        this.updated_by_id = updated_by_id;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +
                ", full_name='" + full_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role_id=" + role_id +
                ", dept_id=" + dept_id +
                ", start_date='" + start_date + '\'' +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", created_at='" + created_at + '\'' +
                ", created_by_id=" + created_by_id +
                ", updated_at='" + updated_at + '\'' +
                ", updated_by_id=" + updated_by_id +
                '}';
    }
}