package com.controlstock.controllers;

import java.time.LocalDate;

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
import com.controlstock.services.ISaleService;
import com.controlstock.services.IStoreService;

@Controller
@RequestMapping("/productsDates")
public class ProductsDatesController {
	
	@Autowired
	@Qualifier("storeService")
	private IStoreService storeService;
	
	@Autowired
	@Qualifier("saleService")
	private ISaleService saleService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTSDATES_INDEX);
		mAV.addObject("stores", storeService.getAll());
		return mAV;
	}
	
	@GetMapping("/show/{storeId}/{date1}/{date2}")
	public ModelAndView show(@PathVariable("storeId") int storeId, @PathVariable("date1") String date1, 
			@PathVariable("date2") String date2) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTSDATES_TABLE);
		//Products de ese store que estan entre esas dos fechas
		String[] straux1 = date1.split("-");
		LocalDate date1bis = LocalDate.of(Integer.parseInt(straux1[0]), Integer.parseInt(straux1[1]), 
				Integer.parseInt(straux1[2]));
		String[] straux2 = date2.split("-");
		LocalDate date2bis = LocalDate.of(Integer.parseInt(straux2[0]), Integer.parseInt(straux2[1]), 
				Integer.parseInt(straux2[2]));

		mAV.addObject("saleRequests", saleService.getSaleRequestsByDates(storeId, date1bis, date2bis));
		return mAV;
	}
	
}
