package com.controlstock.helpers;

import java.util.Comparator;

import com.controlstock.entities.Batch;

//CREO que no esta en uso
public class DateBatchComparator implements Comparator<Batch>{
	
	public int compare(Batch o1, Batch o2) {
		Batch batch1 =(Batch) o1;
		Batch batch2 =(Batch) o2;
		return batch1.getAdmissionDate().compareTo(batch2.getAdmissionDate());
	}
}