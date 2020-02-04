package com.cloudfish.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class IndexController {

	@ApiOperation(value="测试swagger",notes="test")
	@GetMapping("/index")
	public String index(){
		return "index";
	}
}
