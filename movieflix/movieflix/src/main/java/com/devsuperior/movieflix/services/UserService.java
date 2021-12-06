package com.devsuperior.movieflix.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.dtos.UserInsertDTO;
import com.devsuperior.movieflix.dtos.UserUpdateDTO;

public interface UserService {

	UserDTO insert(UserInsertDTO dto);
	
	void delete(Long id);
	
	UserDTO update(Long id, UserUpdateDTO dto);
	
	Page<UserDTO> findAllPaged(Pageable pageable);
	
	UserDTO findById(Long id);
}
