package com.ssafy.happyhouse.repository.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto : 사용자 정보", description = "사용자의 상세 정보를 나타낸다.")
public class UserDto {
	@ApiModelProperty(value = "사용자 이메일(아이디)")
	private String email;
	@ApiModelProperty(value = "사용자 비밀번호")
	private String password;
	@ApiModelProperty(value = "사용자 이름")
	private String name;
	@ApiModelProperty(value = "사용자 주소")
	private String address;
	@ApiModelProperty(value = "가입일")
	private String joinDate;
	@ApiModelProperty(value = "권한")
	private String auth;

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}
}
