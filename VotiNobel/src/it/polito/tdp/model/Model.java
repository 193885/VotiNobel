package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.dao.EsameDAO;

public class Model {
	
	private List <Esame> esami;
	private EsameDAO edao;
	private List <Esame> soluzione; 
	private double bestAvg;
	
	
	public Model () {
		
		edao= new EsameDAO();
		esami= edao.getTuttiEsami();
	}

	public List<Esame> calcolaSottoinsiemeEsami(int numeroCrediti) {
		
		soluzione = new ArrayList<>();
		
		bestAvg = 0;
		
		int step =0;
		
		List <Esame> parziale = new ArrayList<>();
		
		recursive(step, parziale, numeroCrediti);
		
		return soluzione;
	}

	private void recursive(int step, List<Esame> parziale, int numeroCrediti) {
		
		if( totCrediti(parziale) > numeroCrediti ) { //condizione uscita ricorsione
					
			return;
			
		}
		
		if(totCrediti(parziale) == numeroCrediti) { //condizione trovare soluzione alla ricorsione
			
			if( avg(parziale) > bestAvg) {
				
				bestAvg=avg(parziale);
				
				soluzione = new ArrayList<>(parziale);
			}
		}
		
		for (Esame e : esami) {
			
			if(!parziale.contains(e)) {
				
				parziale.add(e);
				
				recursive(step+1, parziale, numeroCrediti);
				
				parziale.remove(e);
				
			}
		}
	}

	private double avg(List<Esame> parziale) {
		
		double avg=0;
		
		for (Esame e : parziale)
			
			avg += e.getCrediti() * e.getVoto();
		
		avg /= this.totCrediti(parziale);
		
		return avg;
	}

	private int totCrediti(List<Esame> parziale) {
		
		int somma=0;
		
		for( Esame e : parziale)
			
			somma+= e.getCrediti();
		
		return somma;
	}
}
