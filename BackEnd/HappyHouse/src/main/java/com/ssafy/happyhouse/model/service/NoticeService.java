package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.repository.dto.NoticeDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {
	public boolean registerNotice(NoticeDto noticeDto) throws Exception;
	public Map<String, Object> getNoticeList(SearchParameterDto searchParameterDto) throws Exception;
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception;
	public NoticeDto getNotice(int noticeNo) throws Exception;
	public void updateViewCnt(int noticeNo) throws Exception;
	public boolean modifyNotice(NoticeDto noticeDto) throws Exception;
	public boolean deleteNotice(int noticeNo) throws Exception;
}
