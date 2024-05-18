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

public class controlleurVen implements Initializable {

    @FXML
    private TableColumn<Vendeur, String> adressecolone;

    @FXML
    private Button ajoutbt;

    @FXML
    private Button ajouterbienbt;

    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private Button detbt;

    @FXML
    private TableColumn<Vendeur,String> typecolone;

    @FXML
    private TableColumn<Vendeur,String> idcolone;

    @FXML
    private Button modifbt;

    @FXML
    private TableColumn<Vendeur,String> nomcolone;

    @FXML
    private Button nvbt;

    @FXML
    private TableColumn<Vendeur,String> prenomcolone;

    @FXML
    private TextField searchField;

    @FXML
    private Button suppbt;

    @FXML
    private TableView<Vendeur> table;

    @FXML
    private TableColumn<Vendeur,String> telephonecolone;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtadresse;

    @FXML
    private TextField txtid;

    @FXML
    private ChoiceBox<String> txtpay;
    private String[] t = {"CASH","CREDIT BANCAIRE"};

    
    @FXML
    private ChoiceBox<String> txtetat;
    private String[] e = {"SATISFAIT" , "ANNULER" , "EN ATTENTE"};
    @FXML
    private TextField txtprenom;

    @FXML
    private TextField txttel;

    private void addTextChangeListeners() {
	    txtNom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtprenom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtadresse.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txttel.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtid.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	}
	
	private void filterTable(String searchText) {
        ObservableList<Vendeur> filteredList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            table.setItems(Main.vendeurs);
            return;
        }
        // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
        for (Vendeur c : table.getItems()) {
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
		if (Main.vendeurs.isEmpty() == false)
		{
			table.setItems(Main.vendeurs);
		}
        nomcolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("nom"));
        prenomcolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("prenom"));
        adressecolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("adresse"));
        telephonecolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("NumtelM"));
        idcolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("Num_id"));
        txtpay.getItems().addAll(t);
        txtpay.setValue("CREDIT BANCAIRE");
        txtetat.getItems().addAll(e);
        txtetat.setValue("EN ATTENTE");
        typecolone.setCellValueFactory(new PropertyValueFactory<Vendeur, String>("typePay"));
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
                                !txtid.getText().isEmpty();

        // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
        ajoutbt.setDisable(!champsRemplis);
    }
    private void updateModifierButtonState() {
        Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		modifbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
            modifbt.setDisable(true);
        }
    }
    
    
    private void updateAjouterBienButtonState() {
    	Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		ajouterbienbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		ajouterbienbt.setDisable(true);
        }
    }
    private void updateSupprimerButtonState() {
    	Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
    	if (clientSelectionne != null)
    	{
    		suppbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		suppbt.setDisable(true);
        }
    }
    
    private void updateAfficherButtonState() {;
    Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
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
    	Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
        
        // Vérifier si un élément est sélectionné
        if (clientSelectionne != null) {
            // Obtenir l'ID du client sélectionné
        	modifbt.setDisable(false);	
        	// le button de detailles
        	suppbt.setDisable(false);
        	ajouterbienbt.setDisable(false);
        	detbt.setDisable(false);
            String idClient = clientSelectionne.getNum_id();
            for (Vendeur a : Main.vendeurs)
            {
            	if (a.getNum_id().equals(idClient))
            	{
                    txtNom.setText(a.getNom());
                    txtprenom.setText(a.getPrenom());
                    txtadresse.setText(a.getAdresse());
                    txttel.setText(a.getNumtelM());
                    txtid.setText(a.getNum_id());                   
                    txtpay.setValue(null);
                    txtpay.setValue(a.getTypePay());  
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
            Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleV.fxml"));
            Parent root = (Parent) loader.load();
            controlleurAffichV controlleur = loader.getController(); // Obtenir le contrôleur
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
    
    public boolean IdUnique(String id) {
        // Parcourir la liste des acheteurs et vérifier si l'ID existe déjà
    	for (Vendeur a : Main.vendeurs) {
            if (a.getNum_id().equals(id)) {
                return false; 
            }
        }
        return true;
    }
    
    @FXML
    void ajouter(ActionEvent event) {

        String nom = txtNom.getText();
        String prenom  = txtprenom.getText();        
        String adresse =txtadresse.getText();
        String tel  = txttel.getText();
        String id  = txtid.getText();
        String pay =txtpay.getValue();
        String etat = txtetat.getValue();
        if (verification.isValidNom(nom) && verification.isValidPrenom(prenom) && verification.isValidTelephone(tel) && verification.isValidNumeroIdentite(id))
        {        	         	
            if (IdUnique(id))
 		        {        	           	
	                Vendeur vendeur = new Vendeur(nom ,prenom,adresse,tel,id,pay,etat);
	                Main.vendeurs.add(vendeur);        
	                table.setItems(Main.vendeurs);
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
    void ajouterbien(ActionEvent event) {

		try {
			Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bien.fxml"));
            Parent root = (Parent) loader.load();
            controlleurB controlle = loader.getController();
            
            if(controlle != null) {
            	controlle.initializeWithID(clientSelectionne.getNum_id(),"VENTE");
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

        // Effacer la sélection retourner a la valeur initiale
        txtpay.setValue("CREDIT BANCAIRE");
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAjouterBienButtonState();
        updateAfficherButtonState();	
    	
    }

    @FXML
    void modifier(ActionEvent event) {

    	   int selectedID = table.getSelectionModel().getSelectedIndex();
           Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
           int index;
           if (!Main.vendeurs.isEmpty() && clientSelectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
               // Obtenir l'ID du client sélectionné
               String idClient = clientSelectionne.getNum_id();
               for (Vendeur  a : Main.vendeurs) {
                   if (a.getNum_id().equals(idClient)) {   
                       index = Main.vendeurs.indexOf(a);                        

                       // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
                       if (txtNom.getText().equals(a.getNom()) &&
                       		txtprenom.getText().equals(a.getPrenom()) &&
                       		txttel.getText().equals(a.getNumtelM()) &&
                               txtpay.getValue().equals(a.getTypePay()) &&
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
                       	
                           if (verification.isValidNom(txtNom.getText()) && verification.isValidPrenom(txtprenom.getText()) && verification.isValidTelephone(txttel.getText()) && verification.isValidNumeroIdentite(txtid.getText()))
                           {
	                           	// Modifier l'élément et mettre à jour la liste et la table
	   	                        a.setNom(txtNom.getText());
	   	                        a.setPrenom(txtprenom.getText());
	   	                        a.setNumtelM(txttel.getText());
	   	                        a.setNum_id(txtid.getText());
	   	                        a.setAdresse(txtadresse.getText());
	   	                        a.setTypePay(txtpay.getValue());
	   	                        a.setEtat(txtetat.getValue());
	   	
	   	                       Main.vendeurs.set(index, a);
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

    @FXML
    void supprimer(ActionEvent event) {

        int selectedID = table.getSelectionModel().getSelectedIndex();
        Vendeur clientSelectionne = table.getSelectionModel().getSelectedItem();
        
        
        // Vérifier si un élément est sélectionné
        if (clientSelectionne != null) {
        	table.getItems().remove(selectedID);
            // Obtenir l'ID du client sélectionné
            String idClient = clientSelectionne.getNum_id();
            for (Vendeur v : Main.vendeurs)
            {
            	if (v.getNum_id().equals(idClient))
            	{
            		Main.vendeurs.remove(clientSelectionne);
                }
            }
        }
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAjouterBienButtonState();
        updateAfficherButtonState();
    	
    }

}
