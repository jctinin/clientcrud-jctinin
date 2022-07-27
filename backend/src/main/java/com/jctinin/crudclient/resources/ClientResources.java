package com.jctinin.crudclient.resources;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jctinin.crudclient.entities.Client;

@RestController
@RequestMapping(value = "/clients")
public class ClientResources {
	
//	@Autowired
//	private ClientService clientService;

	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		
		//PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), direction);
		
		List<Client> list = new ArrayList<>();
		Instant instant = null;
		list.add(new Client(1L, "Júlio César", "32009063830", 15.5, instant, 2)); // fazendo teste manual utilizando Client
		
		
		return ResponseEntity.ok().body(list);
	}
}
