package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repository.dto.InterestDto;
import com.ssafy.happyhouse.repository.mapper.InterestMapper;

@Service
public class InterestServiceImpl implements InterestService {
	@Autowired
	private InterestMapper interestMapper;

	@Override
	public Map<String, Object> getInterestInfo(int dongCode) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("dongcodeDto", interestMapper.selectOneInterest(dongCode));
		return result;
	}

	@Override
	public boolean registerInterest(InterestDto interestDto) throws Exception {
		if(interestDto.getEmail() == null || interestDto.getDongCode() == null) {
			throw new Exception();
		}
		return interestMapper.insertInterest(interestDto) == 1;
	}

	@Override
	public List<InterestDto> getInterestList(String email) throws Exception {
		return interestMapper.selectAllInterest(email);
	}

	@Override
	public boolean deleteInterest(int interestNo) throws Exception {
		return interestMapper.deleteOneInterest(interestNo) == 1;
	}


}
