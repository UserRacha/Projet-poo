package application;


public class Client {

    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String NumtelM;
    protected String Num_id;
    
    public Client(String nom,String prenom,String adresse,String NumtelM,String Num_id)
    {
    	this.nom = nom ;
    	this.prenom = prenom ;
    	this.adresse = adresse ;
    	this.NumtelM = NumtelM ;
    	this.Num_id = Num_id ;
    }
    
    public Client()
    {
    	
    }
  
    //methode de modification racha 
    
    public String toString() {
        return "nom : "+nom+"\nprenom : "+prenom+"\nadresse : "+adresse+"\nnumero tel : "+NumtelM+"\nnumero de carte identite :"+Num_id;
    }

	public String getNumtelM() {
		return NumtelM;
	}

	public void setNumtelM(String numtelM) {
		NumtelM = numtelM;
	}

	public String getNum_id() {
		return Num_id;
	}

	public void setNum_id(String num_id) {
		Num_id = num_id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
    

}
