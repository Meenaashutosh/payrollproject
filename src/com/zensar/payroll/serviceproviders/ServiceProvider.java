package com.zensar.payroll.serviceproviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zensar.payroll.daoservices.PayrollDAOServices;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.services.PayrollServices;

public class ServiceProvider {

	private static Properties payrollProperties;
	static {	
		try {
			payrollProperties = new Properties();
			FileInputStream src = new FileInputStream(new File(".\\Resource\\zensarpayrollsystem.properites"));
			payrollProperties.load(src);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static PayrollServices getPayrollServices() throws PayrollServicesDownException{	
		try {
			String payrollServices = payrollProperties.getProperty("payrollServices");
			Class c = Class.forName(payrollServices);
			return (PayrollServices) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		}
	}
	
	public static PayrollDAOServices getPayrollDAOServices() throws PayrollServicesDownException{		
		try {
			String payrollDAOServices = payrollProperties.getProperty("payrollDAOServices");
			Class c = Class.forName(payrollDAOServices);
			return (PayrollDAOServices) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		}
	}

	public static Connection getDBConnection() throws PayrollServicesDownException {
		try {
			Class.forName(payrollProperties.getProperty("driver"));
			/*String url = payrollProperties.getProperty("url");
			String userName = payrollProperties.getProperty("userName");
			String password = payrollProperties.getProperty("password");
			Connection con = DriverManager.getConnection(url, userName, password);
			return con;*/
			return DriverManager.getConnection(payrollProperties.getProperty("url"), payrollProperties.getProperty("userName"), payrollProperties.getProperty("password"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down plz try after some time", e);
		}
	}



	/*public static PayrollDAOServices getPayrollDAOServices() {
		return new PayrollDAOServicesImpl();
	}
	
	public static PayrollServices getPayrollServices() {
		return new PayrollServicesImpl();
	}*/
}
