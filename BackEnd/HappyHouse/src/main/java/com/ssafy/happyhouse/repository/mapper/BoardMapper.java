package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.CommentDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;

@Mapper
public interface BoardMapper {
	public int insertBoard(BoardDto boardDto) throws SQLException;
	public int insertComment(CommentDto commentDto) throws SQLException;
	public List<BoardDto> selectAllBoard(SearchParameterDto searchParameterDto) throws SQLException;
	public int getTotalCount(SearchParameterDto searchParameterDto) throws SQLException;
	public BoardDto selectOneBoard(int boardNo) throws SQLException;
	public void updateViewCnt(int boardNo) throws SQLException;
	public int updateBoard(BoardDto boardDto) throws SQLException;
	public int deleteComment(int boardNo) throws SQLException;
	public int deleteBoard(int boardNo) throws SQLException;
	public List<CommentDto> selectOneComment(int boardNo) throws SQLException;
	public int getCommentCount(int boardNo) throws SQLException;
	public List<CommentDto> selectAllComment(SearchParameterDto searchParameterDto) throws SQLException;
	public int updateComment(CommentDto commentDto) throws SQLException;
	public int deleteOneComment(int commentNo) throws SQLException;
}