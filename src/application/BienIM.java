package application;


public class BienIM {
	static int nb = 0 ;

    private String type;
    private String etat; // ajout de l'état du bien (vente/location)
    private String tytran;
    private String typeActe;
    
    private double superficie;
    private double prix;
    
    private String localisation;
    private String description;
    private String proprietaire;

    private int identifiantBien; // machi le agent hwa li ydakhlu  // ajout de l'attribut propriétaire
    

    public BienIM(String type, double superficie, double prix, String localisation, String description,
    		String typeActe, String tytran, String proprietaire,String etat) {
        this.type = type;
        this.superficie = superficie;
        this.prix = prix;
        this.localisation = localisation;
        this.description = description;
        this.typeActe = typeActe;
        nb++;
        this.identifiantBien = nb ;
        this.etat = etat;
        this.proprietaire = proprietaire;
        this.tytran = tytran ;
    }

    
 public BienIM() {
		// TODO Auto-generated constructor stub
	}


	// Getters and setters
    public String getTytran() {
		return tytran;
	}

	public void setTytran(String tytran) {
		this.tytran = tytran;
	}
	
    public int getIdentifiantBien() {
        return identifiantBien;
    }

    public void setIdentifiantBien(int identifiantBien) {
        this.identifiantBien = identifiantBien;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeActe() {
        return typeActe;
    }

    public void setTypeActe(String typeActe) {
        this.typeActe = typeActe;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void afficher() {
        System.out.println("	Type de bien : " + type);
        System.out.println("	Superficie : " + superficie + " m²");
        System.out.println("	Prix : " + prix + " DA");
        System.out.println("	Localisation : " + localisation);
        System.out.println("	Description : " + description);
        System.out.println("	Type d'acte : " + typeActe);
        System.out.println("	État du bien : " + etat);
        System.out.println("	Propriétaire : " + proprietaire);
        System.out.println("	Type de transaction : " + tytran);
    }
}

