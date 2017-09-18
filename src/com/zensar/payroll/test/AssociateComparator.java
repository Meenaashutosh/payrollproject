package com.zensar.payroll.test;
import java.util.Comparator;
import com.zensar.payroll.beans.Associate;
public class AssociateComparator implements Comparator<Associate> {
	@Override
	public int compare(Associate o1, Associate o2) {
		return o1.getAssociateId()-o2.getAssociateId();
	}

}
