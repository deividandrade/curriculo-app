package com.curriculo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "curriculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curriculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome completo é obrigatório")
	@Size(min = 3, max = 100)
	@Column(nullable = false)
	private String nomeCompleto;

	@NotBlank(message = "E-mail é obrigatório")
	@Email(message = "E-mail inválido")
	@Column(nullable = false, unique = true)
	private String email;

	@NotBlank(message = "Telefone é obrigatório")
	@Column(nullable = false)
	private String telefone;

	@NotNull(message = "Data de nascimento é obrigatória")
	private LocalDate dataNascimento;

	private String cidade;
	private String estado;

	@Column(length = 500)
	private String linkedin;

	@Column(length = 500)
	private String github;

	@Column(length = 1000)
	private String objetivo;

	@OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Formacao> formacoes;

	@OneToMany(mappedBy = "curriculo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Experiencia> experiencias;

	@ElementCollection
	@CollectionTable(name = "habilidades", joinColumns = @JoinColumn(name = "curriculo_id"))
	@Column(name = "habilidade")
	private List<String> habilidades;

	@ElementCollection
	@CollectionTable(name = "idiomas", joinColumns = @JoinColumn(name = "curriculo_id"))
	@Column(name = "idioma")
	private List<String> idiomas;

	@Column(updatable = false)
	private LocalDateTime criadoEm;

	private LocalDateTime atualizadoEm;

	@PrePersist
	protected void onCreate() {
		criadoEm = LocalDateTime.now();
		atualizadoEm = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		atualizadoEm = LocalDateTime.now();
	}
}
