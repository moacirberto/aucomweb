package com.bertolezo.aucom.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bertolezo.aucom.domain.Pedido;
import com.bertolezo.aucom.repositories.PedidoRepository;
import com.bertolezo.aucom.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        
        return	obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + ", Tipo: " + Pedido.class.getName())); 
	}

}
