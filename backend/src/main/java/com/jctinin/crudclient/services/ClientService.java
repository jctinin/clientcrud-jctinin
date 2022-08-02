package com.jctinin.crudclient.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jctinin.crudclient.dto.ClientDTO;
import com.jctinin.crudclient.entities.Client;
import com.jctinin.crudclient.repositories.ClientRepository;
import com.jctinin.crudclient.services.exceptions.ResourceNotFoundException;

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

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {

		Optional<Client> obj = clientRepository.findById(id);

		Client client = obj.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado."));

		return new ClientDTO(client);
	}

	@Transactional(readOnly = true)
	public ClientDTO insert(ClientDTO clientDto) {
		Client entity = new Client();
		this.copyDtoToEntity(clientDto, entity);
		entity = clientRepository.save(entity);
		return new ClientDTO(entity);

	}

	private void copyDtoToEntity(ClientDTO clientDto, Client entity) {
		entity.setName(clientDto.getName());
		entity.setCpf(clientDto.getCpf());
		entity.setIncome(clientDto.getIncome());
		entity.setBirthDate(clientDto.getBirthDate());
		entity.setChildren(clientDto.getChildren());
	}

}
