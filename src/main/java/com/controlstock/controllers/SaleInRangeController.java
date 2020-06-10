package com.controlstock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.services.IStoreService;


@Controller
@RequestMapping("/saleInRange")
public class SaleInRangeController {
	
	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEINRANGE_INDEX);
		
		mAV.getModelMap().addAttribute("minDate", LocalDateTime.class);

		mAV.getModelMap().addAttribute("maxDate", LocalDateTime.class);

		mAV.getModelMap().addAttribute("idStore");
		
		mAV.addObject("stores", storeService.getAll());
		
		return mAV;
	}
	
	@PostMapping("/check")
	public ModelAndView check (@PathVariable("idStore") int idStore, @PathVariable("minDate") LocalDateTime minDate, @PathVariable("maxDate")LocalDateTime maxDate) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.SALEINRANGE_CHECK);
		mAV.addObject("sales", storeService.getSalesInRangeByStore(idStore, minDate, maxDate));
		return mAV;
	}
}

