package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.service.HappyHouseMapService;
import com.ssafy.happyhouse.repository.dto.HouseInfoDto;
import com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/map")
public class HappyHouseMapController {
	@Autowired
	private HappyHouseMapService happyHouseMapService;
	
	@ResponseBody
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getSido(), HttpStatus.OK);
	}
	
	@ResponseBody
	@ApiOperation(value = "구군 정보", notes = "해당 시도의 구군을 반환한다.", response = List.class)
	@GetMapping("gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SidoGugunCodeDto>>(happyHouseMapService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("dong")
	@ApiOperation(value = "동 정보", notes = "해당 구군의 동을 반환한다.", response = List.class)
	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("apt")
	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getAptInDong(dong), HttpStatus.OK);
	}
	
	@ResponseBody
	@GetMapping("aptlist")
	public ResponseEntity<List<HouseInfoDto>> aptlist(@RequestParam("aptName") String aptName) throws Exception {
		return new ResponseEntity<List<HouseInfoDto>>(happyHouseMapService.getApt(aptName), HttpStatus.OK);
	}
	
	@RequestMapping("search")
	public String list(@RequestParam("dong") String dong, @RequestParam("check") String check,  Model model) throws Exception {
		model.addAttribute("dong", dong);
		model.addAttribute("check", check);
		
		return "houseDeal/list";
	}
	
	@RequestMapping("aptSearch")
	public String aptSearch(@RequestParam("inputAPT") String inputAPT, @RequestParam("check") String check , Model model) throws Exception {
		model.addAttribute("inputAPT", inputAPT);
		model.addAttribute("check", check);
		return "houseDeal/list";
	}
	
}
