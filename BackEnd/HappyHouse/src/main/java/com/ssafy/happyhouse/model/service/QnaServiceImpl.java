package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.repository.dto.QnaDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.happyhouse.repository.mapper.QnaMapper;
import com.ssafy.util.PageNavigation;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;

	@Override
	public boolean registerQna(QnaDto qnaDto) throws Exception {
		if(qnaDto.getQnaTitle() == null || qnaDto.getQnaContent() == null) {
			throw new Exception();
		}
		return qnaMapper.insertQna(qnaDto) == 1;
	}
	
	@Override
	public void updateOriginNo(QnaDto qnaDto) throws Exception {
		if(qnaDto.getQnaTitle() == null || qnaDto.getQnaContent() == null) {
			throw new Exception();
		}
		qnaMapper.updateOriginNo(qnaDto);
	}

	@Override
	public Map<String, Object> getQnaList(SearchParameterDto searchParameterDto) throws Exception {
		int start = searchParameterDto.getPg() == 0 ? 0 : (searchParameterDto.getPg() - 1) * searchParameterDto.getSpp();
		searchParameterDto.setStart(start);
		Map<String, Object> result = new HashMap<>();
		result.put("list", qnaMapper.selectAllQna(searchParameterDto));

		return result;
	}
	
	@Override
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(searchParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = qnaMapper.getTotalCount(searchParameterDto); //총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / searchParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = searchParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < searchParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public QnaDto getQna(int qnaNo) throws Exception {
		return qnaMapper.selectOneQna(qnaNo);
	}

	@Override
	public void updateViewCnt(int qnaNo) throws Exception {
		qnaMapper.updateViewCnt(qnaNo);
	}

	@Override
	public boolean modifyQna(QnaDto qnaDto) throws Exception {
		return qnaMapper.updateQna(qnaDto) == 1;
	}

	@Override
	public boolean deleteQna(int qnaNo) throws Exception {
		return qnaMapper.deleteQna(qnaNo) == 1;
	}
	
}
