package br.edu.itb.exemplo.democrud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.EnumSet;
import java.util.List;

//import org.apache.tomcat.util.codec.binary.Base64;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.itb.exemplo.democrud.model.entity.Produto;
import br.edu.itb.exemplo.democrud.model.enums.EnumTipoProd;
import br.edu.itb.exemplo.democrud.model.repository.ProdutoRepository;

//@RestController
@Controller
@RequestMapping("/api/v1/produto")
public class ProdutoController {

	private ProdutoRepository produtoRepository;
	
	private String foto = "";

	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@GetMapping("/todos-produtos")
	public String todos(Model model) {
		model.addAttribute("produtos", produtoRepository.findAll());		
	    return "produtos";
	}
	
	@GetMapping("/novo-produto")
	public String novoProduto(Produto prod, Model model) {
		model.addAttribute("prod", prod);
		model.addAttribute("tipos", tipos);
		return "novo-prod";
	}
	
	@PostMapping("/add-prod")
	public String gravarNovoProduto(Produto prod, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "novo-prod";
		}
		prod.setStatusProd("ATIVO");
		produtoRepository.save(prod);
		return "redirect:/api/v1/produto/todos-produtos";
	}

	@GetMapping("/todos")
	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	@GetMapping("/editar-prod/{id}")
	public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
		Produto prod = produtoRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid prod Id:" + id));
	
		if (prod.getFoto() != null) {
			foto = Base64.getEncoder().encodeToString(prod.getFoto());
		}

		model.addAttribute("prod", prod);
		model.addAttribute("tipos", EnumTipoProd.values());
		model.addAttribute("foto", foto);
		
		return "editar-prod";
	}

	@PostMapping("/update/{id}")
	public String atualizarProd(
			@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id, 
			@ModelAttribute("prod") Produto prod, BindingResult result) {

		if (result.hasErrors()) {
			prod.setId(id);
			return "editar-prod";
		}
		
		if (file != null && !file.getOriginalFilename().isEmpty()) {
			try {
				prod.setFoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			prod.setFoto(null);
		}

		produtoRepository.save(prod);
		return "redirect:/api/v1/produto/todos-produtos";
	}
	
	@PostMapping
	public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

	/*
	@PatchMapping("/atualizar/{id}")
	public String atualizar(@PathVariable("id") int id, Produto prod, BindingResult result, Model model) {

		if (result.hasErrors()) {
			prod.setId(id);
			return "editar-prod";
		}
		produtoRepository.save(prod);
		return "redirect:/api/v1/index";
	}
    */
	
	List<String> tipos = carregarAtributos();
	
	public List<String> carregarAtributos() {
		List<EnumTipoProd> lista = Arrays.asList(EnumTipoProd.values());
		List<String> retorno = new ArrayList<String>();
		for (int i = 0; i < lista.size(); i++) {
			retorno.add(lista.get(i).toString());
		}
		return retorno;
	}
	
	public List<EnumTipoProd> carregarAtributos2() {
		return Arrays.asList(EnumTipoProd.values());
	}
	
	public EnumSet<EnumTipoProd> listarAtributos() {
	    return EnumSet.allOf(EnumTipoProd.class);
	}

}
