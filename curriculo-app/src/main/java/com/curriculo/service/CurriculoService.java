package com.curriculo.service;

import com.curriculo.dto.CurriculoDTO;
import com.curriculo.model.*;
import com.curriculo.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurriculoService {

	private final CurriculoRepository curriculoRepository;

	@Transactional
	public Curriculo salvar(CurriculoDTO dto) {

		if (curriculoRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("Já existe um currículo cadastrado com este e-mail.");
		}

		Curriculo curriculo = Curriculo.builder().nomeCompleto(dto.getNomeCompleto()).email(dto.getEmail())
				.telefone(dto.getTelefone()).dataNascimento(dto.getDataNascimento()).cidade(dto.getCidade())
				.estado(dto.getEstado()).linkedin(dto.getLinkedin()).github(dto.getGithub()).objetivo(dto.getObjetivo())
				.habilidades(parseList(dto.getHabilidades())).idiomas(parseList(dto.getIdiomas())).build();

		List<Formacao> formacoes = new ArrayList<>();
		if (dto.getInstituicoes() != null) {
			for (int i = 0; i < dto.getInstituicoes().size(); i++) {
				if (!dto.getInstituicoes().get(i).isBlank()) {
					Formacao f = Formacao.builder().instituicao(get(dto.getInstituicoes(), i))
							.curso(get(dto.getCursos(), i)).nivel(get(dto.getNiveis(), i))
							.anoInicio(get(dto.getAnosInicio(), i)).anoConclusao(get(dto.getAnosConclusao(), i))
							.curriculo(curriculo).build();
					formacoes.add(f);
				}
			}
		}
		curriculo.setFormacoes(formacoes);

		curriculo.setExperiencias(experiencias);

		return curriculoRepository.save(curriculo);
	}

	public List<Curriculo> listarTodos() {
		return curriculoRepository.findAll();
	}

}
