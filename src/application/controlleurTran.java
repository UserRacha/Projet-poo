package application;

import java.io.IOException;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

public class controlleurTran implements Initializable{

    @FXML
    private Button ajoutbt;

    @FXML
    private AnchorPane barbutton;

    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private TableColumn<Transaction, Integer> biencolone;

    @FXML
    private TableColumn<Transaction, Date> datecolone;

    @FXML
    private Button detbt;

    @FXML
    private TableColumn<Transaction, String> mem1colone;

    @FXML
    private TableColumn<Transaction, String> mem2colone;
    
    
    @FXML
    private TableColumn<Transaction, Double> benifcolone;
    

    @FXML
    private Button modifbt;

    @FXML
    private Button nvbt;

    @FXML
    private TextField searchField;

    @FXML
    private Button suppbt;

    @FXML
    private TableView<Transaction> table;

    @FXML
    private TableColumn<Transaction, String> trancolone;

    @FXML
    private DatePicker txtdate;
    
    @FXML
    private TextField txtbenif;

    @FXML
    private TextField txtbien;

    @FXML
    private TextField txtmem1;

    @FXML
    private TextField txtmem2;

    @FXML
    private ChoiceBox<String> txttran;
    String[] t = { "VENTE" , "LOCATION"};
    
    
	 //updates des buttons 
    private void updateAjouterButtonState() {
        // Vérifier si tous les champs sont remplis
        boolean champsRemplis = !txtmem2.getText().isEmpty() &&
                                !txtmem1.getText().isEmpty() &&
                                !txtbien.getText().isEmpty() &&
                                !txtbenif.getText().isEmpty() ;
        

        // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
        ajoutbt.setDisable(!champsRemplis);
    }
    private void updateModifierButtonState() {

        Transaction Selectionne = table.getSelectionModel().getSelectedItem();
    	if (Selectionne != null)
    	{
    		modifbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
            modifbt.setDisable(true);
        }
    }
    
    private void updateSupprimerButtonState() {

        Transaction Selectionne  = table.getSelectionModel().getSelectedItem();
    	if (Selectionne  != null)
    	{
    		suppbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		suppbt.setDisable(true);
        }
    }
    
    private void updateAfficherButtonState() {

        Transaction Selectionne = table.getSelectionModel().getSelectedItem();
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
        Transaction Selectionne = table.getSelectionModel().getSelectedItem();

        // Vérifier si un élément est sélectionné
        if (Selectionne != null) {
            // Obtenir l'ID du client sélectionné
        	modifbt.setDisable(false);
        	detbt.setDisable(false);	
        	suppbt.setDisable(false);
            for (Transaction a : Main.Transactions)
            {
                txtmem1.setText(a.getMembre1());
                txtmem2.setText(a.getMembre2());
                txttran.setValue(null);
                txttran.setValue(a.getTypetran());
                txtbenif.setText(Double.toString(a.getBénéfice()));
                txtbien.setText(Integer.toString(a.getId_bien()));
                txtdate.setValue(a.getDate());
                
            }
        } else {
            // Aucun élément sélectionné, gérer le cas en conséquence
            // dialogPane fenetre sera afficher 
               }
    }
    
	private void addTextChangeListeners() {
		 txtmem1.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		 txtmem2.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		 txtbenif.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
		 txtbien.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	}
	
    // a revoire 
	private void filterTable(String searchText) {
        ObservableList<Transaction> filteredList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            table.setItems(Main.Transactions);
            return;
        }
        // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
        for (Transaction c : table.getItems()) {
        	LocalDate transactionDate = c.getDate();
            if (c.getMembre1().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getMembre2().toLowerCase().contains(searchText.toLowerCase()) ||
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
        if (Main.Transactions.isEmpty() == false)
        {
        	table.setItems(Main.Transactions);
        }
        
	    trancolone.setCellValueFactory(new PropertyValueFactory<Transaction, String>("typetran"));
	    mem1colone.setCellValueFactory(new PropertyValueFactory<Transaction, String>("membre1"));
	    mem2colone.setCellValueFactory(new PropertyValueFactory<Transaction, String>("membre2"));
	    datecolone.setCellValueFactory(new PropertyValueFactory<Transaction, Date>("date"));
	    biencolone.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("id_bien"));
	    benifcolone.setCellValueFactory(new PropertyValueFactory<Transaction, Double>("Bénéfice"));
	    
	    // Configurer la valeur factory pour le Spinner
	    txttran.getItems().addAll(t);
        txttran.setValue("VENTE");
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
	    try {
	        Transaction selectionnee = table.getSelectionModel().getSelectedItem();
	        if (selectionnee != null) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("contart.fxml"));
	            Parent root = loader.load();
	            Scene scene = new Scene(root);
	            ContolleurContrat controlleur = loader.getController(); // Obtenir le contrôleur
	            if (controlleur != null) {
	                controlleur.forme_a_afficher(selectionnee);
	                Stage stage = new Stage();
	                stage.setScene(scene);
	                stage.show();
	            } else {
	                System.err.println("Erreur : le contrôleur est null");
	            }
	        } else {
	            System.err.println("Aucune transaction sélectionnée");
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

	    String Type = txttran.getValue();
        String membre1 = txtmem1.getText();        
        String membre2 =txtmem2.getText();
        String code  = txtbien.getText();
        String benif  = txtbenif.getText();
        LocalDate date = txtdate.getValue() ;
        
        if (date.isBefore(LocalDate.now())) {
            verification.showAlert("Erreur", "Date invalide", "Date antérieure à la date actuelle.");
            return;
        }
        
        
        if (verification.isValidNumeroIdentite(membre1) && 
        		verification.isValidNumeroIdentite(membre2) &&  
        		verification.isValidDouble(benif) && 
        		verification.isValidInt(code) &&
        		verification.isClientWithRole(membre1 , Type , 1) &&  
        		verification.isClientWithRole(membre2 , Type , 2) &&
        		verification.isBienexicte(Integer.parseInt(code))&&
        		verification.bienduclient(Integer.parseInt(code), membre1) &&
        		verification.isValidType(Integer.parseInt(code), Type) 
        		)
        {        	 
        	     	           	
           Transaction tran = new Transaction(Type ,date,membre1,membre2,Double.parseDouble(benif),Integer.parseInt(code));
           Main.Transactions.add(tran);        
           table.setItems(Main.Transactions);
           ajoutbt.setDisable(true);
        }
    	
    }
    
    @FXML
    void effacer(ActionEvent event) {
        // Effacer le contenu de chaque TextField
        txtmem1.clear();
        txtmem2.clear();
        txtbien.clear();
        txtbenif.clear();

        // Effacer la sélection retourner a la valeur initiale
        txtdate.setValue(LocalDate.now());
        txttran.setValue("VENTE");
        
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }

    @FXML
    void modifier(ActionEvent event) {
    	    int selectedID = table.getSelectionModel().getSelectedIndex();
	        Transaction Selectionne = table.getSelectionModel().getSelectedItem();
	        int index;
	        if (!Main.Transactions.isEmpty() && Selectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
	            // Obtenir l'ID du client sélectionné
	            int idTran = Selectionne.getCode();
	            for (Transaction a : Main.Transactions) {
	                if (a.getCode() == idTran) {   
	                    
	                	index = Main.Transactions.indexOf(a);                        
	                    // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
	            	    String Type = txttran.getValue();
	                    String membre1 = txtmem1.getText();        
	                    String membre2 =txtmem2.getText();
	                    String code  = txtbien.getText();
	                    String benif  = txtbenif.getText();
	                    LocalDate date = txtdate.getValue() ;
	                    
	                    if (Type.equals(a.getTypetran()) &&
	                        membre1.equals(a.getMembre1()) &&
	                        membre2.equals(a.getMembre2()) &&
	                        code.equals(String.valueOf(a.getId_bien())) &&
	                        benif.equals(String.valueOf(a.getBénéfice())) &&
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
	            	        if (verification.isValidNumeroIdentite(membre1) && 
	            	        		verification.isValidNumeroIdentite(membre2) &&  
	            	        		verification.isValidDouble(benif) && 
	            	        		verification.isValidInt(code) &&
	            	        		verification.isClientWithRole(membre1 , Type , 1) &&  
	            	        		verification.isClientWithRole(membre2 , Type , 2) &&
	            	        		verification.isBienexicte (Integer.parseInt(code)) &&
	            	        		verification.bienduclient(Integer.parseInt(code), membre1) &&
	            	        		verification.isValidType(Integer.parseInt(code), Type) 
	            	        		)
	            	        {
			                        a.setBénéfice(Double.parseDouble(benif));
			                        a.setDate(date);
			                        a.setId_bien(Integer.parseInt(code));
			                        a.setMembre1(membre1);
			                        a.setMembre2(membre2);
			                        a.setTypetran(Type);

			                        Main.Transactions.set(index, a);
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
        Transaction Selectionne = table.getSelectionModel().getSelectedItem();
        
        
        // Vérifier si un élément est sélectionné
        if (Selectionne != null) {
        	table.getItems().remove(selectedID);
            // Obtenir l'ID du client sélectionné
            int idClient = Selectionne.getCode();
            for (Transaction t : Main.Transactions)
            {
            	if (t.getCode() == idClient)
            	{
            		Main.Transactions.remove(Selectionne);
                }
            }
        }
        table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }



}

