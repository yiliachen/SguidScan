package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.pojo.DirObj;
import com.example.pojo.FileList;

@Controller
public class EmployeeController {
	
	@RequestMapping(path="/", method=RequestMethod.GET)
	public String goHome(Model model){
		model.addAttribute("Scanlist", new FileList());
		model.addAttribute("Issuelist", new FileList());
		return "index";
	}
}