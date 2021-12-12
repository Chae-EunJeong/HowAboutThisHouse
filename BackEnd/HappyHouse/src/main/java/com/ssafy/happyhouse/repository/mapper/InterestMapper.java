package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.DongcodeDto;
import com.ssafy.happyhouse.repository.dto.InterestDto;

@Mapper
public interface InterestMapper {
	public DongcodeDto selectOneInterest(int dongCode) throws SQLException;
	public int insertInterest(InterestDto interestDto) throws SQLException;
	public List<InterestDto> selectAllInterest(String email) throws SQLException;
	public int deleteOneInterest(int interestNo) throws SQLException;

}
