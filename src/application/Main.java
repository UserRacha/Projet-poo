package application;
	


import java.time.LocalDate;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	static ObservableList<Bailleur> bailleurs = FXCollections.observableArrayList();
	static ObservableList<Client> Clients = FXCollections.observableArrayList();
	static ObservableList<BienIM> Biens = FXCollections.observableArrayList();
	static ObservableList<Acheteur> achteurs = FXCollections.observableArrayList();
	static ObservableList<Locataire> locataires = FXCollections.observableArrayList();
	static ObservableList<Vendeur> vendeurs = FXCollections.observableArrayList();	
	static ObservableList<Vendeur> agents = FXCollections.observableArrayList();
	static ObservableList<Transaction> Transactions = FXCollections.observableArrayList();
	static ObservableList<Visite> Visite = FXCollections.observableArrayList();
	static ObservableList<AgentIMM> listeAgents = FXCollections.observableArrayList();
	
	
	// ajouter des information existant( des exemple ) 
       public static void ajoute()
       {
    	   Bailleur bailleur1 = new Bailleur("Dupont", "Jean", "1 rue des Bailleurs", "0123456789", "201", 100000.0, 12, "EN ATTENTE", "APPARTEMENT", 80.0, "Quartier A", "********");
           Bailleur bailleur2 = new Bailleur("Durand", "Marie", "2 avenue des Propriétaires", "0987654321", "002", 150000.0, 24, "SATISFAIT", "MAISON", 120.0, "Quartier B", "**********");
           Bailleur bailleur3 = new Bailleur("Martin", "Pierre", "3 rue des Investisseurs", "0123456789", "003", 80000.0, 6, "ANNULER", "LOCAL", 50.0, "Quartier C", "*****");
           
           bailleurs.addAll(bailleur1,bailleur2,bailleur3);
           
           Acheteur acheteur1 = new Acheteur("Dubois", "Sophie", "4 impasse des Acheteurs", "0123456789", "101", 200000.0, "CASH", "APPARTEMENT", 90.0, "Quartier D", "Familial", "EN ATTENTE");
           Acheteur acheteur2 = new Acheteur("Leclerc", "Thomas", "5 rue des Futurs Propriétaires", "0987654321", "102", 250000.0, "CREDIT BANCIARE", "MAISON", 150.0, "Quartier B", "Moderne", "EN ATTENTE");
           achteurs.addAll(acheteur1,acheteur2) ;
           
      // Instanciation des locataires
           Locataire locataire1 = new Locataire("Leroy", "Emma", "7 avenue des Locataires", "0123456789", "005", "EN ATTENTE", 12);
           Locataire locataire2 = new Locataire("Fournier", "Louis", "8 rue des Locataires", "0987654321", "106", "SATISFAIT", 6);
           locataires.addAll(locataire1,locataire2);
           
      // Instanciation des vendeurs
           Vendeur vendeur1 = new Vendeur("Petit", "Camille", "9 boulevard des Vendeurs", "0123456789", "301", "CREDIT BANCIARE", "ANNULER");
          // Vendeur vendeur2 = new Vendeur ("saidani","nour","alger" , "0666666666","001","CREDIT BANCIARE","EN ATTENTE");
           vendeurs.addAll(vendeur1);
     
      //Instanciation des biens
           // Instanciation des biens pour les locataires
           BienIM bien1 = new BienIM("MAISON", 85.0, 120000.0, "Quartier A", "**********************", "ACTE_NOTARIE", "LOCATION", "106", "OCCUPER");
           BienIM bien2 = new BienIM("APPARTEMENT", 60.0, 10000.0, "Quartier M", "**********************", "ACTE_NOTARIE", "LOCATION", "005", "OCCUPER");
           // Instanciation des biens pour le vendeur
           BienIM bien3 = new BienIM("MAISON", 150.0, 250000.0, "Quartier E", "********************", "ACTE_NOTARIE", "VENTE", "301", "LIBRE");
           BienIM bien4 = new BienIM("APPARTEMENT", 100.0, 180000.0, "Quartier C", "******************", "ACTE_NOTARIE", "VENTE", "301", "LIBRE");
           Biens.addAll(bien1,bien2,bien3,bien4);
           
           
     //Instanciation des visites          
           LocalDate date1 = LocalDate.of(2024, 5, 3);
           LocalDate date2 = LocalDate.of(2024, 5, 7);
           LocalDate date3 = LocalDate.of(2024, 7, 3);
           LocalDate date4 = LocalDate.of(2024, 6, 4);
           LocalDate date5 = LocalDate.of(2024, 6, 5);
           LocalDate date6 = LocalDate.of(2024, 11, 6);
           Visite visite1 = new Visite(1, date1, "002", "ANNULER");
           Visite visite2 = new Visite(2, date2, "201", "FAITE");
           Visite visite3 = new Visite(1, date4, "002", "FAITE");
           Visite visite4 = new Visite(4, date3, "102", "ANNULE");
           Visite visite5 = new Visite(4, date5, "102", "FAIT");
           Visite visite6 = new Visite(3, date6, "101", "PAS ENCORE");
           
           Visite.addAll(visite1,visite2,visite3,visite4,visite5,visite6);

           Transaction transaction1 = new Transaction("LOCATION", LocalDate.of(2024, 5, 8), "005", "201", 2000.0, 2);
           Transaction transaction2 = new Transaction("VENTE", LocalDate.of(2024, 4, 2), "301", "102", 5000.0, 3);
           Transactions.addAll(transaction1,transaction2);
           
      // Instanciation des agents     
           AgentIMM agent1 = new AgentIMM("BENGANA", "Malak", "434", "0123456789", "23 Main Street", "malak");
           AgentIMM agent2 = new AgentIMM("BOULEAM", "Racha", "522", "0987654321", "456 Elm Street", "racha");
           AgentIMM agent3 = new AgentIMM("BELLAZOUZ", "Nour El Imene", "434", "0123336789", "789 Oak Avenue", "nour");
           AgentIMM agent4 = new AgentIMM("SAIDANI", "Nour", "522", "0976231321", "456 Elm Street", "nour");
           // Ajout des agents à la liste des agents
           listeAgents.addAll(agent1, agent2,agent3,agent4);

           
       }
	
	@Override
	public void start(Stage primaryStage) {
		ajoute();
		try {
			
	        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	        primaryStage.setTitle("Hello World");
            Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setResizable(false);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
