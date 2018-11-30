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
	private String filesPath="";
    
    public void loadData(){
	   	activeOrders= new HashMap<String,String>();
    	activePatients= new HashMap<String,String>();
    	activeInvoices= new HashMap<String,ArrayList<String>>();


    	MedSegaDaoService medSegaDao = new MedSegaDaoService(filesPath);
    	for(Order order:medSegaDao.getOrderData()){
    		if(order.getState().equalsIgnoreCase(Constants.STATE_ACTIVE)){
    		activeOrders.put(order.getOrderId(), order.getCategory());
    		}
    	}
    	for(Patient patient:medSegaDao.getPatientData()){
    		if(patient.getState().equalsIgnoreCase(Constants.STATE_ACTIVE)){
    			activePatients.put(patient.getPatientId(), patient.getName());
    		}
    	}
    	for(Invoice invoice:medSegaDao.getInvoiceData()){
    		if(invoice.getState().equalsIgnoreCase(Constants.STATE_ACTIVE)){
    			if((activeOrders.get(invoice.getOrderId())!=null) && (activePatients.get(invoice.getPatientId())!=null)){
    				if(activeInvoices.get(invoice.getOrderId())!=null){
    					activeInvoices.get(invoice.getOrderId()).add(activePatients.get(invoice.getPatientId()));
    				}else{
    					ArrayList<String> patientList = new ArrayList<String>();
     					patientList.add(activePatients.get(invoice.getPatientId()));
     					activeInvoices.put(invoice.getOrderId(),patientList);
    				}
    			}
    		}
    	}
    	
    }
	public Map<String,String> getActiveOrders() throws CWAException{
		return activeOrders;
	}
	
	public ArrayList<String> getOrderActiveInvoices(String orderId) throws CWAException{
		return activeInvoices.get(orderId);
	}
}
