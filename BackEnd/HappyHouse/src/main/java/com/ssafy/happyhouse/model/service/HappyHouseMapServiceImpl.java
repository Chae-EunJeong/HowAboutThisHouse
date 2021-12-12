package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repository.dto.HouseInfoDto;
import com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.repository.mapper.HouseMapMapper;

@Service
public class HappyHouseMapServiceImpl implements HappyHouseMapService {

	@Autowired
	private HouseMapMapper houseMapMapper;
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getApt(String aptName) throws Exception {
		return houseMapMapper.getApt(aptName);
	}

}
