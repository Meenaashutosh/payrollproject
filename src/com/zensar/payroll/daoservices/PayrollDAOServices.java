package com.zensar.payroll.daoservices;

import java.sql.SQLException;
import java.util.List;

import com.zensar.payroll.beans.Associate;

public interface PayrollDAOServices {

	int insertAssociate(Associate associate)throws SQLException;
	boolean updateAssociate(Associate asociate)throws SQLException;
	boolean deleteAssociate(int associateId)throws SQLException;
	Associate getAssociate(int associateId)throws SQLException;
	List<Associate> getAssociates()throws SQLException;
}
