package com.zensar.payroll.services;

import java.util.List;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.exceptions.AssociateDetailsNotFoundException;
import com.zensar.payroll.exceptions.PayrollServicesDownException;

public interface PayrollServices {

	public int acceptAssociateDetails(String firstName, String lastName,
			String emialId, String department, String designation,
			String pancard, int yearlyInvestmentUnder8oC, int basicSalary,
			int accountNo, String bankName, String ifscCode) throws PayrollServicesDownException;

	int calaculateNetSalary(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException, PayrollServicesDownException;

	List<Associate> getAllAssociateDetails() throws PayrollServicesDownException;
}
