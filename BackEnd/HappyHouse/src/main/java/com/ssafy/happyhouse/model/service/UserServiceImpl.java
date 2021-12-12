package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.repository.dto.UserDto;
import com.ssafy.happyhouse.repository.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean registerUser(UserDto userDto) throws Exception {
		if(userDto.getEmail() == null || userDto.getName() == null) {
			throw new Exception();
		}
		return userMapper.insertUser(userDto);
	}

	@Override
	public List<UserDto> getUserList() throws Exception {
		return userMapper.selectAllUser();
	}

	@Override
	public UserDto getUser(String email) throws Exception {
		return userMapper.selectOneUser(email);
	}
	
	@Override
	public UserDto loginUser(UserDto userDto) throws Exception {
		if(userDto.getEmail() == null || userDto.getPassword() == null) {
			throw new Exception();
		}
		return userMapper.selectLogin(userDto);
	}

	@Override
	@Transactional
	public boolean modifyUser(UserDto userDto) throws Exception {
		return userMapper.updateUser(userDto);
	}

	@Override
	@Transactional
	public boolean withdrawUser(String email) throws Exception {
		return userMapper.deleteUser(email) == 1;
	}

}
