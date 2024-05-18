package application;


public class AgentIMM {
	    private String nom;
	    private String prenom;
	    private String id;
	    private String telephone;
	    private String adresse;
	    private String password;

	    public AgentIMM(String nom, String prenom, String id, String telephone, String adresse, String password) {
	        this.nom = nom;
	        this.prenom = prenom;
	        this.id = id;
	        this.telephone = telephone;
	        this.adresse = adresse;
	        this.password = password;
	    } 

	    // Getters and Setters
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

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id=id;
	    }

	    public String getTelephone() {
	        return telephone;
	    }

	    public void setTelephone(String telephone) {
	        this.telephone = telephone;
	    }

	    public String getAdresse() {
	        return adresse;
	    }

	    public void setAdresse(String adresse) {
	        this.adresse= adresse;
	    }
	    
	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	}

