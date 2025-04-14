package com.jceco.inventario_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jceco.inventario_api.services.impl.FornecedorServiceImpl;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorServiceImpl repository;
}
