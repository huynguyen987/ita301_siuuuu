package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Project {
    private int project_id;
    private String name;
    private String code;
    private Date start_date;
    private Date end_date;
    private int dept_id;
    private int status;
    private String description;
    private Timestamp created_at;
    private int created_by_id;
    private Timestamp updated_at;

    // Getters and Setters
    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
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

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public int getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(int created_by_id) {
        this.created_by_id = created_by_id;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    // Constructor
    public Project() {
    }

    public Project(int project_id, String name, String code, Date start_date, Date end_date, int dept_id, int status, String description, Timestamp created_at, int created_by_id, Timestamp updated_at) {
        this.project_id = project_id;
        this.name = name;
        this.code = code;
        this.start_date = start_date;
        this.end_date = end_date;
        this.dept_id = dept_id;
        this.status = status;
        this.description = description;
        this.created_at = created_at;
        this.created_by_id = created_by_id;
        this.updated_at = updated_at;
    }


    @Override
    public String toString() {
        return "project{" +
                "project_id=" + project_id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", dept_id=" + dept_id +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", created_at=" + created_at +
                ", created_by_id=" + created_by_id +
                ", updated_at=" + updated_at +
                '}';
    }
}
