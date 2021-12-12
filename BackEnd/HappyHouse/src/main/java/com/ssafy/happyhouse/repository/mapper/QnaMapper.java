package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.QnaDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;

@Mapper
public interface QnaMapper {
	public int insertQna(QnaDto QnaDto) throws SQLException;
	public void updateOriginNo(QnaDto QnaDto) throws SQLException;
	public List<QnaDto> selectAllQna(SearchParameterDto searchParameterDto) throws SQLException;
	public int getTotalCount(SearchParameterDto searchParameterDto) throws SQLException;
	public QnaDto selectOneQna(int qnaNo) throws SQLException;
	public void updateViewCnt(int qnaNo) throws SQLException;
	public int updateQna(QnaDto QnaDto) throws SQLException;
	public int deleteQna(int qnaNo) throws SQLException;
}
