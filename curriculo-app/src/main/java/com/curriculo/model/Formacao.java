package com.curriculo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String instituicao;
	private String curso;
	private String nivel; // Ex: Graduação, Pós-Graduação, Técnico
	private String anoInicio;
	private String anoConclusao;
	private boolean emAndamento;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculo_id")
	private Curriculo curriculo;
}
