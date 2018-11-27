package com.wb.casadocodigo.greendogdelivery.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wb.casadocodigo.greendogdelivery.domain.Cliente;
import com.wb.casadocodigo.greendogdelivery.repository.ClienteRepository;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/")
	public ModelAndView list() {
		Iterable<Cliente> clientes = this.repository.findAll();
		return new ModelAndView( "clientes/list", "clientes", clientes);
	}
	
	@GetMapping("{id}")
	public ModelAndView view(@PathVariable("id") Cliente cliente) {
		return new ModelAndView("clientes/view", "cliente", cliente);	
	}
	
	@GetMapping("/novo")
	public String createForm(@ModelAttribute Cliente cliente) {
		return "clientes/form";
	}
	
	@PostMapping(params="form")
	public ModelAndView create(@Valid Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return new ModelAndView("clientes"+"form", "formErros",result.getAllErrors());
		}
		
		cliente = this.repository.save(cliente);
		redirectAttributes.addFlashAttribute("globalMessage","Cliente gravado com sucesso!");

		return new ModelAndView("redirect:/"+"clientes/"+"{cliente.id}","cliente.id", cliente.getId()); 
	}
	
	@GetMapping(value="alterar/{id}")
	public ModelAndView alterarForm(@PathVariable("id") Cliente cliente) {
		return new ModelAndView("clientes/form", "cliente", cliente);
	}
	
	@GetMapping(value="remover/{id}")
	public ModelAndView remover(@PathVariable("id") Long id,RedirectAttributes redirect) {
		this.repository.delete(id);
		Iterable<Cliente> clientes = repository.findAll();
		ModelAndView mv = new ModelAndView("clientes/list","clientes",clientes);
		mv.addObject("globalMessage","Cliente removido com sucesso!");
		return mv;	
	}
	
}
