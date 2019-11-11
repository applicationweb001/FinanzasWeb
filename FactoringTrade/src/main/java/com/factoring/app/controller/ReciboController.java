package com.factoring.app.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.factoring.app.common.PageInitPagination;
import com.factoring.app.model.Article;
import com.factoring.app.model.Recibo;
import com.factoring.app.service.ArticleService;
import com.factoring.app.service.ReciboService;

@Controller
@RequestMapping("/recibos")
public class ReciboController {
	
	protected static final String RECIBO_VIEW = "recibos/show"; // view template for single article
	protected static final String RECIBO_ADD_FORM_VIEW = "recibos/new"; // form for new article
	protected static final String RECIBO_EDIT_FORM_VIEW = "recibos/edit"; // form for editing an article
	protected static final String RECIBO_PAGE_VIEW = "recibos/all"; // list with pagination
	protected static final String INDEX_VIEW = "index"; // articles with pagination

	@Autowired
	private PageInitPagination pageInitiPagination;
	
	@Autowired
	private ReciboService reciboService;

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public String getReciboById(@PathVariable(value = "id") Long reciboId, Model model) {
		model.addAttribute("recibo", reciboService.findById(reciboId));
		return RECIBO_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("/new")
	public String newRecibo(Model model) {

		// in case of redirection model will contain article
		if (!model.containsAttribute("recibo")) {
			model.addAttribute("recibo", new Recibo());
		}
		return RECIBO_ADD_FORM_VIEW;
	}
	
	@Secured({"ROLE_ADMIN"})
	@PostMapping("/create")
	public String createRecibo(@Valid Recibo recibo, BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.recibo", result);
			attr.addFlashAttribute("recibo", recibo);
			attr.addFlashAttribute("error", "No se permite recibos");

			return "redirect:/recibos/new";
		}
		
		Recibo newRecibo = reciboService.create(recibo);
		model.addAttribute("recibo", newRecibo);

		return "redirect:/recibos/" + newRecibo.getId();
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping("{id}/edit")
	public String editRecibo(@PathVariable(value = "id") Long reciboId
			, Model model) {
		/*
		 * in case of redirection from '/article/{id}/update' model will contain article
		 * with field values
		 */
		if (!model.containsAttribute("recibo")) {
			model.addAttribute("recibo", reciboService.findById(reciboId));
		}
		return RECIBO_EDIT_FORM_VIEW;
	}

	@Secured({"ROLE_ADMIN"})
	@PostMapping(path = "/{id}/update")
	public String updateRecibo(@PathVariable(value = "id") Long reciboId, 
			@Valid Recibo reciboDetails,
			BindingResult result, Model model, RedirectAttributes attr) {

		if (result.hasErrors()) {

			/// After the redirect: flash attributes pass attributes to the model
			attr.addFlashAttribute("org.springframework.validation.BindingResult.recibo", result);
			attr.addFlashAttribute("recibo", reciboDetails);

			attr.addFlashAttribute("error", "No se permite articulos con el mismo titulo y autor");

			return "redirect:/recibos/" + reciboDetails.getId() + "/edit";
		}

		reciboService.update(reciboId, reciboDetails);
		model.addAttribute("recibo", reciboService.findById(reciboId));
		return "redirect:/recibos/" + reciboId;
	}
	
	@Secured({"ROLE_ADMIN"})
	@GetMapping(value = "/{id}/delete")
	public String deleteRecibo(@PathVariable("id") Long reciboId) {
		//Article article = articleService.findById(articleId);
		// String title = article.getTitle();
		reciboService.delete(reciboId);
		return "redirect:/recibos";
	}

	
}
