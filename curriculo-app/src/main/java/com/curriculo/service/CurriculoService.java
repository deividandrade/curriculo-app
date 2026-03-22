package com.curriculo.service;

import com.curriculo.dto.CurriculoDTO;
import com.curriculo.model.*;
import com.curriculo.repository.CurriculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

		List<Experiencia> experiencias = new ArrayList<>();
		if (dto.getEmpresas() != null) {
			for (int i = 0; i < dto.getEmpresas().size(); i++) {
				if (!dto.getEmpresas().get(i).isBlank()) {
					Experiencia e = Experiencia.builder().empresa(get(dto.getEmpresas(), i))
							.cargo(get(dto.getCargos(), i)).dataInicio(get(dto.getDatasInicio(), i))
							.dataFim(get(dto.getDatasFim(), i)).descricao(get(dto.getDescricoes(), i))
							.curriculo(curriculo).build();
					experiencias.add(e);
				}
			}
		}
		curriculo.setExperiencias(experiencias);

		return curriculoRepository.save(curriculo);
	}

	public List<Curriculo> listarTodos() {
		return curriculoRepository.findAll();
	}

	private List<String> parseList(String valor) {
		if (valor == null || valor.isBlank())
			return new ArrayList<>();
		return Arrays.stream(valor.split(",")).map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
	}

	private String get(List<String> lista, int i) {
		if (lista == null || i >= lista.size())
			return "";
		return lista.get(i) != null ? lista.get(i) : "";
	}
}
