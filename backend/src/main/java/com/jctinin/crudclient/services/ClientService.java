package com.jctinin.crudclient.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jctinin.crudclient.dto.ClientDTO;
import com.jctinin.crudclient.entities.Client;
import com.jctinin.crudclient.repositories.ClientRepository;


@Service	
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		
		Page<Client> list = clientRepository.findAll(pageRequest);
		
		Page<ClientDTO> result = list.map(element -> new ClientDTO(element));
				
		return result;
	}

}
