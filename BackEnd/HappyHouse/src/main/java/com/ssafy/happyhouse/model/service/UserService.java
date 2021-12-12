package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.repository.dto.UserDto;

public interface UserService {
	public boolean registerUser(UserDto userDto) throws Exception;
	public List<UserDto> getUserList() throws Exception;
	public UserDto getUser(String email) throws Exception;
	public UserDto loginUser(UserDto userDto) throws Exception;
	public boolean modifyUser(UserDto userDto) throws Exception;
	public boolean withdrawUser(String email) throws Exception;
}
