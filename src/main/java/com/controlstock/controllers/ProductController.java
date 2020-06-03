package com.controlstock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
	public ModelAndView index () {
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
	public RedirectView create(@ModelAttribute("product") ProductModel productModel) {
		productService.insertOrUpdate(productModel);
		return new RedirectView(ViewRouteHelper.PRODUCT_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCT_UPDATE);
		mAV.addObject("product", productService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("product") ProductModel productModel) {
		productService.insertOrUpdate(productModel);
		return new RedirectView(ViewRouteHelper.PRODUCT_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id) {
		productService.remove(id);
		return new RedirectView(ViewRouteHelper.PRODUCT_ROOT);
	}
	
}
