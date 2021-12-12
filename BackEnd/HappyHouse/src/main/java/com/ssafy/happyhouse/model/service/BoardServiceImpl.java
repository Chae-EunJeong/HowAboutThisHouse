package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.CommentDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.happyhouse.repository.mapper.BoardMapper;
import com.ssafy.util.PageNavigation;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	public boolean registerBoard(BoardDto boardDto) throws Exception {
		if(boardDto.getBoardTitle() == null || boardDto.getBoardContent() == null) {
			throw new Exception();
		}
		return boardMapper.insertBoard(boardDto) == 1;
	}
	
	@Override
	public boolean registerComment(CommentDto commentDto) throws Exception {
		if(commentDto.getContent() == null) {
			throw new Exception();
		}
		return boardMapper.insertComment(commentDto) == 1;
	}

	@Override
	public Map<String, Object> getBoardList(SearchParameterDto searchParameterDto) throws Exception {
		int start = searchParameterDto.getPg() == 0 ? 0 : (searchParameterDto.getPg() - 1) * searchParameterDto.getSpp();
		searchParameterDto.setStart(start);
		Map<String, Object> result = new HashMap<>();
		result.put("list", boardMapper.selectAllBoard(searchParameterDto));

		return result;
	}

	@Override
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(searchParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = boardMapper.getTotalCount(searchParameterDto); //총글갯수  269
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
	public Map<String, Object> getBoard(int boardNo) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("boardDto", boardMapper.selectOneBoard(boardNo));
		result.put("count", boardMapper.getCommentCount(boardNo));
		return result; 
	}
	
	@Override
	public void updateViewCnt(int boardNo) throws Exception {
		boardMapper.updateViewCnt(boardNo);
	}

	@Override
	@Transactional
	public boolean modifyBoard(BoardDto boardDto) throws Exception {
		return boardMapper.updateBoard(boardDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int boardNo) throws Exception {
		boardMapper.deleteComment(boardNo);
		return boardMapper.deleteBoard(boardNo) == 1;
	}

	@Override
	public Map<String, Object> getComment(int boardNo) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("list", boardMapper.selectOneComment(boardNo));
		result.put("count", boardMapper.getCommentCount(boardNo));
		return result; 
	}

	@Override
	public List<CommentDto> getCommentList(SearchParameterDto searchParameterDto) throws Exception {
		int start = searchParameterDto.getPg() == 0 ? 0 : (searchParameterDto.getPg() - 1) * searchParameterDto.getSpp();
		searchParameterDto.setStart(start);
		return boardMapper.selectAllComment(searchParameterDto);
	}

	@Override
	@Transactional
	public boolean modifyComment(CommentDto commentDto) throws Exception {
		return boardMapper.updateComment(commentDto) == 1;
	}
	
	@Override
	@Transactional	
	public boolean deleteComment(int commentNo) throws Exception {
		return boardMapper.deleteOneComment(commentNo) == 1;
	}

}