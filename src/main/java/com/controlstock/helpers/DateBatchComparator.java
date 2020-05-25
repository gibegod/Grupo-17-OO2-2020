package com.controlstock.helpers;

import java.util.Comparator;

import com.controlstock.models.BatchModel;

public class DateBatchComparator implements Comparator<BatchModel>{
	
	public int compare(BatchModel o1, BatchModel o2) {
		BatchModel batch1 =(BatchModel) o1;
		BatchModel batch2 =(BatchModel) o2;
		return batch1.getAdmissionDate().compareTo(batch2.getAdmissionDate());
	}
}