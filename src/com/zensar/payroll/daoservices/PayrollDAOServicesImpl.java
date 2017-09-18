package com.zensar.payroll.daoservices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zensar.payroll.beans.Associate;
import com.zensar.payroll.beans.BankDetails;
import com.zensar.payroll.beans.Salary;
import com.zensar.payroll.exceptions.PayrollServicesDownException;
import com.zensar.payroll.serviceproviders.ServiceProvider;
public class PayrollDAOServicesImpl implements PayrollDAOServices {
	private Connection conn;

	public PayrollDAOServicesImpl() throws PayrollServicesDownException{
		conn = ServiceProvider.getDBConnection();
	}

	@Override
	public int insertAssociate(Associate associate) throws SQLException {
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into Associate (yearlyInvestmentUnder8oC,firstName,lastName,department,designation,pancard,emailId) values(?,?,?,?,?,?,?)");
			pstmt.setInt(1, associate.getYearlyInvestmentUnder8oC());
			pstmt.setString(2, associate.getFirstName());
			pstmt.setString(3, associate.getLastName());
			pstmt.setString(4, associate.getDepartment());
			pstmt.setString(5, associate.getDesignation());
			pstmt.setString(6, associate.getPancard());
			pstmt.setString(7, associate.getEmailId());
			pstmt.executeUpdate();
			ResultSet rs =conn.prepareStatement("select max(associateId)  from Associate").executeQuery();
			rs.next(); 
			int associateId =rs.getInt(1); 
			
			pstmt = conn.prepareStatement("insert into Salary(associateId,basicSalary)values(?,?)");
			pstmt.setInt(1, associateId);
			pstmt.setInt(2, associate.getSalary().getBasicSalary());
			//pstmt.setInt(3, associate.getSalary().getEpf());
			//pstmt.setInt(4, associate.getSalary().getCompanyPf());
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("insert into BankDetails(associateId,accountNo,bankName, ifscCode)values(?,?,?,?)");
			pstmt.setInt(1, associateId);
			pstmt.setInt(2, associate.getBankDetails().getAccountNo());
			pstmt.setString(3, associate.getBankDetails().getBankName());
			pstmt.setString(4, associate.getBankDetails().getIfscCode());
			pstmt.executeUpdate();
			conn.commit();
			return associateId;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally{
			conn.setAutoCommit(true);
		}
	}

	@Override
	public boolean updateAssociate(Associate associate) throws SQLException {
		try {
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update Salary set basicSalary=?,epf=?,companyPf=?"+
											", hra=?, conveyanceAllowance=?, otherAllowance=?,"
											+ "personalAllowance=?,"
											+ "monthlyTax=?,gratuity=?,"
											+ "grossSalary=?,netSalary=? where associateId="+associate.getAssociateId());
			pstmt.setInt(1, associate.getSalary().getBasicSalary());
			pstmt.setInt(2, associate.getSalary().getEpf());
			pstmt.setInt(3, associate.getSalary().getCompanyPf());
			pstmt.setInt(4, associate.getSalary().getHra());
			pstmt.setInt(5, associate.getSalary().getConveyanceAllowance());
			pstmt.setInt(6, associate.getSalary().getOtherAllowance());
			pstmt.setInt(7, associate.getSalary().getPersonalAllowance());
			pstmt.setInt(8, associate.getSalary().getMonthlyTax());
			pstmt.setInt(9, associate.getSalary().getGratuity());
			pstmt.setInt(10, associate.getSalary().getGrossSalary());
			pstmt.setInt(11, associate.getSalary().getNetSalary());
			
			pstmt.executeUpdate();
			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
		}
	}

	@Override
	public boolean deleteAssociate(int associateId) throws SQLException {
		Associate associate = this.getAssociate(associateId);
		if (associate != null) {
			PreparedStatement pstmt = conn.prepareStatement("delete from Associate where associateId=" +associateId);
			pstmt.executeUpdate();
			return true;
		}
		return false;
	}

	@Override
	public Associate getAssociate(int associateId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("select * from Associate a, Salary s, BankDetails b where a.associateId = s.associateId and s.associateId = b.associateId and  b.associateId=" + associateId);
		//pstmt.setInt(1, associateId);
		ResultSet rs= pstmt.executeQuery();
		
		if (rs.next()) 
			return new Associate(rs.getInt("associateId"), rs.getInt("yearlyInvestmentUnder8oC"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("department"), rs.getString("designation"), rs.getString("pancard"), rs.getString("emailId"), new Salary(rs.getInt("basicSalary"), rs.getInt("hra"), rs.getInt("conveyanceAllowance"), rs.getInt("otherAllowance"), rs.getInt("personalAllowance"), rs.getInt("monthlyTax"), rs.getInt("epf"), rs.getInt("companyPf"), rs.getInt("gratuity"), rs.getInt("grossSalary"), rs.getInt("netSalary")), new BankDetails(rs.getInt("accountNo"), rs.getString("bankName"), rs.getString("ifscCode")));
			/*if(associateRs.next()){
			Associate associate = new Associate(associateRs.getInt("associateId"), associateRs.getInt("yearlyInvestmentUnder80C"), associateRs.getString("firstName"), associateRs.getString("lastName"), associateRs.getString("department"), associateRs.getString("designation"), associateRs.getString("pancard"), associateRs.getString("emailId"));
			pstmt = conn.prepareStatement("select * from Salary where associateId=?");
			pstmt.setInt(1, associateId);
			ResultSet salaryRs = pstmt.executeQuery();
			associate.setSalary(new Salary(salaryRs.getInt("basicSalary"), salaryRs.getInt("hra"), salaryRs.getInt("conveyanceAllowance"), salaryRs.getInt("otherAllowances"),salaryRs.getInt("personalAllowances") , salaryRs.getInt("monthlyTax"), salaryRs.getInt("epf"), salaryRs.getInt("companyPf"), salaryRs.getInt("gratuity"), salaryRs.getInt("grossSalary"), salaryRs.getInt("netSalary")));
			pstmt = conn.prepareStatement("select * from BankDetails where associateId=?");
			pstmt.setInt(1, associateId);
			ResultSet bankDetailsRs = pstmt.executeQuery();
			associate.setBankDetails(new BankDetails(bankDetailsRs.getInt("accountNo"), bankDetailsRs.getString("bankName"), bankDetailsRs.getString("ifscCode")));
			return associate;
		}*/
		return null;
	}

	@Override
	public List<Associate> getAssociates() throws SQLException {
		List<Associate> associateList = new ArrayList<>();
		
		ResultSet rs = conn.prepareStatement("select * from Associate a , Salary s , BankDetails b  "
				+ "where a.associateId=s.associateId and  "
				+ "a.associateId=b.associateId").executeQuery();
		while(rs.next())
			associateList.add(new Associate(rs.getInt("associateId"), rs.getInt("yearlyInvestmentUnder8oC"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("department"), rs.getString("designation"), rs.getString("pancard"), rs.getString("emailId"), new Salary(rs.getInt("basicSalary"), rs.getInt("hra"), rs.getInt("conveyanceAllowance"), rs.getInt("otherAllowance"), rs.getInt("personalAllowance"), rs.getInt("monthlyTax"), rs.getInt("epf"), rs.getInt("companyPf"), rs.getInt("gratuity"), rs.getInt("grossSalary"), rs.getInt("netSalary")), new BankDetails(rs.getInt("accountNo"), rs.getString("bankName"), rs.getString("ifscCode"))));
		return associateList;
	}

}
