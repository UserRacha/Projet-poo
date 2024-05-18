package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class controlleurVisite implements Initializable{

    @FXML
    private Button ajoutbt;

    @FXML
    private AnchorPane barbutton;

    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private TableColumn<Visite,Integer> biencolone;

    @FXML
    private TableColumn<Visite, String> clientcolone;

    @FXML
    private TableColumn<Visite, Date> datecolone;

    @FXML
    private Button detbt;

    @FXML
    private ChoiceBox<String> etatVisit;
    String[] t = { "FAITE" , "ANNULE" , "PAS ENCORE"}; 

    @FXML
    private TableColumn<Visite, String> etatcolone;

    @FXML
    private Button modifbt;

    @FXML
    private Button nvbt;

    @FXML
    private TextField searchField;

    @FXML
    private Button suppbt;

    @FXML
    private TableView<Visite> table;

    @FXML
    private TextField txtbien;

    @FXML
    private DatePicker txtdate;

    @FXML
    private TextField txtid;

    
    
  	 //updates des buttons 
      private void updateAjouterButtonState() {
          // Vérifier si tous les champs sont remplis
          boolean champsRemplis = !txtid.getText().isEmpty() &&
                                  !txtbien.getText().isEmpty();
          
          // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
          ajoutbt.setDisable(!champsRemplis);
      }
      
      private void updateModifierButtonState() {

          Visite Selectionne = table.getSelectionModel().getSelectedItem();
      	if (Selectionne != null)
      	{
      		modifbt.setDisable(false);	
      	}else {
              // Sinon, désactiver le bouton "Modifier"
              modifbt.setDisable(true);
          }
      }
      
      private void updateSupprimerButtonState() {

    	  Visite Selectionne  = table.getSelectionModel().getSelectedItem();
      	if (Selectionne  != null)
      	{
      		suppbt.setDisable(false);	
      	}else {
              // Sinon, désactiver le bouton "Modifier"
      		suppbt.setDisable(true);
          }
      }
      
      private void updateAfficherButtonState() {

    	  Visite Selectionne = table.getSelectionModel().getSelectedItem();
      	if (Selectionne != null)
      	{
      		detbt.setDisable(false);	
      	}else {
              // Sinon, désactiver le bouton "Modifier"
      		detbt.setDisable(true);
          }
      }    
      
      public void selectplus() {
          // Récupérer l'élément sélectionné dans la TableView
    	  Visite Selectionne = table.getSelectionModel().getSelectedItem();

          // Vérifier si un élément est sélectionné
          if (Selectionne != null) {
              // Obtenir l'ID du client sélectionné
          	modifbt.setDisable(false);
          	detbt.setDisable(false);	
          	suppbt.setDisable(false);
              for (Visite a : Main.Visite)
              {
            	  etatVisit.setValue(null);
                  etatVisit.setValue(a.getVisiteFaite());
                  txtid.setText(a.getIdClient());
                  txtbien.setText(Integer.toString(a.getIdBien()));
                  LocalDate localDate = a.getDate();
                  txtdate.setValue(localDate);
                  
              }
          } else {
              // Aucun élément sélectionné, gérer le cas en conséquence
              // dialogPane fenetre sera afficher 
                 }
      }
      
  	private void addTextChangeListeners() {
  		 txtid.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
  		 txtdate.accessibleTextProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
  		 txtbien.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
  	}
  	
      // a revoire 
  	private void filterTable(String searchText) {
          ObservableList<Visite> filteredList = FXCollections.observableArrayList();

          if (searchText.isEmpty()) {
              table.setItems(Main.Visite);
              return;
          }
          // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
          for (Visite c : table.getItems()) {
          	LocalDate transactionDate = c.getDate();
          	// pour ajouter la recherche selon le nom ou prenom 
          	String Nom = searchText.toLowerCase();
          	String Prenom = searchText.toLowerCase();
              if (Integer.toString(c.getIdBien()).contains(searchText.toLowerCase()) ||
                  c.getIdClient().toLowerCase().contains(searchText.toLowerCase()) ||
                  transactionDate.toString().contains(searchText) ){
                  filteredList.add(c);
              }
          }

          // Mettre à jour la TableView avec la liste filtrée
          table.setItems(filteredList);
      }
      
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Autres initialisations
        if (Main.Visite.isEmpty() == false)
        {
        	table.setItems(Main.Visite);
        }
        
	    clientcolone.setCellValueFactory(new PropertyValueFactory<Visite, String>("idClient"));
	    etatcolone.setCellValueFactory(new PropertyValueFactory<Visite, String>("visiteFaite"));
	    datecolone.setCellValueFactory(new PropertyValueFactory<Visite, Date>("date"));
	    biencolone.setCellValueFactory(new PropertyValueFactory<Visite, Integer>("idBien"));
	    
	    // Configurer la valeur factory pour le Spinner
	    etatVisit.getItems().addAll(t);
	    etatVisit.setValue("PAS ENCORE");
        txtdate.setValue(LocalDate.now());
        	    
	    table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	            // Si une nouvelle sélection est effectuée, appeler la méthode selectplus()
	            selectplus();
	        } 
	    });
	    
	    //ajouter le lintener pour le textefield de recherche
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterTable(newValue); // Appel de la méthode de filtrage avec le nouveau texte saisi
        });
	    
	    addTextChangeListeners(); // Ajouter les écouteurs de changement de texte
	    updateAjouterButtonState(); // Mettre à jour l'état initial du bouton "Ajouter"
	    updateModifierButtonState();
	    updateSupprimerButtonState();
	    updateAfficherButtonState();
		
	}
        
    
    @FXML
    void afficher(ActionEvent event) {
       boolean fini = false ;
       BienIM q = new BienIM();
        try {
            Visite Selectionne = table.getSelectionModel().getSelectedItem();
            if (Selectionne != null) {             
                String idcient = Selectionne.getIdClient();
                int bien = Selectionne.getIdBien();
                for (BienIM b : Main.Biens) {
                	if (b.getIdentifiantBien() == bien )
                	{	
                		q = b ;
                	}
               }
	                for(Bailleur l : Main.bailleurs)
	                {
	                	if (l.getNum_id().equals(idcient))
	                	{              
	                		FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleVis.fxml"));
	                        Parent root = (Parent) loader.load();
	                        controlleurAffichvis controlleur = loader.getController(); // Obtenir le contrôleur
	                        if(controlleur != null) {
	                            controlleur.afficherinfobien(q , l , Selectionne);
	                            Stage stage = new Stage();
	                            Scene scene = new Scene(root);
	                            stage.setScene(scene);
	                            stage.show();
	                            fini = true ;
	                        }
	                        else {
	                            System.err.println("Erreur : le contrôleur est null");
	                        }
	                	}
	                }
	                if (fini == false)
	                {
		                for(Acheteur e : Main.achteurs )
		                    {
		                    	if (e.getNum_id().equals(idcient))
		                    	{
			                		FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleVis.fxml"));
			                        Parent root = (Parent) loader.load();
		                            controlleurAffichvis controlleur = loader.getController(); // Obtenir le contrôleur
		                            if(controlleur != null) {
		                                controlleur.afficherinfobien(q , e , Selectionne);
		                                Stage stage = new Stage();
		                                Scene scene = new Scene(root);
		                                stage.setScene(scene);
		                                stage.show();
		                    	    }
			                        else {
			                            System.err.println("Erreur : le contrôleur est null");
			                        }
		                            
		                    	}
		                    }
		            }
              }
        } catch (IOException e) {
            e.printStackTrace();
        }
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState(); 
     
    }

    @FXML
    void ajouter(ActionEvent event) {
    	
        String id = txtid.getText();
        String bien  = txtbien.getText();
        String etat  = etatVisit.getValue();
        LocalDate date = txtdate.getValue() ;
        
        if (date.isBefore(LocalDate.now())) {
            verification.showAlert("Erreur", "Date invalide", "Date antérieure à la date actuelle.");
            return;
        }
                
        if (verification.isValidInt(bien) &&
            verification.isBienexicte (Integer.parseInt(bien)) &&
            verification.idExicte1(id) &&
            verification.isValidNumeroIdentite(id)
                )
            { 
           Visite tran = new Visite(Integer.parseInt(bien), date , id , etat);
           Main.Visite.add(tran);        
           table.setItems(Main.Visite);
           ajoutbt.setDisable(true);
            }
    }

    @FXML
    void modifier(ActionEvent event) {
       
 	    int selectedID = table.getSelectionModel().getSelectedIndex();
        Visite Selectionne = table.getSelectionModel().getSelectedItem();
        int index;
        if (!Main.Visite.isEmpty() && Selectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
            // Obtenir l'ID du client sélectionné
            int idTran = Selectionne.getCode();
            for (Visite a :Main.Visite) {
                if (a.getCode() == idTran) {                     
                	index = Main.Visite.indexOf(a);                        
                    
                    String id = txtid.getText();
                    String bien  = txtbien.getText();
                    String etat  = etatVisit.getValue();
                    LocalDate date = txtdate.getValue() ;
                    
                    if ( id.equals(a.getIdClient()) &&
                    	 bien.equals(a.getIdBien()) &&
                    	 etat.equals(a.getVisiteFaite()) &&
                    	 date.equals(a.getDate())		                   
                        // ou on peut faire localDate.equals(a.toLocalDate()) 
                        ) {

                        // Aucune modification n'a été apportée
                        // Afficher une alerte
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Aucune modification n'a été apportée.");
                        alert.showAndWait();
                    } else {
                        if (verification.isValidInt(bien) &&
                                verification.isBienexicte (Integer.parseInt(bien)) &&
                                verification.idExicte1(id) &&
                                verification.isValidNumeroIdentite(id)
                                    )
                                { 
                                a.setDate(date);
                                a.setIdBien(Integer.parseInt(bien));
                                a.setIdClient(id);
                                a.setVisiteFaite(etat);
                       
		                        Main.Visite.set(index, a);
		        	            table.getItems().set(selectedID, a);
		        		        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
		        		        updateModifierButtonState();
		        		        updateSupprimerButtonState();
		        		        updateAfficherButtonState();
                                }
                        
                    }
                    break;
                }
            }
        }

	
    	
    }
    
    @FXML
    void effacer(ActionEvent event) {
        // Effacer le contenu de chaque TextField
        txtid.clear();
        txtbien.clear();
        // Effacer la sélection retourner a la valeur initiale
        txtdate.setValue(LocalDate.now());
        etatVisit.setValue("PAS ENCORE");
        
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }


    @FXML
    void supprimer(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
        Visite Selectionne = table.getSelectionModel().getSelectedItem();
        
        
        // Vérifier si un élément est sélectionné
        if (Selectionne != null) {
           table.getItems().remove(selectedID);
           Main.Visite.remove(Selectionne);

        }
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }

}
