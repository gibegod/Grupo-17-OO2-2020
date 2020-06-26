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
import com.controlstock.models.BatchModel;
import com.controlstock.services.IBatchService;
import com.controlstock.services.IProductService;
import com.controlstock.services.IStoreService;

@Controller
@RequestMapping("/batch")
public class BatchController {

	@Autowired
	@Qualifier("batchService")
	private IBatchService batchService;
	
	@Autowired
	@Qualifier("productService")
	private IProductService productService;
	
	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;
	
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
		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}
	
	@PostMapping("/create")
	public ModelAndView create(@Valid @ModelAttribute("batch") BatchModel batchModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.BATCH_NEW);
		} else {
			mAV.setViewName("redirect:/batch");
			batchService.insert(batchModel);
		}
		return mAV;
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.BATCH_UPDATE);
		mAV.addObject("batch", batchService.findById(id));
		
		mAV.addObject("products", productService.getAll());

		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}
	
	@PostMapping("/update")
	public ModelAndView update(@Valid @ModelAttribute("batch") BatchModel batchModel, BindingResult bindingResult) {
		ModelAndView mAV = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mAV.setViewName(ViewRouteHelper.BATCH_UPDATE);
		} else {
			mAV.setViewName("redirect:/batch");
			batchService.update(batchModel);
		}
		return mAV;
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		RedirectView rVT = new RedirectView(ViewRouteHelper.BATCH_ROOT);
		boolean rem = batchService.remove(id);	
		
		//Si rem es falso que siga en la misma vista y tire el error. Si es true que vaya al index
		if(rem == false) {
			RedirectView rVF = new RedirectView("/batch/{id}");
		    redirectAttrs.addFlashAttribute("mensaje", "ERROR: The chosen batch is related to other objects.")
		    			.addFlashAttribute("clase", "danger");
			return rVF;
		} else {
			return rVT;
		}
	}
		
}
