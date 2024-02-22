package com.cinco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinco.DTO.PetDTO;
import com.cinco.entities.Pet;
import com.cinco.repository.PetRepository;

@Service
public class PetService {
	
	private final PetRepository petRepository;
	
	@Autowired
	public PetService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	public PetDTO salvar(PetDTO petDTO) {
		Pet pet = new Pet(petDTO.id(), petDTO.nome(), petDTO.documento(), petDTO.nascimento(), petDTO.cuidador());
		Pet salvarPet = petRepository.save(pet);
		return new PetDTO(salvarPet.getId(), salvarPet.getNome(), salvarPet.getDocumento(), salvarPet.getNascimento(), salvarPet.getCuidador());
	}
	
	public PetDTO atualizar(Long id, PetDTO petDTO) {
		Pet existePet = petRepository.findById(id).orElseThrow(() -> new RuntimeException("Pet não encontrado"));
		
		existePet.setNome(petDTO.nome());
		existePet.setDocumento(petDTO.documento());
		existePet.setNascimento(petDTO.nascimento());
		existePet.setCuidador(petDTO.cuidador());
		
		Pet updatePet = petRepository.save(existePet);
		return new PetDTO(updatePet.getId(), updatePet.getNome(), updatePet.getDocumento(), updatePet.getNascimento(), updatePet.getCuidador());
	}
	
	public boolean deletar(Long id) {
		Optional <Pet> existePet = petRepository.findById(id);
		if(existePet.isPresent()) {
			petRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public List<Pet> buscarTodos(){
		return petRepository.findAll();
	}
	
	public Pet buscarPorId(Long id) {
		Optional <Pet> pet = petRepository.findById(id);
		return pet.orElse(null);
	}
}
