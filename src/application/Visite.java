package application;

import java.time.LocalDate;


public class Visite {
	static int nb = 0 ;
    private int idBien; // Identifiant du bien immobilier à visiter
    private LocalDate date; // Date et heure de la visite
    private String idClient; // Nom du locataire qui effectue la visite
    private String visiteFaite;// Indique si la visite a été faite ou non
    private int code ;
    
    // Constructeur
    public Visite(int idBien, LocalDate date, String idClient ,String visite) {
        this.idBien = idBien;
        this.date = date;
        this.idClient = idClient;
        this.visiteFaite = visite; // Par défaut, la visite n'est pas faite lors de la création
        nb++;
        this.code = nb ;
    }

    // Getters et setters
    public int getIdBien() {
        return idBien;
    }

    public void setIdBien(int idBien) {
        this.idBien = idBien;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCode() {
		return code;
	}


	public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public String getVisiteFaite() {
        return visiteFaite;
    }

    public void setVisiteFaite(String visiteFaite) {
        this.visiteFaite = visiteFaite;
    }



}
