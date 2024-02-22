package com.cinco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinco.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}