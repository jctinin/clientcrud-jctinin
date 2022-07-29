package com.jctinin.crudclient.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jctinin.crudclient.entities.Client;
import com.jctinin.crudclient.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {
	
	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<Page<Client>> findAll(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage",defaultValue = "5") Integer linesPerPage,
			@RequestParam(value = "name", defaultValue = "orderBy") String name
			//@RequestParam(value = "direction", defaultValue = "ASC") String direction
			){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage);
		
		Page<Client> list = clientService.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
}
