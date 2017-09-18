package com.zensar.payroll.test;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.beans.BankDetails;
import com.zensar.payroll.beans.Salary;
import com.zensar.payroll.daoservices.PayrollDAOServicesImpl;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.services.PayrollServices;
import com.zensar.payroll.services.PayrollServicesImpl;

public class PayrollServicesTest {
	private static PayrollServices payrollServices ;
	@BeforeClass
	public static void setUpTestEnv() throws PayrollServicesDownException{
		payrollServices= new PayrollServicesImpl();
	}
	/*@Before 
	public void  setUpTestData(){
		PayrollDAOServicesImpl.associates.put(101, new Associate(101, 10000, "Satish", "Mahajan", "Training", "Trainer", "VUKUG65", "satish@gmail.com", new Salary(20000, 8000, 6000, 0, 6000, 1296, 2400, 1800, 3600, 44200, 38704), new BankDetails(111, "ICICI", "ljklh53")));
		PayrollDAOServicesImpl.ASSOCIATE_ID_COUNTER++;
	}

	@Test(expected=AssociateDetailsNotFoundException.class)
	public void testGetAssociateDetailsForInvalidId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		payrollServices.getAssociateDetails(1765);
	}

	@Test
	public void testGetAssociateDetailsForValidId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		Associate expectdAssociate = new Associate(101, 10000, "Satish", "Mahajan", "Training", "Trainer", "VUKUG65", "satish@gmail.com", new Salary(20000, 8000, 6000, 0, 6000, 1296, 2400, 1800, 3600, 44200, 38704), new BankDetails(111, "ICICI", "ljklh53"));
		Associate actualAssociate= payrollServices.getAssociateDetails(101);
		Assert.assertEquals(expectdAssociate, actualAssociate);
	}	

	@Test(expected=AssociateDetailsNotFoundException.class)
	public void testCalculateNetSalaryForInvalidAssociateId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		payrollServices.calaculateNetSalary(162);
	}

	@Test
	public void testCalculateNetSalaryForValidAssociateId() throws AssociateDetailsNotFoundException, PayrollServicesDownException{
		int expectdNetSal = 38704;
		int actualNetSalary=payrollServices.calaculateNetSalary(101);
		Assert.assertEquals(expectdNetSal	, actualNetSalary);
	}

	@Test
	public void testAcceptAssociateDetailsForValidData() throws PayrollServicesDownException{
		int expectdIAssociateId =102;
		int actualAssociateId=payrollServices.acceptAssociateDetails("Kumar", "LastName", "satish@gmail.com", "Training", "Traininer", "khikmd", 16353, 10000, 43, "ICIC", "jhdhd63");
		Assert.assertEquals(expectdIAssociateId	, actualAssociateId);
	}

	@Test
	public  void testGetAllAssociateDetails() throws PayrollServicesDownException{
		ArrayList<Associate>expectdList= new ArrayList<>();
		expectdList.add(new Associate(101, 10000, "Satish", "Mahajan", "Training", "Trainer", "VUKUG65", "satish@gmail.com", new Salary(20000, 8000, 6000, 0, 6000, 1296, 2400, 1800, 3600, 44200, 38704), new BankDetails(111, "ICICI", "ljklh53")));
	
		ArrayList<Associate>actualList= (ArrayList<Associate>) payrollServices.getAllAssociateDetails();
		
		Collections.sort(expectdList, new AssociateComparator());
		Collections.sort(actualList,new AssociateComparator());
				
		Assert.assertEquals(expectdList	, actualList);
		
	}

	@After
	public void tearDownTestData(){
		PayrollDAOServicesImpl.associates.clear();
		PayrollDAOServicesImpl.ASSOCIATE_ID_COUNTER=101;
	}*/

	@AfterClass
	public static void tearDownTestEnv(){
		payrollServices=null;
	}

}
