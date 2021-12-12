package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.CommentDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {
	public boolean registerBoard(BoardDto boardDto) throws Exception;
	public boolean registerComment(CommentDto commentDto) throws Exception;
	public Map<String, Object> getBoardList(SearchParameterDto searchParameterDto) throws Exception;
	public PageNavigation makePageNavigation(SearchParameterDto searchParameterDto) throws Exception;
	public Map<String, Object> getBoard(int boardNo) throws Exception;
	public void updateViewCnt(int boardNo) throws Exception;
	public boolean modifyBoard(BoardDto boardDto) throws Exception;
	public boolean deleteBoard(int boardNo) throws Exception;
	public Map<String, Object> getComment(int boardNo) throws Exception;
	public List<CommentDto> getCommentList(SearchParameterDto searchParameterDto) throws Exception;
	public boolean modifyComment(CommentDto commentDto) throws Exception;
	public boolean deleteComment(int commentNo) throws Exception;
}
