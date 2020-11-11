package br.com.original.cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.original.cliente.modelo.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
}
