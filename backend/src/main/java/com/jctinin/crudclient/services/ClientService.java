package com.jctinin.crudclient.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jctinin.crudclient.dto.ClientDTO;
import com.jctinin.crudclient.entities.Client;
import com.jctinin.crudclient.repositories.ClientRepository;
import com.jctinin.crudclient.services.exceptions.DatabaseException;
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

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDto) {

		try {
			Client client = clientRepository.getReferenceById(id);
			this.copyDtoToEntity(clientDto, client);
			client = clientRepository.save(client);
			return new ClientDTO(client);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found" + id);
		}

	}
	
	public void delete(Long id) {
		try {
			clientRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new DatabaseException("Id not found " + id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(ClientDTO clientDto, Client entity) {
		entity.setName(clientDto.getName());
		entity.setCpf(clientDto.getCpf());
		entity.setIncome(clientDto.getIncome());
		entity.setBirthDate(clientDto.getBirthDate());
		entity.setChildren(clientDto.getChildren());
	}

}
