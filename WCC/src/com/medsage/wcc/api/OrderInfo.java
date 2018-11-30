package com.medsage.wcc.api;

import java.util.ArrayList;
import java.util.Map;

import com.medsage.wcc.exception.CWAException;

public interface OrderInfo {
	public Map<String,String> getActiveOrders() throws CWAException;
	public ArrayList<String> getOrderActiveInvoices(String orderId) throws CWAException;
}
