package com.bertolezo.aucom.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bertolezo.aucom.domain.Categoria;
import com.bertolezo.aucom.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Optional<Categoria> buscar(Integer id) {
        Optional<Categoria> obj = repo.findById(id);
		return obj;
	}

}
