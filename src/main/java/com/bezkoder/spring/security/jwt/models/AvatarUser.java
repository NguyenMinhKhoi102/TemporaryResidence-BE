package com.bezkoder.spring.security.jwt.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "avatars_of_user")
public class AvatarUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 40)
    private String name;
    private String path;

    @Size(max = 5)
    private String type;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Integer isActive;

    private Integer isDelete;

    public AvatarUser(){

    }

    public AvatarUser(String name, String path, String type){
        this.name = new String(name);
        this.path = new String(path);
        this.type = new String(type);
        this.isActive = 1;
        this.isDelete = 0;
    }

    public Long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    public String getPath(){
        return this.path;
    }

    public String getType(){
        return this.type;
    }

    public Integer getIsActive() {
        return this.isActive;
    }

    public User getUser() {
        return user;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setType(String type) {
        this.type = type;
    }
}
