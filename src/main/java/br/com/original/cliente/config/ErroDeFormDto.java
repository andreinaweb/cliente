package br.com.original.cliente.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ObjetoDeErros", description = "Objeto com campo e mensagem do erro")
public class ErroDeFormDto {

	@ApiModelProperty(example = "nome")
	private String campo;
	
	@ApiModelProperty(example = "não deve estar vazio")
	private String erro;
	
	public ErroDeFormDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	@Override
	public String toString() {
		return "ErroDeFormDto [campo=" + campo + ", erro=" + erro + "]";
	}
	
}
