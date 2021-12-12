package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.BoardService;
import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.CommentDto;
import com.ssafy.happyhouse.repository.dto.SearchParameterDto;
import com.ssafy.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/board")
@Api("게시판 Controller API V1")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

	@ApiOperation(value = "게시판 글 작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> registerBoard(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("registerBoard - 호출");
		if (boardService.registerBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글 목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<Map<String, Object>> getBoardList(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) SearchParameterDto searchParameterDto) throws Exception {
		logger.info("getBoardList - 호출");
		PageNavigation pageNavigation = boardService.makePageNavigation(searchParameterDto);
		Map<String, Object> result = boardService.getBoardList(searchParameterDto);
		result.put("pageNavigation", pageNavigation);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 한 개 보기", notes = "글 번호에 해당하는 게시글의 정보를 반환하고 조회수가 1 증가한다.", response = BoardDto.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Map<String, Object>> getBoard(@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("getBoard - 호출 : " + boardNo);
		boardService.updateViewCnt(boardNo);
		return new ResponseEntity<Map<String, Object>>(boardService.getBoard(boardNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 한 개에 대한 정보 가져오기", notes = "글 번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/info/{boardNo}")
	public ResponseEntity<Map<String, Object>> getBoardInfo(@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("getBoardInfo - 호출 : " + boardNo);
		return new ResponseEntity<Map<String, Object>>(boardService.getBoard(boardNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 수정", notes = "게시글 정보를 수정한다. 그리고 DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyBoard(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		logger.info("modifyBoard - 호출");
		if (boardService.modifyBoard(boardDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 삭제", notes = "글 번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable("boardNo") @ApiParam(value = "삭제할 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("deleteBoard - 호출");
		if (boardService.deleteBoard(boardNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 댓글 작성", notes = "새로운 댓글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/comment")
	public ResponseEntity<String> registerComment(@RequestBody @ApiParam(value = "댓글 정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("registerComment - 호출");
		if (boardService.registerComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 댓글 보기", notes = "글 번호에 해당하는 댓글의 정보를 반환하고 증가한다.", response = BoardDto.class)
	@GetMapping("/comment/{boardNo}")
	public ResponseEntity<Map<String, Object>> getComment(@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo) throws Exception {
		return new ResponseEntity<Map<String, Object>>(boardService.getComment(boardNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글 수정", notes = "게시글 정보를 수정한다. 그리고 DB 수정 성공 여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/comment")
	public ResponseEntity<String> modifyComment(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) CommentDto commentDto) throws Exception {
		logger.info("modifyBoard - 호출");
		if (boardService.modifyComment(commentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 댓글 삭제", notes = "글 번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/comment/{commentNo}")
	public ResponseEntity<String> deleteComment(@PathVariable("commentNo") @ApiParam(value = "삭제할 댓글의 번호.", required = true) int commentNo) throws Exception {
		logger.info("deleteComment - 호출");
		if (boardService.deleteComment(commentNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}