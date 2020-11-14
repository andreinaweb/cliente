package br.com.original.cliente;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.original.cliente.exception.ExpectGeneralException;
import br.com.original.cliente.model.Cliente;
import br.com.original.cliente.repository.ClienteRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ClienteRepositoryTeste {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test	
	public void createShouldPersistData() {
		
		Cliente cliente = new Cliente("Jesus");
		this.clienteRepository.save(cliente);
		
		assertThat(cliente.getId()).isNotNull();
		assertThat(cliente.getNome()).isEqualTo("Jesus");		
		
	}
	
	@Test
	public void deleteShouldPersistData() {
		
		Cliente cliente = new Cliente("Jesus");
		this.clienteRepository.save(cliente);
		
		clienteRepository.delete(cliente);
		assertThat(clienteRepository.findById(cliente.getId())).isEmpty();		
		
	}
	
	@Test
	public void updateShouldPersistData() {
		
		Cliente cliente = new Cliente("Jesus");
		this.clienteRepository.save(cliente);
		
	    cliente = new Cliente("Cristo");
		this.clienteRepository.save(cliente);
		
		cliente = clienteRepository.findById(cliente.getId()).orElse(null);
		assertThat(cliente.getNome()).isEqualTo("Cristo");
				
	}
	
	@Test
	public void notEmptyName() {
		
		 final ExpectGeneralException generalEx = new ExpectGeneralException();

	     NullPointerException exception = assertThrows(NullPointerException.class, () -> {
	            generalEx.validateParameters(null);
	        });
	    assertEquals("Parâmetros nulos não são permitidos", exception.getMessage());
		
	}
	
}
