package application;


public class Bailleur extends Client{
	

	private Double Montant_max;  // buget max
	private int dure_loc; 
	private String etat ;
	private String Type_b;
	private Double superficie ;
	private String localisation ;
	private String preference ;

	
	public Bailleur(String nom,String prenom,String adresse,String NumtelM,String Num_id,Double Montant_max,int dure_loc,String etat,String Type_b,Double superficie,String localisation,String preference)
	{   
		super(nom,prenom,adresse,NumtelM,Num_id);
		this.Montant_max =Montant_max ;
		this.dure_loc = dure_loc;
		this.etat = etat ;
		this.Type_b = Type_b ;
		this.superficie = superficie ;
		this.localisation = localisation ;
		this.preference = preference ;
		this.localisation = localisation ;
	}
    public String getPreference() {
		return preference;
	}
	public void setPreference(String preference) {
		this.preference = preference;
	}
	public Bailleur()
    {
    	super();
    }

	public Double getMontant_max() {
		return Montant_max;
	}


	public void setMontant_max(Double montant_max) {
		Montant_max = montant_max;
	}


	public int getDure_loc() {
		return dure_loc;
	}


	public void setDure_loc(int dure_loc) {
		this.dure_loc = dure_loc;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getType_b() {
		return Type_b;
	}


	public void setType_b(String nouveauTypeB) {
		Type_b = nouveauTypeB;
	}


	public Double getSuperficie() {
		return superficie;
	}


	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}


	public String getLocalisation() {
		return localisation;
	}


	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}


	public String toString() {
		return super.toString()+"\n   Montant_max: " + Montant_max + "\n   dure_loc :" + dure_loc + ", etat :" + etat 
				+ "\n   Type_b : " + Type_b + "\n   superficie : " + superficie + "\n   localisation : " + localisation
				  ;
	}
   

}
