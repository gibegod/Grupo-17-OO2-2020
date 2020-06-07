package com.controlstock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.services.IProductRankingService;

@Controller
@RequestMapping("/productRanking")
public class ProductRankingController {
	
	@Autowired
	@Qualifier("productRankingService")
	private IProductRankingService productRankingService;

	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCT_RANKING_INDEX);
		mAV.addObject("products", productRankingService.getAll());
		return mAV;
	}
}
