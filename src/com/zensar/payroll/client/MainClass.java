package com.zensar.payroll.client;

import java.util.Scanner;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.serviceproviders.ServiceProvider;
import com.zensar.payroll.services.PayrollServices;

public class MainClass {

	public static void main(String[] args) {
		
		PayrollServices payrollServices;
		do {
			System.out.println("1. Insert associate ");
			System.out.println("2. Net Salary");
			System.out.println("3. Get associate details");
			System.out.println("4. Get all associates");
			System.out.println("5. Exit");
			System.out.println("Enter your choice");
			Scanner sc=new Scanner(System.in);		
			int choice = sc.nextInt();
			try{
				payrollServices = ServiceProvider.getPayrollServices();	
				switch(choice) {
					case 1:
						System.out.println("enter firstname:");
						String firstName = sc.next();
						System.out.println("enter lastname:");
						String lastName = sc.next();
						System.out.println("enter emailId:");
						String emialId = sc.next();
						System.out.println("enter department:");
						String department = sc.next();
						System.out.println("enter desigantion:");
						String designation = sc.next();
						System.out.println("enter pannumber:");
						String pancard = sc.next();
						System.out.println("enter yearly investment:");
						int yearlyInvestmentUnder8oC = sc.nextInt();
						System.out.println("enter basic salary:");
						int basicSalary = sc.nextInt();
						System.out.println("enter account no:");
						int accountNo = sc.nextInt();
						System.out.println("enter bank name:");
						String bankName = sc.next();
						System.out.println("enter ifsc code:");
						String ifscCode = sc.next();
						System.out.println(payrollServices.acceptAssociateDetails(firstName, lastName, emialId, department, designation, pancard, yearlyInvestmentUnder8oC, basicSalary, accountNo, bankName, ifscCode));
						break;
					case 2:
						System.out.println("enter associate id");
						int associateId = sc.nextInt();
						System.out.println(payrollServices.calaculateNetSalary(associateId));
						break;
					case 3:
						System.out.println("enter associate id");
						int associateId1 = sc.nextInt();
						System.out.println(payrollServices.getAssociateDetails(associateId1));
						break;
					case 4:
						for(Associate associate : payrollServices.getAllAssociateDetails()) 
							System.out.println(associate);
						break;
					case 5:
						System.exit(0);
				}	
			} catch (AssociateDetailsNotFoundException e) {
				System.out.println(e);
			} catch (PayrollServicesDownException e) {
				e.printStackTrace();
				//System.out.println(e.getMessage());
			}
		}while(true);	
	}
}
