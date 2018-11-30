package com.medsage.wcc.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.medsage.wcc.dto.WCCData;

@Controller
public class WCCController {
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView doGetOrdersCategory(@ModelAttribute("wccData")WCCData wccData, BindingResult result, SessionStatus status)
	{
		ModelAndView mv= new ModelAndView("wccform");
		return mv;
	}
	@RequestMapping(value="/getInvoices", method=RequestMethod.POST)
	public ModelAndView doGetToOrderInvoices(@ModelAttribute("wccData")WCCData wccData, BindingResult result, SessionStatus status)
	{
		ModelAndView mv= new ModelAndView("wccform");
		return mv;
	}
}
