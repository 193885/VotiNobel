package it.polito.tdp.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model model = new Model();
		List <Esame> soluzione = model.calcolaSottoinsiemeEsami(22);
		
		
		for(Esame e : soluzione)
			
			System.out.println(e);
	}

}
