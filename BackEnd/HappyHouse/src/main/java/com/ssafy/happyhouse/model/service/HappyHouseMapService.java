package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.repository.dto.HouseInfoDto;
import com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto;

public interface HappyHouseMapService {
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	List<HouseInfoDto> getApt(String aptName) throws Exception;
}
