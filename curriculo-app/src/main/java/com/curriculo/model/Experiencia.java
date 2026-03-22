package com.curriculo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experiencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String empresa;
	private String cargo;
	private String dataInicio;
	private String dataFim;
	private boolean empregoAtual;

	@Column(length = 1000)
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "curriculo_id")
	private Curriculo curriculo;
}
