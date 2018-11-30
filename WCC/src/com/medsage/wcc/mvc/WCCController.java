package com.medsage.wcc.mvc;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import com.medsage.wcc.api.OrderInfo;
import com.medsage.wcc.dto.WCCData;
import com.medsage.wcc.exception.CWAException;
import com.medsage.wcc.util.SpringHelper;

@Controller
public class WCCController {
	Logger log = Logger.getLogger(WCCController.class);


	@RequestMapping(value="/home", method=RequestMethod.GET)
	public ModelAndView doGetOrdersCategory(@ModelAttribute("wccData")WCCData wccData, BindingResult result, SessionStatus status)
	{
		log.info("home called:");
		ModelAndView mv= new ModelAndView("wccform");
		try {

			OrderInfo orderInfo =(OrderInfo) SpringHelper.getBean("medSageService");//Singleton instance;
			Map<String,String> ordersList = orderInfo.getActiveOrders();
			log.info("No of Order:"+ordersList.size());
			String orderId = (String) ordersList.keySet().toArray()[0];
			wccData.setOrderId(orderId);
			ArrayList<String> invoices = orderInfo.getOrderActiveInvoices(orderId);
			log.info("No of Invoices:"+invoices.size());

			mv.addObject("ordersList",ordersList);
			mv.addObject("invoices",invoices);
		}catch (CWAException ex) {
			mv.addObject("error",ex.getMessage());
			log.error("Error:"+ex.getMessage());
		}
		catch (Exception e) {
			mv.addObject("error","Server Internal Error");
			e.printStackTrace();
			log.error("Error:",e);
		}
		mv.addObject("wccData",wccData);
		return mv;

	}
	@RequestMapping(value="/getInvoices", method=RequestMethod.POST)
	public ModelAndView doGetToOrderInvoices(@ModelAttribute("wccData")WCCData wccData, BindingResult result, SessionStatus status)
	{
		log.info("Order Id:"+wccData.getOrderId());
		ModelAndView mv= new ModelAndView("wccform");
		try {
			OrderInfo orderInfo =(OrderInfo) SpringHelper.getBean("medSageService");//Singleton instance;
			Map<String,String> ordersList = orderInfo.getActiveOrders();
			String orderId = wccData.getOrderId();
			ArrayList<String> invoices = orderInfo.getOrderActiveInvoices(orderId);
			mv.addObject("ordersList",ordersList);
			mv.addObject("invoices",invoices);
		}catch (CWAException ex) {
			mv.addObject("error",ex.getMessage());
			log.error("Error:"+ex.getMessage());
		}
		catch (Exception e) {
			mv.addObject("error","Server Internal Error");
			e.printStackTrace();
			log.error("Error:",e);
		}
		mv.addObject("wccData",wccData);
		return mv;

	}
}
