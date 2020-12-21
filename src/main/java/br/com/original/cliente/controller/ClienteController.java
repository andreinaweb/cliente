package br.com.original.cliente.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import br.com.original.cliente.model.Cliente;
import br.com.original.cliente.repository.ClienteRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Clientes")
@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@ApiOperation("Lista todos os clientes")
	@GetMapping
	public List<ClienteDto> listaClientes(String nome){
		
		if(nome == null) {
			List<Cliente> clientes = clienteRepository.findAll();			
			return ClienteDto.converter(clientes);
		}else {
			List<Cliente> clientes = clienteRepository.findByNome(nome);
			return ClienteDto.converter(clientes);
		}
	}
		
	@ApiOperation("Lista um cliente pelo ID")
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> clientePorId(@ApiParam(value = "Id de um cliente", example = "1") @PathVariable("id") long id){
		
			Optional<Cliente> cliente = clienteRepository.findById(id);
			if(cliente.isPresent()) {				
				return ResponseEntity.ok(new ClienteDto(cliente.get()));
			}
			
			return ResponseEntity.notFound().build();
			
	}
	

	@ApiOperation("Adiciona um novo cliente")
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDto> cadastraCliente(@ApiParam(name = "corpo", value = "Representação de um novo cliente") 
			@RequestBody @Valid ClienteForm form, UriComponentsBuilder uriBuilder) {
		
		try {
			
			Cliente cliente = form.converter();
			clienteRepository.save(cliente);
			
			URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
			return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
		}catch (Exception e) {
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} 
	}
	
	@ApiOperation("Atualiza os dados de um cliente")
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDto> atualizaCliente(@ApiParam(value = "ID de um cliente para atualizar", example = "1")
			@PathVariable Long id, @ApiParam(name = "corpo", value = "Representação de cliente atualizado") @RequestBody @Valid ClienteForm form){
		
		Optional<Cliente> optional = clienteRepository.findById(id);
		if(optional.isPresent()) {
			
			Cliente cliente = form.atualizar(id, clienteRepository);			
			return ResponseEntity.ok(new ClienteDto(cliente));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@ApiOperation("Exclui um cliente")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> removeCliente(@ApiParam(value = "ID de um cliente para excluir", example = "1") @PathVariable Long id){
		
		clienteRepository.deleteById(id);
		return ResponseEntity.ok().build();
		
	}
	
	
	
}
