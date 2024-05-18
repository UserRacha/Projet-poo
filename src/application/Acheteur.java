package application;


public class Acheteur extends Client {
	

	//attributs propres a un client achteur
		private double montMax;   //budget maximum, prix maximum du bien immobilier a chercher
		private String typePay;   //type du payemant
		private String typeBienD;    //type du bien immobilier desire (a chercher)
		private double superficieBD;  // superficie du bien desire
		private String localBD; //localisation du bien desire
		private String prefenrence;
		private String etat ;
		//*******private BienIM Bien;
		
		//constructeurs
		public Acheteur(String nom,String prenom,String adresse,String NumtelM,String Num_id,double montMax, String typay,String type,double superficieBD,String localBD ,String prefenrence, String etat) {
			super(nom,prenom,adresse,NumtelM,Num_id);
			this.montMax=montMax;
			this.typePay=typay;
			this.typeBienD=type;
			this.superficieBD=superficieBD;
			this.localBD=localBD;
			this.prefenrence = prefenrence;
			this.etat = etat;
		}
		
		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

		public String getPrefenrence() {
			return prefenrence;
		}

		public void setPrefenrence(String prefenrence) {
			this.prefenrence = prefenrence;
		}

		//getteurs et setteurs
		public double getMontMax() {
			return montMax;
		}
		public void setMontMax(double montMax) {
			this.montMax = montMax;
		}
		
		public String getTypePay() {
			return typePay;
		}
		public void setTypePay(String typePay) {
			this.typePay = typePay;
		}
			
		public String getTypeBienD() {
			return typeBienD;
		}
		public void setTypeBienD(String typeBienD) {
			this.typeBienD = typeBienD;
		}
		
		public double getSuperficieBD() {
			return superficieBD;
		}
		public void setSuperficieBD(double superficieBD) {
			this.superficieBD = superficieBD;
		}
		
		public String getLocalBD() {
			return localBD;
		}
		public void setLocalBD(String localBD) {
			this.localBD = localBD;
		}
		
		//redefinition du methode toString()
		public String toString() {
			return super.toString()+"\nMontant maximum : "+montMax+"\nType payement:"+typePay+"\nType bien voulu : "+typeBienD+"\nsuperficie : "+superficieBD+"\nlocalisation : "+localBD+"\npreference : "+prefenrence;
		}

	
}
