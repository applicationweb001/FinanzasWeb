package com.factoring.app.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.factoring.app.common.PageInitPagination;
import com.factoring.app.model.Banco;
import com.factoring.app.service.BancoService;

@Controller
@RequestMapping("/bancos")
public class BancoController {

	protected static final String BANCO_VIEW = "bancos/showBanco"; // view template for single article
	protected static final String BANCO_ADD_FORM_VIEW = "bancos/newBanco"; // form for new article
	protected static final String BANCO_EDIT_FORM_VIEW = "bancos/editBanco"; // form for editing an article

	protected static final String BANCO_PAGE_VIEW = "bancos/allBancos"; // list with pagination

	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private BancoService bancoService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getBancoById(@PathVariable(value = "id") Long bancoId, Model model) {
		model.addAttribute("banco", bancoService.findById(bancoId));
		return BANCO_VIEW;
	}

	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllBancos(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPaginationBanco(pageSize, page, BANCO_PAGE_VIEW);
		return modelAndView;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newBanco(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("banco")) {
			model.addAttribute("banco", new Banco());
		}
		return BANCO_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createBanco(@Valid Banco banco, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.banco", result);
			attr.addFlashAttribute("banco", banco);


			return "redirect:/bancos/new";
		}
		Banco newBanco = bancoService.create(banco);
		model.addAttribute("banco", newBanco);

		return "redirect:/bancos/" + newBanco.getId();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editBanco(@PathVariable(value = "id") Long bancoId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("banco")) {
			model.addAttribute("banco", bancoService.findById(bancoId));
		}
		return BANCO_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateBanco(@PathVariable(value = "id") Long bancoId, @Valid Banco bancoDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || bancoService.BancoValid(bancoDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.banco", result);
			attr.addFlashAttribute("banco", bancoDetails);

			attr.addFlashAttribute("error", "No se permite cbanco");

			return "redirect:/bancos/" + bancoDetails.getId() + "/edit";
		}

		bancoService.update(bancoId, bancoDetails);
		model.addAttribute("banco", bancoService.findById(bancoId));
		return "redirect:/bancos/" + bancoId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteBanco(@PathVariable("id") Long bancoId) {
		//Article article = articleService.findById(articleId);
		// String title = article.getTitle();
		bancoService.delete(bancoId);
		return "redirect:/bancos";
	}

	
	
}
