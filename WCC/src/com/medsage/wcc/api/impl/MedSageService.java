package com.medsage.wcc.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.medsage.wcc.api.OrderInfo;
import com.medsage.wcc.common.Constants;
import com.medsage.wcc.dao.MedSegaDaoService;
import com.medsage.wcc.dto.Invoice;
import com.medsage.wcc.dto.Order;
import com.medsage.wcc.dto.Patient;
import com.medsage.wcc.exception.CWAException;

public class MedSageService implements OrderInfo{
    private HashMap<String,String> activeOrders =null;
    private HashMap<String,String> activePatients =null;
    private HashMap<String,ArrayList<String> > activeInvoices =null;
    
	public Map<String,String> getActiveOrders() throws CWAException{
		return activeOrders;
	}
	
	public ArrayList<String> getOrderActiveInvoices(String orderId) throws CWAException{
		return activeInvoices.get(orderId);
	}
}
