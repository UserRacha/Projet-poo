package application;


public class Vendeur extends Client{
     

	//attributs propres a un client vendeur
	private String typePay;   //type de payement
    private String Etat ;
	
	public Vendeur(String nom,String prenom,String adresse,String NumtelM,String Num_id,String typePay , String Etat) {
		super(nom,prenom,adresse,NumtelM,Num_id);
		this.typePay = typePay;
		this.Etat = Etat ; 
	}
	public String getEtat() {
		return Etat;
	}
	public void setEtat(String etat) {
		Etat = etat;
	}
	// constructeur sans parametre 
	public Vendeur() {		
		super();
	}
	
	//getteurs et setteurs
	public String getTypePay() {
		return typePay;
	}
	public void setTypePay(String typePay) {
		this.typePay = typePay;
	}
	

	//redefinition de la classe toString
	public String toString() {
		
		return super.toString()+"\nType payement:"+typePay;
	}	
}
