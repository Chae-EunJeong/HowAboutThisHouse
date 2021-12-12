package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.repository.dto.InterestDto;


public interface InterestService {
	public Map<String, Object> getInterestInfo(int dongCode) throws Exception;
	public boolean registerInterest(InterestDto interestDto) throws Exception;
	public List<InterestDto> getInterestList(String email) throws Exception;
	public boolean deleteInterest(int interestNo) throws Exception;

}
