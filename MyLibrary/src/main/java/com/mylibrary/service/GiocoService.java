package com.mylibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mylibrary.repository.GiocoRepository;

@Service
public class GiocoService {
	
	@Autowired
	private GiocoRepository giocoRepository;
}
