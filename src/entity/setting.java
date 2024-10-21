package entity;

public class setting {
//    CREATE TABLE setting (
//                         setting_id INT PRIMARY KEY AUTO_INCREMENT,
//                         name VARCHAR(50) NOT NULL, -- Tên cấu hình
//                         value VARCHAR(50), -- Giá trị cấu hình
//                         type VARCHAR(50), -- Loại cấu hình (ví dụ: role, department)
//                         priority INT, -- Độ ưu tiên
//                         status BIT DEFAULT 1, -- Trạng thái cấu hình: 1 = active, 0 = inactive
//                         description TEXT, -- Mô tả cấu hình
//                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Thời gian tạo
//                         created_by_id INT, -- FK đến user (Người tạo)
//                         updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- Thời gian cập nhật
//                         updated_by_id INT -- FK đến user (Người cập nhật)
//);
    private int setting_id;
    private String name;
    private String value;
    private String type;
    private int priority;
    private int status;
    private String description;
    private String created_at;
    private int created_by_id;
    private String updated_at;
    private int updated_by_id;

    public setting() {
    }

    public setting(int setting_id, String name, String value, String type, int priority, int status, String description, String created_at, int created_by_id, String updated_at, int updated_by_id) {
        this.setting_id = setting_id;
        this.name = name;
        this.value = value;
        this.type = type;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
        this.updated_by_id = updated_by_id;
    }

    public int getSetting_id() {
        return setting_id;
    }

    public void setSetting_id(int setting_id) {
        this.setting_id = setting_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "setting{" + "setting_id=" + setting_id + ", name=" + name + ", value=" + value + ", type=" + type + ", priority=" + priority + ", status=" + status + ", description=" + description + ", created_at=" + created_at + ", created_by_id=" + created_by_id + ", updated_at=" + updated_at + ", updated_by_id=" + updated_by_id + '}';
}