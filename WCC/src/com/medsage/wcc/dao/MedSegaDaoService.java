package com.medsage.wcc.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import com.medsage.wcc.api.impl.MedSageService;
import com.medsage.wcc.common.Constants;
import com.medsage.wcc.dto.Invoice;
import com.medsage.wcc.dto.Order;
import com.medsage.wcc.dto.Patient;
import org.apache.log4j.Logger;

public class MedSegaDaoService {
	private Logger log = Logger.getLogger(MedSegaDaoService.class);
	private ArrayList<Order> orders =null;
	private ArrayList<Patient> patients =null;
	private ArrayList<Invoice> invoices =null;

	public MedSegaDaoService(String filesPath){
		loadData(filesPath);
	}
	private void loadData(String filesPath){
		orders= new ArrayList<Order> ();
		patients= new ArrayList<Patient>();
		invoices= new ArrayList<Invoice> ();
		try {
			File folder = new File(filesPath);
			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String cvsSplitBy=",";
					String line = br.readLine();
					String[] columns = line.split(cvsSplitBy);
					ArrayList<String> firstRow = new ArrayList<String>(Arrays.asList(columns));
					if(firstRow.contains(Constants.ORDER_CATEGORY_COLUMN)){	//Orders Data
						while ((line = br.readLine()) != null) {
							// use comma as separator
							String[] orderData = line.split(cvsSplitBy);
							Order order = new Order();
							for (int i=0;i<columns.length;i++){ // Column order in the CSV is unspecified
								if(columns[i].equalsIgnoreCase(Constants.ORDER_ID_COLUMN)){
									order.setOrderId(orderData[i]);
								}else if(columns[i].equalsIgnoreCase(Constants.ORDER_CATEGORY_COLUMN)){
									order.setCategory(orderData[i]);
								}else{
									order.setState(orderData[i]);
								}
							}
							orders.add(order);
						}
					}else if (firstRow.contains(Constants.PATIENT_NAME_COLUMN)){ // Patients Data
						while ((line = br.readLine()) != null) {
							// use comma as separator
							String[] data = line.split(cvsSplitBy);
							Patient patient = new Patient();
							for (int i=0;i<columns.length;i++){ // Column order in the CSV is unspecified
								if(columns[i].equalsIgnoreCase(Constants.PATIENT_ID_COLUMN)){
									patient.setPatientId(data[i]);
								}else if(columns[i].equalsIgnoreCase(Constants.PATIENT_NAME_COLUMN)){
									patient.setName(data[i]);
								}else{
									patient.setState(data[i]);
								}
							}
							patients.add(patient);
						}
					}else if (firstRow.contains(Constants.ORDER_ID_COLUMN)&&firstRow.contains(Constants.PATIENT_ID_COLUMN)){//Invoice Data
						while ((line = br.readLine()) != null) {
							// use comma as separator
							String[] data = line.split(cvsSplitBy);
							Invoice invoice = new Invoice();
							for (int i=0;i<columns.length;i++){ // Column order in the CSV is unspecified
								if(columns[i].equalsIgnoreCase(Constants.ORDER_ID_COLUMN)){
									invoice.setOrderId(data[i]);
								}else if(columns[i].equalsIgnoreCase(Constants.PATIENT_ID_COLUMN)){
									invoice.setPatientId(data[i]);
								}else{
									invoice.setState(data[i]);
								}
							}
							invoices.add(invoice);
						}
					} // if file
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Error Files not found",e);
		} catch( Exception e) {
			e.printStackTrace();
			log.error("Error reading data files",e);
		} finally {

		}
	}
	private void printData(){
		System.out.println("OrderData========");
		for(Order order:orders){
			System.out.println(order.getOrderId()+","+order.getCategory()+","+order.getState());
		}
		System.out.println("patientData========");
		for(Patient patient:patients){
			System.out.println(patient.getPatientId()+","+patient.getName()+","+patient.getState());
		}
		System.out.println("invoiceData========");
		for(Invoice invoice:invoices){
			System.out.println(invoice.getOrderId()+","+invoice.getPatientId()+","+invoice.getState());
		}
		System.out.println("========================");
	}
	public ArrayList<Order> getOrderData(){
		return orders;
	}
	public ArrayList<Patient> getPatientData(){
		return patients;
	}
	public ArrayList<Invoice> getInvoiceData(){
		return invoices;
	}
	public static void main(String[ ] args){

	}

}
