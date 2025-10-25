package no.hvl.dat108.oblig4.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Deltager {

	@NotBlank(message = "Du må skrive et navn")
	@Pattern(regexp = "^[A-ZÆØÅ][A-Za-zÆØÅæøå\\- ]{1,19}$",
			message = "Fornavn må ha mellom 2-20 tegn, stor første bokstav")
	private String fornavn;
	
	@NotBlank(message = "Du må skrive et etternavn")
	@Pattern(regexp = "^[A-ZÆØÅ][A-Za-zÆØÅæøå\\-]{1,19}$",
			message = "Etternavn må ha mellom 2–20 tegn, ingen mellomrom, og stor første bokstav")
	private String etternavn;
	
	@NotBlank(message = "Deltager må ha et tlf - nummer")
	@Pattern(regexp = "^[0-9]{8}$", message = "Må være eksakt 8 siffer")
	private String tlfNummer;
	
	@NotBlank(message = "Deltager må ha et passord")
	@Size(min = 4, max = 8, message = "Passord må være mellom 4 og 8 tegn")
	private String passord;
	
	@NotBlank(message = "Deltager må ha et kjønn")
	@Pattern(regexp = "^(mann|kvinne)$", message = "Finnes kun to kjønn kompis...")
	private String kjonn;
	
	private String repetertPassord;

	public Deltager() { }

	public Deltager(String fornavn,String etternavn,String tlfNummer, String passord, String kjonn) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.tlfNummer = tlfNummer;
		this.passord = passord;
		this.kjonn = kjonn;
	}

	public String getFornavn() {
		return fornavn;
	}

	public void setFornavn(String fornavn) {
		this.fornavn = fornavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public String getTlfNummer() {
		return tlfNummer;
	}

	public void setTlfNummer(String tlfNummer) {
		this.tlfNummer = tlfNummer;
	}

	public String getPassord() {
		return passord;
	}

	public void setPassord(String passord) {
		this.passord = passord;
	}

	public String getKjonn() {
		return kjonn;
	}

	public void setKjonn(String kjonn) {
		this.kjonn = kjonn;
	}
	
	public String getRepetertPassord() { 
		return repetertPassord; 
	}
	
	public void setRepetertPassord(String repetertPassord) {
		this.repetertPassord = repetertPassord; 
	}

	public boolean passordMatcher() {
		
		return passord != null && passord.equals(repetertPassord);
	}
	
	@Override
	public String toString() {
	    return String.format("%s %s (%s)", fornavn, etternavn, tlfNummer);
	}

	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Deltager)) return false;
	    Deltager d = (Deltager) o;
	    return tlfNummer.equals(d.tlfNummer);
	}

	@Override
	public int hashCode() {
	    return tlfNummer.hashCode();
	}

	
	
	
}
