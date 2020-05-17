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
import com.controlstock.models.BatchModel;
import com.controlstock.services.IBatchService;
import com.controlstock.services.IProductService;

@Controller
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	@Qualifier("batchService")
	private IBatchService batchService;
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BATCH_INDEX);
		mAV.addObject("batchs", batchService.getAll());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BATCH_NEW);
		mAV.addObject("batch", new BatchModel());
		mAV.addObject("products", productService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("batch") BatchModel batchModel) {
		batchService.insert(batchModel);
		return new RedirectView(ViewRouteHelper.BATCH_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BATCH_UPDATE);
		mAV.addObject("batch", batchService.findById(id));
		mAV.addObject("products", productService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("batch") BatchModel batchModel) {
		batchService.update(batchModel);
		return new RedirectView(ViewRouteHelper.BATCH_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id) {
		batchService.remove(id);
		return new RedirectView(ViewRouteHelper.BATCH_ROOT);
	}
		
}
