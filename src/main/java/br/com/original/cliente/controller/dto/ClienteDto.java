package br.com.original.cliente.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.original.cliente.model.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ClienteOut", description = "Representa um cliente")
public class ClienteDto {
	
	@ApiModelProperty(value = "Id de um cliente", example = "3")
	private Long id;
	
	@ApiModelProperty(value = "Nome de um cliente", example = "Jesus", required = true)
	private String nome;	
	
	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public static List<ClienteDto> converter(List<Cliente> clientes) {		
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}


}
