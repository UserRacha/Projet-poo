package application;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
	
	private static int nbcode = 0 ;
	private int Code ;
	private String typetran ;  // location / vente
	private LocalDate date ;
	private String membre1;// vendeur / locatire
	private String membre2; // acheteur / bailleur 
	private double Bénéfice;
	private int id_bien;

    public Transaction(String typetran, LocalDate date, String membre1, String membre2, double bénéfice , int id_bien) {
        this.typetran = typetran;
        this.date = date;
        this.membre1 = membre1;
        this.membre2 = membre2;
        this.Bénéfice = bénéfice;
        this.id_bien = id_bien;
        nbcode++;
        this.Code = nbcode ;
        
    }

	public int getId_bien() {
		return id_bien;
	}

	public void setId_bien(int id_bien) {
		this.id_bien = id_bien;
	}

	public int getCode() {
		return Code;
	}

	public void setMembre1(String membre1) {
		this.membre1 = membre1;
	}

	public void setMembre2(String membre2) {
		this.membre2 = membre2;
	}

	public String getTypetran() {
		return typetran;
	}

	public void setTypetran(String typetran) {
		this.typetran = typetran;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getMembre1() {
		return membre1;
	}

	public String getMembre2() {
		return membre2;
	}

	public double getBénéfice() {
		return Bénéfice;
	}

	public void setBénéfice(double bénéfice) {
		Bénéfice = bénéfice;
	}
    
	
}
