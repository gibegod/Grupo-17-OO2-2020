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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.controlstock.helpers.ViewRouteHelper;
import com.controlstock.models.ClientModel;
import com.controlstock.services.IClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	@Qualifier("clientService")
	private IClientService clientService;
	
	@GetMapping("")
	public ModelAndView index () {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENT_INDEX);
		mAV.addObject("clients", clientService.getAll());
		return mAV;
	}
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENT_NEW);
		mAV.addObject("client", new ClientModel());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("client") ClientModel clientModel) {
		clientService.insertOrUpdate(clientModel);
		return new RedirectView(ViewRouteHelper.CLIENT_ROOT);
	}
	
	@GetMapping("/{id}")
	public ModelAndView get(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CLIENT_UPDATE);
		mAV.addObject("client", clientService.findById(id));
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("client") ClientModel clientModel) {
		clientService.insertOrUpdate(clientModel);
		return new RedirectView(ViewRouteHelper.CLIENT_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete (@PathVariable("id") int id, RedirectAttributes redirectAttrs) {
		RedirectView rVT = new RedirectView(ViewRouteHelper.CLIENT_ROOT);
		boolean rem = clientService.remove(id);	
		
		//Si rem es falso que siga en la misma vista y tire el error. Si es true que vaya al index
		if(rem == false) {
			RedirectView rVF = new RedirectView("/client/{id}");
		    redirectAttrs.addFlashAttribute("mensaje", "ERROR: El client seleccionado tiene relacion con otras clases (FK)")
		    			.addFlashAttribute("clase", "danger");
			return rVF;
		} else {
			return rVT;
		}
	}
		
}
