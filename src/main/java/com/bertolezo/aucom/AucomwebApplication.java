package com.bertolezo.aucom;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bertolezo.aucom.domain.Categoria;
import com.bertolezo.aucom.repositories.CategoriaRepository;

@SpringBootApplication
public class AucomwebApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AucomwebApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		categoriaRepository.save(cat1);
		categoriaRepository.save(cat2);
		
	}
}
