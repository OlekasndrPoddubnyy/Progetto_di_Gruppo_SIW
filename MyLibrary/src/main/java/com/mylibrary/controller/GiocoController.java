package com.mylibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mylibrary.service.GiocoService;

@Controller
public class GiocoController {
	
	@Autowired
	private GiocoService giocoService;
}
