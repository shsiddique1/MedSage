package com.medsage.wcc.api.impl;


import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.medsage.wcc.api.OrderInfo;
import com.medsage.wcc.exception.CWAException;


public class MedSageServiceTest {
	String filePath="C:/Dev/apache-tomcat-8.5.35/webapps/WCC/medsage_csvs/";
	@Test
	public void activeOrdersTest() throws CWAException{
		try{
			OrderInfo orderInfo =(OrderInfo) new MedSageService(filePath);
			Map<String,String> orders=orderInfo.getActiveOrders();
			assertTrue(orders.size()>0);
		}
		catch(CWAException ex){
			System.out.println("CWAExcption:"+ex.toString());
			throw ex;
		}
	}
	
	@Test
	public void activeInvoicesTest() throws CWAException{
		try{
			String orderId = "C126767"; 
			OrderInfo orderInfo =(OrderInfo) new MedSageService(filePath);
			ArrayList<String> invoices=orderInfo.getOrderActiveInvoices(orderId);
			assertTrue(invoices.size()>0);
		
		}
		catch(CWAException ex){
			System.out.println("CWAExcption:"+ex.toString());
			throw ex;
		}

	}
}
