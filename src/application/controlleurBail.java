	
package application;


import java.io.IOException;

import java.net.URL;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class controlleurBail implements Initializable{


		// le tableau 
	    @FXML
	    private TableColumn<Bailleur, String> adressecolone;
	    @FXML
	    private TableColumn<Bailleur, String> idcolone;
	    @FXML
	    private TableColumn<Bailleur, String> nomcolone;
	    @FXML
	    private TableColumn<Bailleur, String> prenomcolone;
	    @FXML
	    private TableView<Bailleur> table;
	    @FXML
	    private TableColumn<Bailleur, String> telephonecolone;

	    // les buttons
	    @FXML
	    private Button ajoutbt;
	    @FXML
	    private Button detbt;   
	    @FXML
	    private Button modifbt;    
	    @FXML
	    private Button nvbt;
	    @FXML
	    private Button suppbt;

	    
	    //text field
	    @FXML
	    private TextField txtNom;
	    @FXML
	    private TextField txtadresse;
	    @FXML
	    private TextField txtloc;
	    @FXML
	    private TextField txtmontant;
	    @FXML
	    private TextField txtpref;
	    @FXML
	    private TextField txtprenom;
	    @FXML
	    private TextField txtsurf;   
	    @FXML
	    private TextField txtid;
	    @FXML
	    private TextField txttel;
	    @FXML
	    private ChoiceBox<String> txtetat;
	    private String[] e = {"SATISFAIT" , "ANNULER" , "EN ATTENTE"};
	    @FXML
	    private ChoiceBox<String> txttBien;
	    private String[]  b= { 
	            "MAISON",
	            "APPARTEMENT",
	            "LOFT",
	            "IMMEUBLE",
	            "LOCAL",
	            "TERRAIN",
	            "CARCASSE"
	            };
	    @FXML
	    private Spinner<Integer> txtdur;
	    
	    @FXML
	    private TextField searchField;
	  
	 //updates des buttons 
	    private void updateAjouterButtonState() {
	        // Vérifier si tous les champs sont remplis
	        boolean champsRemplis = !txtNom.getText().isEmpty() &&
	                                !txtprenom.getText().isEmpty() &&
	                                !txtadresse.getText().isEmpty() &&
	                                !txttel.getText().isEmpty() &&
	                                !txtid.getText().isEmpty() &&
	                                !txtmontant.getText().isEmpty() &&
	                                !txtsurf.getText().isEmpty() &&
	                                !txtloc.getText().isEmpty() &&
	                                !txtpref.getText().isEmpty();

	        // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
	        ajoutbt.setDisable(!champsRemplis);
	    }
	    private void updateModifierButtonState() {
	        Bailleur clientSelectionne = null;
	    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
	    	if (clientSelectionne != null)
	    	{
	    		modifbt.setDisable(false);	
	    	}else {
	            // Sinon, désactiver le bouton "Modifier"
	            modifbt.setDisable(true);
	        }
	    }
	    
	    private void updateSupprimerButtonState() {
	    	Bailleur clientSelectionne = null;
	    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
	    	if (clientSelectionne != null)
	    	{
	    		suppbt.setDisable(false);	
	    	}else {
	            // Sinon, désactiver le bouton "Modifier"
	    		suppbt.setDisable(true);
	        }
	    }
	    
	    private void updateAfficherButtonState() {
	    	Bailleur clientSelectionne = null;
	    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
	    	if (clientSelectionne != null)
	    	{
	    		detbt.setDisable(false);	
	    	}else {
	            // Sinon, désactiver le bouton "Modifier"
	    		detbt.setDisable(true);
	        }
	    }    

	    public boolean IdUnique(String id) {
	        // Parcourir la liste des acheteurs et vérifier si l'ID existe déjà
	    	for (Bailleur a : Main.bailleurs) {
	            if (a.getNum_id().equals(id)) {
	                return false; 
	            }
	        }
	        return true;
	    }
	    
	    public void selectplus() {
	        // Récupérer l'élément sélectionné dans la TableView
	        Bailleur clientSelectionne = table.getSelectionModel().getSelectedItem();
	        SpinnerValueFactory<Integer> valueFactory;
	        

	        // Vérifier si un élément est sélectionné
	        if (clientSelectionne != null) {
	            // Obtenir l'ID du client sélectionné
	        	modifbt.setDisable(false);
	        	detbt.setDisable(false);	
	        	suppbt.setDisable(false);
	            String idClient = clientSelectionne.getNum_id();
	            for (Bailleur a : Main.bailleurs)
	            {
	            	if (a.getNum_id().equals(idClient))
	            	{
	                    txtNom.setText(a.getNom());
	                    txtprenom.setText(a.getPrenom());
	                    txtadresse.setText(a.getAdresse());
	                    txttel.setText(a.getNumtelM());
	                    txtid.setText(a.getNum_id());
	                    txtmontant.setText(Double.toString(a.getMontant_max()));
	                    txtsurf.setText(Double.toString(a.getSuperficie()));
	                    txtloc.setText(a.getLocalisation());
	                    txtpref.setText(a.getPreference());
	                    // Effacer la sélection et sélectionner les valeurs correspondantes dans les ChoiceBox
	                    txttBien.setValue(null);
	                    txttBien.setValue(a.getType_b());	
	                    txtetat.setValue(null);
	                    txtetat.setValue(a.getEtat());
	                    valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 30, a.getDure_loc());
	                    txtdur.setValueFactory(valueFactory);
	                }
	            }
	        } else {
	            // Aucun élément sélectionné, gérer le cas en conséquence
	            // dialogPane fenetre sera afficher 
	        }
	    }

	    
		private void filterTable(String searchText) {
	        ObservableList<Bailleur> filteredList = FXCollections.observableArrayList();

	        if (searchText.isEmpty()) {
	            table.setItems(Main.bailleurs);
	            return;
	        }
	        // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
	        for (Bailleur c : table.getItems()) {
	            if (c.getNom().toLowerCase().contains(searchText.toLowerCase()) ||
	                c.getPrenom().toLowerCase().contains(searchText.toLowerCase()) ||
	                c.getNum_id().toLowerCase().contains(searchText.toLowerCase())) {
	                filteredList.add(c);
	            }
	        }

	        // Mettre à jour la TableView avec la liste filtrée
	        table.setItems(filteredList);
	    }
	    
	    // methode d'initialisation
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
		    
			if (Main.bailleurs.isEmpty() == false)
			{
				table.setItems(Main.bailleurs);
			}

		    nomcolone.setCellValueFactory(new PropertyValueFactory<Bailleur, String>("nom"));
		    prenomcolone.setCellValueFactory(new PropertyValueFactory<Bailleur, String>("prenom"));
		    adressecolone.setCellValueFactory(new PropertyValueFactory<Bailleur, String>("adresse"));
		    telephonecolone.setCellValueFactory(new PropertyValueFactory<Bailleur, String>("NumtelM"));
		    idcolone.setCellValueFactory(new PropertyValueFactory<Bailleur, String>("Num_id"));
		    // Configurer la valeur factory pour le Spinner
		    txtdur.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1)); // Min: 1, Max: 30, Initial: 1
		    txtetat.getItems().addAll(e);
		    txttBien.getItems().addAll(b);    
		    txtetat.setValue("EN ATTENTE");
		    txttBien.setValue("MAISON");
		    
		    table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		        if (newSelection != null) {
		            // Si une nouvelle sélection est effectuée, appeler la méthode selectplus()
		            selectplus();
		        } 
		    });
		    
	        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filterTable(newValue); // Appel de la méthode de filtrage avec le nouveau texte saisi
	        });
		    
		    addTextChangeListeners(); // Ajouter les écouteurs de changement de texte
		    updateAjouterButtonState(); // Mettre à jour l'état initial du bouton "Ajouter"
		    updateModifierButtonState();
		    updateSupprimerButtonState();
		    updateAfficherButtonState();
		}
	    
		//
		private void addTextChangeListeners() {
		    txtNom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtprenom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtadresse.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txttel.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtid.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtmontant.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtsurf.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtloc.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		    txtpref.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		}
		
		//les verification
		

	   // fonction des buttons
		

	    @FXML
	    // pour le boutton nouveau
	    void effacer(ActionEvent event) {
	        // Effacer le contenu de chaque TextField
	        txtNom.clear();
	        txtprenom.clear();
	        txtadresse.clear();
	        txttel.clear();
	        txtid.clear();
	        txtmontant.clear();
	        txtsurf.clear();
	        txtloc.clear();
	        txtpref.clear();
	        // Effacer la sélection retourner a la valeur initiale
		    txtetat.setValue("EN ATTENTE");
		    txttBien.setValue("MAISON");
	        txtdur.getValueFactory().setValue(1);
	        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
	        updateModifierButtonState();
	        updateSupprimerButtonState();
	        updateAfficherButtonState();
	    }

	   
	    @FXML
	    void modifier(ActionEvent event) {   
	        int selectedID = table.getSelectionModel().getSelectedIndex();
	        Bailleur clientSelectionne = table.getSelectionModel().getSelectedItem();
	        int index;
	        if (!Main.bailleurs.isEmpty() && clientSelectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
	            // Obtenir l'ID du client sélectionné
	            String idClient = clientSelectionne.getNum_id();
	            for (Bailleur a : Main.bailleurs) {
	                if (a.getNum_id().equals(idClient)) {   
	                    index = Main.bailleurs.indexOf(a);                        

	                    // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
	                    String nom = txtNom.getText();
	                    String prenom  = txtprenom.getText();
	                    String tel  = txttel.getText();
	                    String id  = txtid.getText();
	                    String mont = txtmontant.getText();
	                    String surf = txtsurf.getText();

	                    if (nom.equals(a.getNom()) &&
	                        prenom.equals(a.getPrenom()) &&
	                        tel.equals(a.getNumtelM()) &&
	                        mont.equals(String.valueOf(a.getMontant_max())) &&
	                        surf.equals(String.valueOf(a.getSuperficie())) &&
	                        txtadresse.getText().equals(a.getAdresse()) &&
	                        txtloc.getText().equals(a.getLocalisation()) &&
	                        txtpref.getText().equals(a.getPreference()) &&
	                        txttBien.getValue().equals(a.getType_b())) {

	                        // Aucune modification n'a été apportée
	                        // Afficher une alerte
	                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                        alert.setTitle("Information");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Aucune modification n'a été apportée.");
	                        alert.showAndWait();
	                    } else {
	            	        if (verification.isValidNom(nom) && verification.isValidPrenom(prenom) && verification.isValidTelephone(tel) && verification.isValidNumeroIdentite(id)&& verification.isValidDouble(mont) && verification.isValidDouble(surf))
	            	        {
			                        a.setNom(nom);
			                        a.setPrenom(prenom);
			                        a.setNumtelM(tel);
			                        a.setNum_id(id);
			                        a.setMontant_max(Double.parseDouble(txtmontant.getText()));
			                        a.setAdresse(txtadresse.getText());
			                        a.setLocalisation(txtloc.getText());
			                        a.setPreference(txtpref.getText());
			                        a.setSuperficie(Double.parseDouble(surf));
			                        a.setType_b(txttBien.getValue());
		
			                        Main.bailleurs.set(index, a);
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
	    void supprimer(ActionEvent event) {
	        int selectedID = table.getSelectionModel().getSelectedIndex();
	        Bailleur clientSelectionne = table.getSelectionModel().getSelectedItem();
	        
	        
	        // Vérifier si un élément est sélectionné
	        if (clientSelectionne != null) {
	        	table.getItems().remove(selectedID);
	            // Obtenir l'ID du client sélectionné
	            String idClient = clientSelectionne.getNum_id();
	            for (Bailleur a : Main.bailleurs)
	            {
	            	if (a.getNum_id().equals(idClient))
	            	{
	            		Main.bailleurs.remove(clientSelectionne);
	                }
	            }
	        }
	        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
	        updateModifierButtonState();
	        updateSupprimerButtonState();
	        updateAfficherButtonState();
	    }

	    
	    @FXML
	    void ajouter(ActionEvent event) {
	        String nom = txtNom.getText();
	        String prenom  = txtprenom.getText();        
	        String adresse =txtadresse.getText();
	        String tel  = txttel.getText();
	        String id  = txtid.getText();
	        String mont =txtmontant.getText();
	        String surf = txtsurf.getText();
	        String loc  = txtloc.getText();
	        String pref = txtpref.getText();

	        if (verification.isValidNom(nom) && verification.isValidPrenom(prenom) && verification.isValidTelephone(tel) && verification.isValidNumeroIdentite(id)&& verification.isValidDouble(mont) && verification.isValidDouble(surf))
	        {        	 
	        	
	            if (IdUnique(id))
	 		        {        	           	
		               Bailleur bailleur = new Bailleur(nom ,prenom,adresse,tel,id,Double.parseDouble(mont),txtdur.getValue(),txtetat.getValue(),txttBien.getValue(),Double.parseDouble(surf),loc,pref);
		               Main.bailleurs.add(bailleur);        
		               table.setItems(Main.bailleurs);
		               ajoutbt.setDisable(true);
	 		        }
	 		     	else {
	 		         // Afficher un message d'erreur si l'ID n'est pas unique
	 		         Alert alert = new Alert(Alert.AlertType.ERROR);
	 		         alert.setTitle("Erreur");
	 		         alert.setHeaderText(null);
	 		         alert.setContentText("L'identifiant saisi existe déjà. Veuillez saisir un identifiant unique.");
	 		         alert.showAndWait();
	 		        }
	       
	        }
	    }

	        
	    @FXML
	    void afficher(ActionEvent event) {
	        try {
	            Bailleur clientSelectionne = table.getSelectionModel().getSelectedItem();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleB.fxml"));
	            Parent root = (Parent) loader.load();
	            controlleurAffichB controlleur = loader.getController(); // Obtenir le contrôleur
	            if(controlleur != null) {
	                controlleur.afficherinfobien(clientSelectionne);
	                Stage stage = new Stage();
	                Scene scene = new Scene(root);
	                stage.setScene(scene);
	                stage.show();
	            } else {
	                System.err.println("Erreur : le contrôleur est null");
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
	        updateModifierButtonState();
	        updateSupprimerButtonState();
	        updateAfficherButtonState();
	    }
    }


	
	

