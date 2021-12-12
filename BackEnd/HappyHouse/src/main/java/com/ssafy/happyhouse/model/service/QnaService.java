package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.repository.dto.QnaDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

public interface QnaService {
	public boolean registerQna(QnaDto qnaDto) throws Exception;
	public void updateOriginNo(QnaDto qnaDto) throws Exception;
	public Map<String, Object> getQnaList(SearchParameterDto searchParameterDto) throws Exception;
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception;
	public QnaDto getQna(int qnaNo) throws Exception;
	public void updateViewCnt(int qnaNo) throws Exception;
	public boolean modifyQna(QnaDto qnaDto) throws Exception;
	public boolean deleteQna(int qnaNo) throws Exception;
}
