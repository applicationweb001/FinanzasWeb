package com.factoring.app.common;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.factoring.app.model.Article;
import com.factoring.app.model.Banco;
import com.factoring.app.model.Usuario;
import com.factoring.app.service.ArticleService;
import com.factoring.app.service.BancoService;
import com.factoring.app.service.UsuarioService;

@Component
public class PageInitPagination {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private BancoService bancoService;

	// pagination
	private static final int BUTTONS_TO_SHOW = 3;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10 };

	public  ModelAndView initPagination(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Article> articlesList = articleService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(articlesList.getTotalPages(), articlesList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("articlesList", articlesList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);
		return initModelView;
	}
	public  ModelAndView initPaginationUsuario(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Usuario> usuariosList = usuarioService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(usuariosList.getTotalPages(), usuariosList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("usuariosList", usuariosList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);
		return initModelView;
	}
	public  ModelAndView initPaginationBanco(Optional<Integer> pageSize, Optional<Integer> page, String url) {
		ModelAndView initModelView = new ModelAndView(url);
		// If pageSize == null, return initial page size
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
		/*
		 * If page == null || page < 0 (to prevent exception), return initial size Else,
		 * return value of param. decreased by 1
		 */
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Banco> bancosList = bancoService.findAll(PageRequest.of(evalPage, evalPageSize));
		PagerModel pager = new PagerModel(bancosList.getTotalPages(), bancosList.getNumber(), BUTTONS_TO_SHOW);

		initModelView.addObject("bancosList", bancosList);
		initModelView.addObject("selectedPageSize", evalPageSize);
		initModelView.addObject("pageSizes", PAGE_SIZES);
		initModelView.addObject("pager", pager);
		return initModelView;
	}



}
