package com.bezkoder.spring.security.jwt.models;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email"),
			@UniqueConstraint(columnNames = "phone")
		})
public class User extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@Size(max = 50)
	private String name;
	private Date birthday;
	@Size(min = 10, max = 10)
	@Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
	@NotBlank
	private String phone;

	private Integer gender;
	@Size(max = 255)
	private String address;
	private  Integer status;

	@ManyToOne
	@JoinColumn(name = "ward_code")
	private Ward ward;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password, String phone) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.status = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName(){return name;}

	public Integer getGender(){return gender;}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public String getAddress() {
		return address;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void setCreatedAt(Date createdAt) {
		super.setCreatedAt(createdAt);
	}

	@Override
	public void setUpdatedAt(Date updatedAt) {
		super.setUpdatedAt(updatedAt);
	}

	@Override
	public Date getCreatedAt() {
		return super.getCreatedAt();
	}

	@Override
	public Date getUpdatedAt() {
		return super.getUpdatedAt();
	}
}
