package com.wxy.excel.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 22:54 2020/2/12
 */
@Entity
@Table(name = "excel")
public class Excel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(length=36)
    private String id;
    @Column(length=45,nullable=false)
    private String username;
    @Column(length=100,nullable=false,unique=true)
    private String email;
    @Column(length=45,nullable=false)
    private String password;
    @Column(length=45)
    private String role;

    public Excel() {
    }

    public Excel(String id, String username, String email, String password, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
