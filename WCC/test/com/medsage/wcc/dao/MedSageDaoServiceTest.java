package com.medsage.wcc.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.medsage.wcc.dto.Invoice;
import com.medsage.wcc.dto.Order;
import com.medsage.wcc.dto.Patient;

public class MedSageDaoServiceTest {
	String filePath="medsage_csvs/";
	
	@Test
	public void validateOrders(){
		MedSageDaoService medSage = new MedSageDaoService(filePath);
		ArrayList<Order> orders = medSage.getOrderData();
		assertTrue(orders.size()>0);
	}
	@Test
	public void validatePatients(){
		MedSageDaoService medSage = new MedSageDaoService(filePath);
		ArrayList<Patient> patients = medSage.getPatientData();
		assertTrue(patients.size()>0);
	}
	@Test
	public void validateInvoices(){
		MedSageDaoService medSage = new MedSageDaoService(filePath);
		ArrayList<Invoice> invoices = medSage.getInvoiceData();
		assertTrue(invoices.size()>0);
	}
	@Test
	public void loadDataTest(){
		MedSageDaoService medSage = new MedSageDaoService(filePath);
		medSage.printData();
		assertTrue(true);
	}
}
