package br.com.original.cliente.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.original.cliente.controller.dto.ClienteDto;
import br.com.original.cliente.controller.form.ClienteForm;
import br.com.original.cliente.modelo.Cliente;
import br.com.original.cliente.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<ClienteDto> listaClientes(){
		
			List<Cliente> clientes = clienteRepository.findAll();			
			return ClienteDto.converter(clientes);
		
	}
	
	@GetMapping("/{id}")
	public ClienteDto clientePorId(@PathVariable("id") long id){
		
			Cliente cliente = clienteRepository.getOne(id);
			return new ClienteDto(cliente);
				
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastraCliente(@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		
		try {
			
			Cliente cliente = form.converter();
			clienteRepository.save(cliente);
			
			URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizaCliente(@PathVariable Long id, @RequestBody @Valid ClienteForm form){
		
		Cliente cliente = form.atualizar(id, clienteRepository);		
		return ResponseEntity.ok(new ClienteDto(cliente));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeCliente(@PathVariable Long id){
		
		clienteRepository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	
}
