package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

//@Accessors(chain = true)
//@Getter
//@Setter
@Generated("com.robohorse.robopojogenerator")
public class UserPayload{

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserPayload{" +
				"password='" + password + '\'' +
				", email='" + email + '\'' +
				", username='" + username + '\'' +
				'}';
	}
}