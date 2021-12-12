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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.InterestService;
import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.InterestDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/interest")
@Api("관심지역 Contorller API V1")
public class InterestController {
	private static final Logger logger = LoggerFactory.getLogger(InterestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private InterestService interestService;

	@ApiOperation(value = "동코드에 해당하는 시/구군/동 이름 가져오기", notes = "동코드에 해당하는  시/구군/동 이름을 반환한다.", response = InterestDto.class)
	@GetMapping("/info/{dongCode}")
	public ResponseEntity<Map<String, Object>> getInterestInfo(@PathVariable("dongCode") @ApiParam(value = "얻어올 관심지역의 동코드.", required = true) int dongCode) throws Exception {
		logger.info("getInterestInfo - 호출 : " + dongCode);
		return new ResponseEntity<Map<String, Object>>(interestService.getInterestInfo(dongCode), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심지역 등록", notes = "새로운 관심지역 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerInterest(@RequestBody @ApiParam(value = "관심지역 정보.", required = true) InterestDto interestDto) throws Exception {
		logger.info("registerInterest - 호출");
		if (interestService.registerInterest(interestDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심지역 목록", notes = "이메일에 해당하는 회원의 모든 관심지역의 정보를 반환한다.", response = List.class)
	@GetMapping("/{email}")
	public ResponseEntity<List<InterestDto>> getInterestList(@PathVariable("email") @ApiParam(value = "관심지역을 얻기위한 회원 이메일", required = true) String email) throws Exception {
		logger.info("getInterestList - 호출");
		return new ResponseEntity<List<InterestDto>>(interestService.getInterestList(email), HttpStatus.OK);
	}
	
	@ApiOperation(value = "관심지역 삭제", notes = "파라미터에 해당하는 관심지역의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{interestNo}")
	public ResponseEntity<String> deleteInterest(@PathVariable("interestNo") @ApiParam(value = "삭제할 관심지역 정보", required = true) int interestNo) throws Exception {
		logger.info("deleteInterest - 호출");
		if (interestService.deleteInterest(interestNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
