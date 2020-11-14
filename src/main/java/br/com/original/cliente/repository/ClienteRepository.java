package br.com.original.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.original.cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
}
