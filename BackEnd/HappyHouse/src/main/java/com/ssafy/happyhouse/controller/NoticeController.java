package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.repository.dto.NoticeDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/notice")
@Api("공지사항 Controller RestAPI V1")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;
	
	@ApiOperation(value = "공지사항 글 작성", notes = "새로운 공지사항 정보를 입력한다.(관리자용) DB입력 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerNotice(@RequestBody @ApiParam(value = "공지사항 정보", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("registerNotice - 호출");
		if (noticeService.registerNotice(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "공지사항 글 목록", notes = "모든 공지사항의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getNoticeList(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) SearchParameterDto searchParameterDto) throws Exception {
		logger.info("getNoticeList - 호출");
		PageNavigation pageNavigation = noticeService.makePageNavigation(searchParameterDto);
		Map<String, Object> result = noticeService.getNoticeList(searchParameterDto);
		result.put("pageNavigation", pageNavigation);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 글 한 개 보기", notes = "공지사항 번호에 해당하는 공지사항 정보를 반환하고 조회수가 1 증가한다.", response = NoticeDto.class)
	@GetMapping("/{noticeNo}")
	public ResponseEntity<NoticeDto> getNotice(@PathVariable("noticeNo") @ApiParam(value = "얻어올 공지사항의 번호.", required = true) int noticeNo) throws Exception {
		logger.info("getNotice - 호출 : " + noticeNo);
		noticeService.updateViewCnt(noticeNo);
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeNo), HttpStatus.OK);
	}
	@ApiOperation(value = "공지사항 글 한 개에 대한 정보 가져오기", notes = "공지사항 번호에 해당하는 공지사항 정보를 반환한다.", response = NoticeDto.class)
	@GetMapping("/info/{noticeNo}")
	public ResponseEntity<NoticeDto> getNoticeInfo(@PathVariable("noticeNo") @ApiParam(value = "얻어올 공지사항의 번호.", required = true) int noticeNo) throws Exception {
		logger.info("getNoticeInfo - 호출 : " + noticeNo);
		return new ResponseEntity<NoticeDto>(noticeService.getNotice(noticeNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 정보 수정", notes = "공지사항의 정보를 수정한다. 그리고 DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyNotice(@RequestBody @ApiParam(value = "수정할 공지사항 정보", required = true) NoticeDto noticeDto) throws Exception {
		logger.info("modifyNotice - 호출");
		if (noticeService.modifyNotice(noticeDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "공지사항 삭제", notes = "공지사항 번호에 해당하는 공지사항를 삭제한다. 그리고 DB 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable("noticeNo") @ApiParam(value = "삭제할 공지사항의 번호.", required = true) int noticeNo) throws Exception {
		logger.info("deleteNotice - 호출");
		if (noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
