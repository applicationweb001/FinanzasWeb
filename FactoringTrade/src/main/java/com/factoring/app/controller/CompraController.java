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
import com.factoring.app.model.Compra;
import com.factoring.app.service.CompraService;

@Controller
@RequestMapping("/compras")
public class CompraController {

	protected static final String COMPRA_VIEW = "compras/showCompra"; // view template for single article
	protected static final String COMPRA_ADD_FORM_VIEW = "compras/newCompra"; // form for new article
	protected static final String COMPRA_EDIT_FORM_VIEW = "compras/editCompra"; // form for editing an article

	protected static final String COMPRA_PAGE_VIEW = "compras/allCompras"; // list with pagination

	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private CompraService compraService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getCompraById(@PathVariable(value = "id") Long compraId, Model model) {
		model.addAttribute("compra", compraService.findById(compraId));
		return COMPRA_VIEW;
	}

	
	@Secured({"ROLE_ADMIN","ROLE_USER"})
	@GetMapping
	public ModelAndView getAllCompras(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView modelAndView = pageInitiPagination.initPagination(pageSize, page, COMPRA_PAGE_VIEW);
		return modelAndView;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newCompra(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("compra")) {
			model.addAttribute("compra", new Compra());
		}
		return COMPRA_ADD_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createCompra(@Valid Compra compra, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || compraService.CompraValid(compra) == false) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.compra", result);
			attr.addFlashAttribute("compra", compra);

			attr.addFlashAttribute("error", "No se permite compras");

			return "redirect:/compras/new";
		}
		Compra newCompra = compraService.create(compra);
		model.addAttribute("compra", newCompra);

		return "redirect:/compras/" + newCompra.getId();
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editCompra(@PathVariable(value = "id") Long compraId, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("compra")) {
			model.addAttribute("compra", compraService.findById(compraId));
		}
		return COMPRA_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateCompra(@PathVariable(value = "id") Long compraId, @Valid Compra compraDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors() || compraService.CompraValid(compraDetails) == false) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.compra", result);
			attr.addFlashAttribute("compra", compraDetails);

			attr.addFlashAttribute("error", "No se permite compras");

			return "redirect:/compras/" + compraDetails.getId() + "/edit";
		}

		compraService.update(compraId, compraDetails);
		model.addAttribute("compra", compraService.findById(compraId));
		return "redirect:/compras/" + compraId;
	}

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteCompra(@PathVariable("id") Long compraId) {
		//Article article = articleService.findById(articleId);
		// String title = article.getTitle();
		compraService.delete(compraId);
		return "redirect:/compras";
	}

	
}
