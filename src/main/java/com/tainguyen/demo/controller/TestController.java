package com.tainguyen.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tainguyen.demo.model.TestModel;

@Controller
public class TestController {

	@GetMapping("/test")
	public String index() {
		
		return "test";

	}
	@PostMapping("/test")
	public String index2(@ModelAttribute TestModel testModel, Model model) {
		model.addAttribute("modelTEST", testModel);
		return "test2";
	}
}
