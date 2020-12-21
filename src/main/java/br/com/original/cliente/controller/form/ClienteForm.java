package br.com.original.cliente.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.original.cliente.model.Cliente;
import br.com.original.cliente.repository.ClienteRepository;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ClienteIn", description = "Representa um cliente")
public class ClienteForm {

	@ApiModelProperty(value = "Id de um cliente", example = "3")
	private Long id;
	
	@ApiModelProperty(value = "Nome de um cliente", example = "Cristo", required = true)
	@NotNull @NotEmpty @Length(min=5)
	private String nome;
	
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
	
	public Cliente converter() {
		return new Cliente(nome);
	}
	
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		
		Cliente cliente = clienteRepository.getOne(id);
		cliente.setNome(this.nome);
		return cliente;
	}
	
}
