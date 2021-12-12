package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.repository.dto.NoticeDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.happyhouse.repository.mapper.NoticeMapper;
import com.ssafy.util.PageNavigation;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public boolean registerNotice(NoticeDto noticeDto) throws Exception {
		if(noticeDto.getNoticeTitle() == null || noticeDto.getNoticeContent() == null) {
			throw new Exception();
		}
		return noticeMapper.insertNotice(noticeDto) == 1;
	}
	
	@Override
	public Map<String, Object> getNoticeList(SearchParameterDto searchParameterDto) throws Exception {
		int start = searchParameterDto.getPg() == 0 ? 0 : (searchParameterDto.getPg() - 1) * searchParameterDto.getSpp();
		searchParameterDto.setStart(start);
		Map<String, Object> result = new HashMap<>();
		result.put("list", noticeMapper.selectAllNotice(searchParameterDto));

		return result;
	}
	
	@Override
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(searchParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = noticeMapper.getTotalCount(searchParameterDto); //총글갯수  269
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
	public NoticeDto getNotice(int noticeNo) throws Exception {
		return noticeMapper.selectOneNotice(noticeNo);
	}
	
	@Override
	public void updateViewCnt(int noticeNo) throws Exception {
		noticeMapper.updateViewCnt(noticeNo);
	}

	@Override
	@Transactional
	public boolean modifyNotice(NoticeDto noticeDto) throws Exception {
		return noticeMapper.updateNotice(noticeDto) == 1;
	}
	
	@Override
	@Transactional
	public boolean deleteNotice(int noticeNo) throws Exception {
		return noticeMapper.deleteNotice(noticeNo) == 1;
	}
}
