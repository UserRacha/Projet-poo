package application;


public class Locataire extends Client{
   

    
	private String etat;
	private int dureloc_prevu; 
	
	public Locataire(String nom,String prenom,String adresse,String NumtelM,String Num_id,String etat,int dureloc_prevu)
	{   
		super(nom,prenom,adresse,NumtelM,Num_id); // probleme 
		this.etat = etat ;
		this.dureloc_prevu= dureloc_prevu ;
	}

    public void afficherL()
    {   
    	super.toString();
    	System.out.println("Etat de locataire :"+etat);
    	System.out.println("La duree de location prevue :"+dureloc_prevu);
   
    }
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public int getDureloc_prevu() {
		return dureloc_prevu;
	}
	public void setDureloc_prevu(int dureloc_prevu) {
		this.dureloc_prevu = dureloc_prevu;
	}
	public Locataire()
    {
    	super();
    }
}
