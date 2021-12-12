package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.UserDto;

@Mapper
public interface UserMapper {
	public boolean insertUser(UserDto user) throws SQLException;
	public List<UserDto> selectAllUser() throws SQLException;
	public UserDto selectOneUser(String email) throws SQLException;
	public UserDto selectLogin(UserDto user) throws SQLException;
	public boolean updateUser(UserDto user) throws SQLException;
	public int deleteUser(String email) throws SQLException;
//	User findPwdUser(String email, String name) throws Exception;
//	int checkEmail(String email) throws Exception;
}
