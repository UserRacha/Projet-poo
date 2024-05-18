package application;

import javafx.scene.control.Alert;

import javafx.scene.control.Alert.AlertType;

public class verification {

	    public static boolean isValidNom(String nom) {
	        // Vérifier si le nom contient uniquement des lettres (sans espaces)
	        if (!nom.matches("[a-zA-Z]+")) {
	            showAlert("Erreur", "Nom invalide", "Le nom ne peut contenir que des lettres (sans espaces).");
	            return false;
	        }
	        return true;
	    }

	    public static boolean isValidPrenom(String prenom) {
	        // Vérifier si le prénom contient uniquement des lettres (sans espaces)
	        if (!prenom.matches("[a-zA-Z]+")) {
	            showAlert("Erreur", "Prénom invalide", "Le prénom ne peut contenir que des lettres (sans espaces).");
	            return false;
	        }
	        return true;
	    }

	    public static boolean isValidTelephone(String telephone) {
	        // Vérifier si le numéro de téléphone contient uniquement des chiffres et a une longueur de 10
	        if (!telephone.matches("[0-9]+") ) {
	            showAlert("Erreur", "Numéro de téléphone invalide", "Le numéro de téléphone doit contenir des chiffres.");
	            return false;
	        }
	        else {
	        	if (telephone.length() != 10)
	        	{
	        		
	        		showAlert("Erreur", "Taille fausse", "Le numéro de téléphone doit contenir 10 chiffres.");
	        		return false;
	        	}
	        }
	        return true;

	    }

	    public static boolean isValidNumeroIdentite(String numeroIdentite) {
	        // Vérifier si le numéro d'identité contient uniquement des chiffres
	        if (!numeroIdentite.matches("[0-9]+")) {
	            showAlert("Erreur", "Numéro d'identité n'exicte pas ", "Le numéro d'identité doit contenir uniquement des chiffres.");
	            return false;
	        }
	        return true;
	    }

	    public static boolean isBienexicte(int id) {
	        for (BienIM t : Main.Biens) {
	            if (t.getIdentifiantBien() == id) {
                     return true ;
	            }
	        }

		        showAlert("Erreur", "Numéro d'identité n'existe pas", "Ce bien n'existe pas.");
	        return false;
	    }

	    
	    public static boolean isValidType(int id , String Transaction) {
	    	 for (  BienIM b : Main.Biens)
	    	 {
	    		 if ( b.getIdentifiantBien() != id )
	    		 {
	    			 if (b.getTytran().equals(Transaction) == false)
	    			 {
	    				 showAlert("Erreur", "Mismathing", "Ce bien n'est pas pour ce type de transaction.");
	    				 return false ;
	    			 }
	    			
	    		 }
	    	 }
			return true;
	    }

	    
	    public static boolean isClientWithRole(String clientID, String tran , int a) {
	    	 
	        switch (tran) {
	            case "LOCATION":
	            	if (a == 1) {
		                if( Main.locataires.stream().anyMatch(client -> client.getNum_id().equals(clientID)) == false)
		                {
		                	showAlert("Erreur", "Numéro d'identité invalide", "Ce Locataire n'exicte pas.");
		                	return false;
		                }      
	            	}
	            	if (a == 2) 
	            	{
		                if ( Main.bailleurs.stream().anyMatch(client -> client.getNum_id().equals(clientID)) == false)
		                {
			                 showAlert("Erreur", "Numéro d'identité invalide", "Ce Bailluer n'exicte pas.");
			                 return false;
			              }
	            	}
		            return true;
	            case "VENTE":
	              if ( a == 1)
	              {  if( Main.vendeurs.stream().anyMatch(client -> client.getNum_id().equals(clientID)) == false)
	                {
	                 showAlert("Erreur", "Numéro d'identité invalide", "Ce client Vendeur n'exicte pas.");
	                 return false;
	                }
	              }
	              if (a == 2)
	              {
	                if ( Main.achteurs.stream().anyMatch(client -> client.getNum_id().equals(clientID)) == false)
	                {
		                 showAlert("Erreur", "Numéro d'identité invalide", "Ce client  Acheteur n'exicte pas.");
		                 return false;
		             }
	              }
		          return true;
	            default:
	                return false;
	        }
	    }
	    
	    
	    public static boolean isValidDouble(String input) {
	        // Vérifier si la chaîne peut être convertie en double
	        try {
	            Double.parseDouble(input);
	            return true;
	        } catch (NumberFormatException e) {
	            showAlert("Erreur", "Valeur invalide", "La valeur doit être un nombre(pas de caracete).");
	            return false;
	        }
	    }
	    
	    
	    public static boolean idExicte(String input , String tran) {
	        boolean idExiste = false;
	        if (tran.equals("VENTE")) {
	            for (Vendeur v : Main.vendeurs) {
	                if (v.getNum_id().equals(input)) {
	                    idExiste = true;
	                    break;
	                }
	            }
	        } else if (tran.equals("LOCATION")) {
	            for (Locataire v : Main.locataires) {
	                if (v.getNum_id().equals(input)) {
	                    idExiste = true;
	                    break;
	                }
	            }
	        }
	        if (!idExiste) {
	            showAlert("Erreur", "Valeur n'existe pas", "L'identifiant spécifié n'existe pas.\nvous ne pouvez pas lui affecte un bien.");
	        }
	        return idExiste;
	    }

 	    public static boolean idExicte1(String input) {
	        boolean idExiste = false;
	            for (Bailleur b : Main.bailleurs) {
	                if (b.getNum_id().equals(input)) {
	                    idExiste = true;
	                    break;
	                }
	            }
	           if (!idExiste)
	           {
	            for (Acheteur a : Main.achteurs) {
	                if (a.getNum_id().equals(input)) {
	                    idExiste = true;
	                    break;
	                }
	            }
 	          }
	           
	        if (!idExiste) {
	            showAlert("Erreur", "Valeur n'existe pas", "L'identifiant spécifié n'existe pas.");
	        }
	        return idExiste;
         }
 


	    
	    public static boolean isValidInt(String input) {
	        // Vérifier si la chaîne peut être convertie en double
	        try {
	            Integer.parseInt(input);
	            return true;
	        } catch (NumberFormatException e) {
	            showAlert("Erreur", "Valeur invalide", "La valeur doit être un nombre(pas de caracete).");
	            return false;
	        }
	    }
	    
        public static boolean bienduclient(int code , String id)
        {
		 	   	boolean exict = false ;
		    	boolean libre = false ;
		        for (BienIM t : Main.Biens) {
		            if (t.getIdentifiantBien() == code && t.getProprietaire().equals(id)) {
		                exict = true ;
		                if (t.getEtat().equals("LIBRE"))
		                {
		                	libre = true ;
		                }
		            }
		        }
		        if ( !exict )
		        {
		        	showAlert("Erreur", "Proprietaire faux", "Ce bien n'appartient pas a ce Membre1");
			        
		        }
		        else 
		        {if ( !libre)
			        {
				        showAlert("Erreur", "Bien Occuper", "Ce bien n'est pas libre pour le moment");
				        
			        }
		           else {
		        	   return true;
		           }
		        }
		        return false;
        }
	    
	    // Méthode utilitaire pour afficher une boîte de dialogue d'alerte
	    public static void showAlert(String title, String headerText, String contentText) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle(title);
	        alert.setHeaderText(headerText);
	        alert.setContentText(contentText);
	        alert.showAndWait();
	    }
	    
}


