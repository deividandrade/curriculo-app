package com.curriculo.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurriculoDTO {

	@NotBlank(message = "Nome completo é obrigatório")
	private String nomeCompleto;

	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	private String email;

	@NotBlank(message = "Telefone é obrigatório")
	private String telefone;

	@NotNull(message = "Data de nascimento é obrigatória")
	private LocalDate dataNascimento;

	private String cidade;
	private String estado;
	private String linkedin;
	private String github;

	@Size(max = 1000, message = "Objetivo deve ter no máximo 1000 caracteres")
	private String objetivo;

	private List<String> instituicoes;
	private List<String> cursos;
	private List<String> niveis;
	private List<String> anosInicio;
	private List<String> anosConclusao;

	private List<String> empresas;
	private List<String> cargos;
	private List<String> datasInicio;
	private List<String> datasFim;
	private List<String> descricoes;

	private String habilidades;
	private String idiomas;
}
