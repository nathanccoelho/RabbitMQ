package com.microservico.estoque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservico.estoque.constantes.RabbitmqConstantes;
import com.microservico.estoque.dto.EstoqueDto;
import com.microservico.estoque.service.RabbitmqService;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

	@Autowired
	private RabbitmqService rabbitService;
	
	
	@PutMapping
	private ResponseEntity<Object> alteraEstoque(@RequestBody EstoqueDto estoqueDto){
		this.rabbitService.enviaMensagem(RabbitmqConstantes.FILA_ESTOQUE, estoqueDto);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
