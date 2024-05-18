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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ControlleurLoc implements Initializable {
	
    @FXML
    private TableView<Locataire> table;
    @FXML
    private TableColumn<Locataire, String> adressecolone;

    @FXML
    private Button ajoutbt;
    
    @FXML
    private Button ajouterbienbt;

    @FXML
    private Button nvbt;


    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private TableColumn<Locataire,Long> durcolone;

    @FXML
    private TableColumn<Locataire, String> etatcolone;

    @FXML
    private TableColumn<Locataire, String> idcolone;

    @FXML
    private Button modifbt;

    @FXML
    private TableColumn<Locataire, String> nomcolone;

    @FXML
    private TableColumn<Locataire, String> prenomcolone;

    @FXML
    private Button suppbt;

    @FXML
    private Button detbt;


    @FXML
    private TableColumn<Locataire, String> telephonecolone;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtprenom;
    
    @FXML
    private ChoiceBox<String> txtetat;
    private String[] t = {"SATISFAIT" , "ANNULER" , "EN ATTENTE"};

    @FXML
    private TextField txtid;


    @FXML
    private TextField txttel;
    
    @FXML
    private TextField txtadresse;

    @FXML
    private TextField txtdure;

    @FXML
    private TextField searchField;

	private void addTextChangeListeners() {
	    txtNom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtprenom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtadresse.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txttel.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtid.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtdure.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	}
	
	
    public static boolean IdUnique(String id) {
        // Parcourir la liste des acheteurs et vérifier si l'ID existe déjà
    	for (Locataire l : Main.locataires) {
            if (l.getNum_id().equals(id)) {
                return false; 
            }
        }
        return true;
    }
	
	private void filterTable(String searchText) {
        ObservableList<Locataire> filteredList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            table.setItems(Main.locataires);
            return;
        }
        // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
        for (Locataire c : table.getItems()) {
            if (c.getNom().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getPrenom().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getNum_id().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(c);
            }
        }

        // Mettre à jour la TableView avec la liste filtrée
        table.setItems(filteredList);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//pour assurer que tout les element seront visible dans le tableau 
		if (Main.locataires.isEmpty() == false)
		{
			table.setItems(Main.locataires);
		}
        nomcolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("nom"));
        prenomcolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("prenom"));
        adressecolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("adresse"));
        telephonecolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("NumtelM"));
        idcolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("Num_id"));
        txtetat.getItems().addAll(t);
        txtetat.setValue("EN ATTENTE");
        etatcolone.setCellValueFactory(new PropertyValueFactory<Locataire, String>("etat"));
        durcolone.setCellValueFactory(new PropertyValueFactory<Locataire, Long>("dureloc_prevu"));
		
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
	    updateAjouterBienButtonState();
	    updateAfficherButtonState();
        
	}
	
	
	
	
    private void updateAjouterButtonState() {
        // Vérifier si tous les champs sont remplis
        boolean champsRemplis = !txtNom.getText().isEmpty() &&
                                !txtprenom.getText().isEmpty() &&
                                !txtadresse.getText().isEmpty() &&
                                !txttel.getText().isEmpty() &&
                                !txtid.getText().isEmpty() &&
                                !txtdure.getText().isEmpty() ;

        // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
        ajoutbt.setDisable(!champsRemplis);
    }
    private void updateModifierButtonState() {
        Locataire clientSelectionne = null;
    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		modifbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
            modifbt.setDisable(true);
        }
    }
    
    
    private void updateAjouterBienButtonState() {
        Locataire clientSelectionne = null;
    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		ajouterbienbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		ajouterbienbt.setDisable(true);
        }
    }
    private void updateSupprimerButtonState() {
    	Locataire clientSelectionne = null;
    	 clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		suppbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		suppbt.setDisable(true);
        }
    }
    
    private void updateAfficherButtonState() {;
    	Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		detbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		detbt.setDisable(true);
        }
    }  
    

    
    public void selectplus() {
        // Récupérer l'élément sélectionné dans la TableView
    	Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
        
        // Vérifier si un élément est sélectionné
        if (clientSelectionne != null) {
            // Obtenir l'ID du client sélectionné
        	modifbt.setDisable(false);	
        	// le button de detailles
        	suppbt.setDisable(false);
        	ajouterbienbt.setDisable(false);
        	detbt.setDisable(false);
            String idClient = clientSelectionne.getNum_id();
            for (Locataire a : Main.locataires)
            {
            	if (a.getNum_id().equals(idClient))
            	{
                    txtNom.setText(a.getNom());
                    txtprenom.setText(a.getPrenom());
                    txtadresse.setText(a.getAdresse());
                    txttel.setText(a.getNumtelM());
                    txtid.setText(a.getNum_id());                   
                    txtdure.setText(Long.toString(a.getDureloc_prevu()));
                    txtetat.setValue(null);
                    txtetat.setValue(a.getEtat());                 
                }
            }
        } else {
            // Aucun élément sélectionné, gérer le cas en conséquence
            // dialogPane fenetre sera afficher 
        }
    }
	
    @FXML
    void afficher(ActionEvent event) {
        try {
        	Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleL.fxml"));
            Parent root = (Parent) loader.load();
            controlleurAffichL controlleur = loader.getController(); // Obtenir le contrôleur
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
        updateAjouterBienButtonState();
        updateAfficherButtonState();
    }
	
     
    @FXML
    void ajouter(ActionEvent event) {
        String nom = txtNom.getText();
        String prenom  = txtprenom.getText();        
        String adresse =txtadresse.getText();
        String tel  = txttel.getText();
        String id  = txtid.getText();
        String dure =txtdure.getText();
        
        if (verification.isValidNom(nom) && verification.isValidPrenom(prenom) && verification.isValidTelephone(tel) && verification.isValidNumeroIdentite(id))
        {     
           if (IdUnique(id))
		        {        	 
		            Locataire locataire = new Locataire(nom ,prenom,adresse,tel,id,txtetat.getValue(),Integer.parseInt(dure));
		            Main.locataires.add(locataire);        
		            table.setItems(Main.locataires);
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
    void supprimer(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
        Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
        
        
        // Vérifier si un élément est sélectionné
        if (clientSelectionne != null) {
        	table.getItems().remove(selectedID);
            // Obtenir l'ID du client sélectionné
            String idClient = clientSelectionne.getNum_id();
            for (Locataire l : Main.locataires)
            {
            	if (l.getNum_id().equals(idClient))
            	{
            		Main.locataires.remove(clientSelectionne);
                }
            }
        }
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAjouterBienButtonState();
        updateAfficherButtonState();
    }
    

    @FXML
    void ajouterbien(ActionEvent event) {
        
    		try {
    			Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bien.fxml"));
	            Parent root = (Parent) loader.load();
	            controlleurB controlle = loader.getController();
	            
	            if(controlle != null) {
	            	controlle.initializeWithID(clientSelectionne.getNum_id(),"LOCATION");
	                Stage stage = new Stage();
	                Scene scene = new Scene(root);
	                stage.setScene(scene);
	                stage.show();
	            }
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	
            table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
            updateModifierButtonState();
            updateSupprimerButtonState();
            updateAjouterBienButtonState();
            updateAfficherButtonState();
    }

    @FXML
    void effacer(ActionEvent event) {

        // Effacer le contenu de chaque TextField
        txtNom.clear();
        txtprenom.clear();
        txtadresse.clear();
        txttel.clear();
        txtid.clear();
        txtdure.clear();
        // Effacer la sélection retourner a la valeur initiale
        txtetat.setValue("EN ATTENTE");
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAjouterBienButtonState();
        updateAfficherButtonState();
    	
    	
    }
    
    @FXML
    void modifier(ActionEvent event) {   
        int selectedID = table.getSelectionModel().getSelectedIndex();
        Locataire clientSelectionne = table.getSelectionModel().getSelectedItem();
        int index;
        if (!Main.locataires.isEmpty() && clientSelectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
            // Obtenir l'ID du client sélectionné
            String idClient = clientSelectionne.getNum_id();
            for (Locataire  a : Main.locataires) {
                if (a.getNum_id().equals(idClient)) {   
                    index = Main.locataires.indexOf(a);                        

                    // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
                    if (txtNom.getText().equals(a.getNom()) &&
                    		txtprenom.getText().equals(a.getPrenom()) &&
                    		txttel.getText().equals(a.getNumtelM()) &&
                    		txtdure.getText().equals(String.valueOf(a.getDureloc_prevu())) &&
                            txtetat.getValue().equals(a.getEtat()) &&
                            txtadresse.getText().equals(a.getAdresse())
                            )
                               {

                        // Aucune modification n'a été apportée
                        // Afficher une alerte
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Aucune modification n'a été apportée.");
                        alert.showAndWait();
                    } else {
                    	
                        if (verification.isValidNom(txtNom.getText()) && verification.isValidPrenom(txtprenom.getText()) && verification.isValidTelephone(txttel.getText()) && verification.isValidNumeroIdentite(txtid.getText()) && verification.isValidInt(txtdure.getText()))
                        {
                        	// Modifier l'élément et mettre à jour la liste et la table
	                        a.setNom(txtNom.getText());
	                        a.setPrenom(txtprenom.getText());
	                        a.setNumtelM(txttel.getText());
	                        a.setNum_id(txtid.getText());
	                        a.setAdresse(txtadresse.getText());
	                        a.setEtat(txtetat.getValue());
	                        a.setDureloc_prevu(Integer.parseInt(txtdure.getText()));
	
	                       Main.locataires.set(index, a);
	                       table.getItems().set(selectedID,a);
	                       table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
	                       updateModifierButtonState();
	                       updateSupprimerButtonState();
	                       updateAjouterBienButtonState();
	                       updateAfficherButtonState();
                        }
                    }
                    break;
                }
            }
        }
    }
}



