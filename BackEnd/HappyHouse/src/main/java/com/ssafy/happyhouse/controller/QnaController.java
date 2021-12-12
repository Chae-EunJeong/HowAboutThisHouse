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

import com.ssafy.happyhouse.model.service.QnaService;
import com.ssafy.happyhouse.repository.dto.QnaDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/qna")
@Api("QnA 컨트롤러  API V1")
public class QnaController {

	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnaService qnaService;
	
	@ApiOperation(value = "QnA 글작성", notes = "새로운 QnA를 입력한다. 그리고 DB 입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerQna(@RequestBody @ApiParam(value = "QnA 정보.", required = true) QnaDto qnaDto) throws Exception {
		logger.info("registerQna - 호출");
		if (qnaService.registerQna(qnaDto)) {
			qnaService.updateOriginNo(qnaDto);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value = "QnA 글작성", notes = "새로운 QnA를 입력한다. 그리고 DB 입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/reply")
	public ResponseEntity<String> registerQnaReply(@RequestBody @ApiParam(value = "QnA 정보.", required = true) QnaDto qnaDto) throws Exception {
		logger.info("registerQna - 호출");
		logger.info("dd");
		logger.info(String.valueOf(qnaDto.getOriginNo()));
		if (qnaService.registerQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "QnA 목록", notes = "모든 QnA의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getQnaList(@ApiParam(value = "QnA을 얻기위한 부가정보.", required = true) SearchParameterDto searchParameterDto) throws Exception {
		logger.info("getQnaList - 호출");
		PageNavigation pageNavigation = qnaService.makePageNavigation(searchParameterDto);
		Map<String, Object> result = qnaService.getQnaList(searchParameterDto);
		result.put("pageNavigation", pageNavigation);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
		
	@ApiOperation(value = "QnA 보기", notes = "qna번호에 해당하는 QnA의 정보를 반환한다.", response = QnaDto.class)
	@GetMapping("/{qnaNo}")
	public ResponseEntity<QnaDto> getQna(@PathVariable("qnaNo") @ApiParam(value = "얻어올 QnA의 qna번호.", required = true) int qnaNo) throws Exception {
		logger.info("getQna - 호출 : " + qnaNo);
		qnaService.updateViewCnt(qnaNo);
		return new ResponseEntity<QnaDto>(qnaService.getQna(qnaNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 보기", notes = "qna번호에 해당하는 QnA의 정보를 반환한다.", response = QnaDto.class)
	@GetMapping("/info/{qnaNo}")
	public ResponseEntity<QnaDto> getQnaInfo(@PathVariable("qnaNo") @ApiParam(value = "얻어올 QnA의 qna번호.", required = true) int qnaNo) throws Exception {
		logger.info("getQna - 호출 : " + qnaNo);
		qnaService.updateViewCnt(qnaNo);
		return new ResponseEntity<QnaDto>(qnaService.getQna(qnaNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 수정", notes = "기존 QnA 정보에서 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyQna(@RequestBody @ApiParam(value = "수정할 QnA 정보.", required = true) QnaDto qnaDto) throws Exception {
		logger.info("modifyQna - 호출");
		if (qnaService.modifyQna(qnaDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "QnA 삭제", notes = "qna번호에 해당하는 QnA의 정보를 삭제한다. 그리고 DB삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{qnaNo}")
	public ResponseEntity<String> deleteQna(@PathVariable("qnaNo") @ApiParam(value = "삭제할 QnA의 qna번호.", required = true) int qnaNo) throws Exception {
		logger.info("deleteQna - 호출");
		if (qnaService.deleteQna(qnaNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
