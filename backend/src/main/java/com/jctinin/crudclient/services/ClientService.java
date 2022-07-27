package com.jctinin.crudclient.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jctinin.crudclient.dto.ClientDTO;


@Service	
public class ClientService {

	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
