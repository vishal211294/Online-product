package com.iiht.training.auction.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class CustomerDto {

	private Long id;

	@NotNull(message="Invalid Property.")
	@Size(min = 3,max = 100 ,message = "username must lie between 3 and 100 characters.")
	private String username;
	
	@NotNull(message="Invalid Property.")
	@Size(min = 3,max = 100 ,message = "password must lie between 3 and 100 characters.")
	private String password;
	
	@NotNull(message = "Invalid Property.")
	@Size(min = 3, max = 100, message = "Email must lie between 3 and 100 characters.")
	@Pattern(regexp = "^(.+)@(\\S+)$")
	private String email;
	
	@NotNull(message = "Invalid Property.")
	@Min(value = 1000000000L, message = "Invalid Property.")
	@Max(value = 9999999999L, message = "Invalid Property.")
	private Long phoneNumber;
	
	@NotNull(message = "Invalid Property.")
	@Size(min = 3, max = 100, message = "Address must lie between 3 and 100 characters.")
	private String address;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, id, password, phoneNumber, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDto other = (CustomerDto) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(phoneNumber, other.phoneNumber) && Objects.equals(username, other.username);
	}

	
}
