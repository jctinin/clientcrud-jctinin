package com.jctinin.crudclient.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jctinin.crudclient.entities.Client;
import com.jctinin.crudclient.repositories.ClientRepository;


@Service	
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	public List<Client> findAll() {
		
		return clientRepository.findAll();
	}

}
