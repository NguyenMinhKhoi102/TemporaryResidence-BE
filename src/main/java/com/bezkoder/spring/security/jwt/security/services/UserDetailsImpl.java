package com.bezkoder.spring.security.jwt.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.bezkoder.spring.security.jwt.models.Ward;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bezkoder.spring.security.jwt.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.crypto.Data;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String email;
	private String phone;
	private String name;
	private Integer gender;
	private Date birthday;
	private String address;
	private Integer status;
	private Date createdAt;
	private Date updatedAt;
	private Ward ward;
	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Ward ward, Date createdAt, Date updatedAt, String name, Integer gender, Date birthday, String address, Integer status, String phone, Long id, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.ward = ward;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.status = status;
		this.phone = phone;
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getWard(),
				user.getCreatedAt(),
				user.getUpdatedAt(),
				user.getName(),
				user.getGender(),
				user.getBirthday(),
				user.getAddress(),
				user.getStatus(),
				user.getPhone(),
				user.getId(), 
				user.getUsername(), 
				user.getEmail(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone(){return phone; }

	public String getAddress() {
		return address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public Integer getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public Ward getWard() {
		return ward;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
