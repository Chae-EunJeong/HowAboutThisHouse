package com.ssafy.happyhouse.repository.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.repository.dto.NoticeDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;

@Mapper
public interface NoticeMapper {
	public int insertNotice(NoticeDto noticeDto) throws SQLException;
	public List<NoticeDto> selectAllNotice(SearchParameterDto searchParameterDto) throws SQLException;
	public int getTotalCount(SearchParameterDto searchParameterDto) throws SQLException;
	public NoticeDto selectOneNotice(int noticeNo) throws Exception;
	public void updateViewCnt(int boardNo) throws SQLException;
	public int updateNotice(NoticeDto noticeDto) throws Exception;
	public int deleteNotice(int noticeNo) throws Exception;
}
