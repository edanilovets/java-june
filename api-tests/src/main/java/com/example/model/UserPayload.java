package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@Accessors(chain = true)
@Data
@Generated("com.robohorse.robopojogenerator")
public class UserPayload{

	@JsonProperty("username")
	private String username;

	private String firstname;
	private String lastname;

	@JsonProperty("password")
	private String password;

	@JsonProperty("email")
	private String email;

}