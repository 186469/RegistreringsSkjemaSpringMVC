package no.hvl.dat108.oblig4.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import no.hvl.dat108.oblig4.model.Deltager;

@Service
public class DeltagerService {

	private List<Deltager> deltagere = new ArrayList<>();
	
	public DeltagerService() {
		
		// Test data
        deltagere.add(new Deltager("Ola", "Nordmann", "12345678", "hemmelig", "mann"));
        deltagere.add(new Deltager("Kari", "Nordmann", "87654321", "hemmelig", "kvinne"));
    }
	
	public List<Deltager> hentAlleSortert(){
		
		return deltagere.stream()
				.sorted(Comparator.comparing(Deltager::getFornavn)
						.thenComparing(Deltager::getEtternavn))
				.toList();
	}
	
	public boolean leggTil(Deltager del) {
	    if (del == null) {
	        System.out.println("❌ leggTil: del er null");
	        return false;
	    }
	    if (del.getTlfNummer() == null) {
	        System.out.println("❌ leggTil: tlfNummer er null");
	        return false;
	    }

	    String mobil = del.getTlfNummer();
	    if (mobil.isEmpty()) {
	        System.out.println("❌ leggTil: mobil er tom streng");
	        return false;
	    }
	    if (finnesMobil(mobil)) {
	        System.out.println("❌ leggTil: mobil finnes allerede: " + mobil);
	        return false;
	    }

	    deltagere.add(del);
	    System.out.println("✅ NY DELTAGER LAGT TIL: " + del);
	    System.out.println("Totalt antall nå: " + deltagere.size());
	    return true;
	}


	
	public boolean finnesMobil(String mobil) {
		
		if(mobil == null) return false;
		
		return deltagere.stream()
				.anyMatch(n -> n.getTlfNummer().equals(mobil));
	}
	
	public int antallDeltagere() {
		
		return deltagere.size();
	}
}
