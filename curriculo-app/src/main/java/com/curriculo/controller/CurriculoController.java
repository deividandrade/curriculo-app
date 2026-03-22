package com.curriculo.controller;

import com.curriculo.dto.CurriculoDTO;
import com.curriculo.model.Curriculo;
import com.curriculo.service.CurriculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/curriculo")
@RequiredArgsConstructor
public class CurriculoController {

	private final CurriculoService curriculoService;

	@GetMapping("/cadastro")
	public String exibirFormulario(Model model) {
		model.addAttribute("curriculoDTO", new CurriculoDTO());
		return "cadastro"; // templates/cadastro.html
	}

	@PostMapping("/cadastro")
	public String salvar(@Valid @ModelAttribute("curriculoDTO") CurriculoDTO dto, BindingResult result,
			RedirectAttributes redirectAttributes, Model model) {

		if (result.hasErrors()) {
			return "cadastro";
		}

		try {
			Curriculo salvo = curriculoService.salvar(dto);
			redirectAttributes.addFlashAttribute("sucesso",
					"Currículo de " + salvo.getNomeCompleto() + " cadastrado com sucesso!");
			return "redirect:/curriculo/sucesso";
		} catch (IllegalArgumentException e) {
			model.addAttribute("erro", e.getMessage());
			return "cadastro";
		}
	}

	@GetMapping("/sucesso")
	public String sucesso() {
		return "sucesso";
	}

	@GetMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("curriculos", curriculoService.listarTodos());
		return "lista";
	}
}
