package com.ssafy.happyhouse.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.repository.dto.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
@Api("user 컨트롤러  API V1")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(이메일, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUserDto = userService.loginUser(userDto);
			if (loginUserDto != null) {
				String token = jwtService.create("email", loginUserDto.getEmail(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{email}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("email") @ApiParam(value = "인증할 회원의 이메일", required = true) String email,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.getUser(email);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "사용자 등록(회원가입)", notes = "새로운 사용자 정보를 입력한다. 그리고 DB 입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody @ApiParam(value = "사용자 정보.", required = true) UserDto userDto) throws Exception {
		logger.info("registerUser - 호출");
		System.out.println(userDto);
		if (userService.registerUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "일반 사용자 목록", notes = "모든 사용자의 정보를 반환한다.(관리자용)", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserDto>> getUserList() throws Exception {
		logger.info("getUserList - 호출");
		return new ResponseEntity<List<UserDto>>(userService.getUserList(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 정보 한 개 보기", notes = "사용자 번호에 해당하는 사용자의 정보를 반환한다.", response = UserDto.class)
	@GetMapping("/{email}")
	public ResponseEntity<UserDto> getUser(@PathVariable("email") @ApiParam(value = "얻어올 사용자의 번호.", required = true) String email) throws Exception {
		logger.info("getUser - 호출 : " + email);
		return new ResponseEntity<UserDto>(userService.getUser(email), HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 정보 수정", notes = "사용자의 정보를 수정한다. 그리고 DB 수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyUser(@RequestBody @ApiParam(value = "수정할 사용자 정보", required = true) UserDto userDto) throws Exception {
		logger.info("modifyUser - 호출");
		if (userService.modifyUser(userDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "사용자 삭제(탈퇴)", notes = "사용자 번호에 해당하는 사용자를 삭제한다. 그리고 DB 삭제 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{email}")
	public ResponseEntity<String> withdrawUser(@PathVariable("email") @ApiParam(value = "삭제할 사용자의 번호.", required = true) String email) throws Exception {
		logger.info("withdrawUser - 호출");
		if (userService.withdrawUser(email)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}