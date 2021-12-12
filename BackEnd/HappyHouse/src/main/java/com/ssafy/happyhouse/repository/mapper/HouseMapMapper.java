package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.HouseInfoDto;
import com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto;

@Mapper
public interface HouseMapMapper {
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	List<HouseInfoDto> getApt(String aptName) throws SQLException;
}
