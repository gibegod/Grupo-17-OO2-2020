package com.controlstock.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.ProductModel;
import com.controlstock.services.IProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	@Qualifier("productService")
	private IProductService productService;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCT_INDEX);
		mAV.addObject("products", productService.getAll());
		return mAV;
	}

	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCT_NEW);
		mAV.addObject("product", new ProductModel());
		return mAV;
	}

	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("product") ProductModel productModel,
			BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.PRODUCT_NEW);
		} else {
			mAV.setViewName("redirect:/product");
			productService.insertOrUpdate(productModel);
		}
		return mAV;
	}

	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCT_UPDATE);
		mAV.addObject("product", productService.findById(id));
		return mAV;
	}

	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("product") ProductModel productModel,
			BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.PRODUCT_UPDATE);
		} else {
			mAV.setViewName("redirect:/product");
			productService.insertOrUpdate(productModel);
		}
		return mAV;
	}

	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		RedirectView rVT = new RedirectView(ViewRouteHelper.PRODUCT_ROOT);
		boolean rem = productService.remove(id);

		// Si rem es falso que siga en la misma vista y tire el error. Si es true que
		// vaya al index
		if (rem == false) {
			RedirectView rVF = new RedirectView("/product/{id}");
			redirectAttrs.addFlashAttribute("mensaje", "ERROR: The chosen product is related to other objects.")
					.addFlashAttribute("clase", "danger");
			return rVF;
		} else {
			return rVT;
		}
	}

}
