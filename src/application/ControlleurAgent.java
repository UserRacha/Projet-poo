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

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControlleurAgent implements Initializable{



	@FXML
    private TableView<AgentIMM> table;
	
    @FXML
    private TableColumn<AgentIMM , String> adressecolone;

    @FXML
    private Button ajoutbt;
    
    @FXML
    private Button nvbt;


    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private TableColumn<AgentIMM , String> idcolone;

    @FXML
    private Button modifbt;

    @FXML
    private TableColumn<AgentIMM , String> nomcolone;

    @FXML
    private TableColumn<AgentIMM , String> prenomcolone;

    @FXML
    private Button suppbt;

    @FXML
    private Button detbt;


    @FXML
    private TableColumn<AgentIMM , String> telephonecolone;

    @FXML
    private TextField txtNom;

    @FXML
    private TextField txtprenom;


    @FXML
    private TextField txtid;


    @FXML
    private TextField txttel;
    
    @FXML
    private TextField txtadresse;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private PasswordField txtconf;

    @FXML
    private TextField searchField;
    
    
    private void addTextChangeListeners() {
	    txtNom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtprenom.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtadresse.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txttel.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtid.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtpassword.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtconf.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	}
    
    
    public static boolean IdUnique(String id ) {
        // Parcourir la liste des agents et vérifier si l'ID existe déjà)
        for (AgentIMM agent : Main.listeAgents ) {
            if (agent.getId().equals(id)) {   
                return false; 
            }
          }
    	
        return true;
    }
    
    
    private void filterTable(String searchText) {
        ObservableList<AgentIMM> filteredList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            table.setItems(Main.listeAgents);
            return;
        }
        // Parcourir la liste des agents et ajouter ceux qui correspondent aux critère de recherche
        for (AgentIMM agent : table.getItems()) {
            if (agent.getNom().toLowerCase().contains(searchText.toLowerCase()) ||
                agent.getPrenom().toLowerCase().contains(searchText.toLowerCase()) ||
                agent.getId().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(agent);
            }
        }

        // Mettre à jour la TableView avec la liste filtrée
        table.setItems(filteredList);
    }
        
     
        
        
        private void updateAjouterButtonState() {
            // Vérifier si tous les champs sont remplis
            boolean champsRemplis = !txtNom.getText().isEmpty() &&
                                    !txtprenom.getText().isEmpty() &&
                                    !txtadresse.getText().isEmpty() &&
                                    !txttel.getText().isEmpty() &&
                                    !txtid.getText().isEmpty() &&
                                    !txtpassword.getText().isEmpty() &&
                                    txtconf.getText().equals(txtpassword.getText());

            // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
            ajoutbt.setDisable(!champsRemplis);
        }
        
        private void updateModifierButtonState() {
            AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
        	if (agentSelectionne != null)
        	{
        		modifbt.setDisable(false);	
        	}else {
                // Sinon, désactiver le bouton "Modifier"
                modifbt.setDisable(true);
            }
        }
        
        
        
        private void updateSupprimerButtonState() {
        	AgentIMM agentSelectionne = null;
        	 agentSelectionne = table.getSelectionModel().getSelectedItem();
        	if (agentSelectionne != null)
        	{
        		suppbt.setDisable(false);	
        	}else {
                // Sinon, désactiver le bouton "Modifier"
        		suppbt.setDisable(true);
            }
        }
        
        private void updateAfficherButtonState() {;
        	AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
        	if (agentSelectionne != null)
        	{
        		detbt.setDisable(false);	
        	}else {
                // Sinon, désactiver le bouton "Modifier"
        		detbt.setDisable(true);
            }
        } 
        
        
        public void selectplus() {
            // Récupérer l'élément sélectionné dans la TableView
        	AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
            
            // Vérifier si un élément est sélectionné
            if (agentSelectionne != null) {
                // Obtenir l'ID du client sélectionné
            	modifbt.setDisable(false);	
            	// le button de detailles
            	suppbt.setDisable(false);
            	detbt.setDisable(false);
                String idAgent = agentSelectionne.getId();
                for (AgentIMM a : Main.listeAgents)
                {
                	if (a.getId().equals(idAgent))     
                	{
                        txtNom.setText(a.getNom());
                        txtprenom.setText(a.getPrenom());
                        txtadresse.setText(a.getAdresse());
                        txttel.setText(a.getTelephone());
                        txtid.setText(a.getId());                  
                        txtpassword.setText(a.getPassword());                
                    }
                }
            }  else {
                // Aucun élément sélectionné, gérer le cas en conséquence
                // dialogPane fenetre sera afficher 
            }
          }
            
        
        
        @Override
    	public void initialize(URL arg0, ResourceBundle arg1) {
    		//pour assurer que tout les element seront visible dans le tableau 
    		if (Main.listeAgents.isEmpty() == false)
    		{
    			table.setItems(Main.listeAgents);
    		}
            nomcolone.setCellValueFactory(new PropertyValueFactory<AgentIMM, String>("nom"));
            prenomcolone.setCellValueFactory(new PropertyValueFactory<AgentIMM, String>("prenom"));
            adressecolone.setCellValueFactory(new PropertyValueFactory<AgentIMM, String>("adresse"));
            telephonecolone.setCellValueFactory(new PropertyValueFactory<AgentIMM, String>("telephone"));
            idcolone.setCellValueFactory(new PropertyValueFactory<AgentIMM, String>("id"));
            
    	    table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	        if (newSelection != null) {
    	            // Si une nouvelle sélection est effectuée, appeler la méthode selectplus()
    	            selectplus();
    	        } 
    	    });
    	     /*
    	 // Ajouter un écouteur pour masquer le mot de passe
    	    txtpassword.textProperty().addListener((obs, oldVal, newVal) -> {
    	        // Remplacer chaque caractère par un astérisque
    	        StringBuilder maskedPassword = new StringBuilder();
    	        for (int i = 0; i < newVal.length(); i++) {
    	            maskedPassword.append("*");
    	        }
    	        txtpassword.setText(maskedPassword.toString());
    	    });
    	    
    	    txtconf.textProperty().addListener((obs, oldVal, newVal) -> {
    	        // Remplacer chaque caractère par un astérisque
    	        StringBuilder maskedPassword = new StringBuilder();
    	        for (int i = 0; i < newVal.length(); i++) {
    	            maskedPassword.append("*");
    	        }
    	        txtconf.setText(maskedPassword.toString());
    	    });
    	    */
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
                	AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
                	
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichedetailleAgent.fxml"));
                    Parent root = (Parent) loader.load();
                    controlleurAffichAgent controlleur = loader.getController(); // Obtenir le contrôleur
                    if(controlleur != null) {
                        controlleur.afficherinfobien(agentSelectionne);
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
        	
             
            @FXML
            void ajouter(ActionEvent event) {
                String nom = txtNom.getText();
                String prenom  = txtprenom.getText();        
                String adresse =txtadresse.getText();
                String tel  = txttel.getText();
                String id  = txtid.getText();
                String password =txtpassword.getText();
                String conf = txtconf.getText();
                if (verification.isValidNom(nom) 
                		&& verification.isValidPrenom(prenom) 
                		&& verification.isValidTelephone(tel) 
                		&& verification.isValidNumeroIdentite(id)
                		&&  password.equals(conf))
                {     
                   if (IdUnique(id))
        		        {        	 
        		            AgentIMM agent = new AgentIMM (nom ,prenom,id ,tel,adresse,password);
        		            Main.listeAgents.add(agent);        
        		            table.setItems(Main.listeAgents);
        		            
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
                ajoutbt.setDisable(true);
            }
            

            @FXML
            void supprimer(ActionEvent event) {
                int selectedID = table.getSelectionModel().getSelectedIndex();
                AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
                
                
                // Vérifier si un élément est sélectionné
                if (agentSelectionne != null) {
                	table.getItems().remove(selectedID);
                    // Obtenir l'ID du client sélectionné
                    String idAgent = agentSelectionne.getId();
                    for (AgentIMM a : Main.listeAgents)
                    {
                    	if (a.getId().equals(idAgent))
                    	{
                    		Main.listeAgents.remove(agentSelectionne);
                        }
                    }
                }
                table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
                updateModifierButtonState();
                updateSupprimerButtonState();
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
                txtpassword.clear();
                // Effacer la sélection retourner a la valeur initiale
                table.getSelectionModel().clearSelection(); // Effacer la sélection après la modification
                updateModifierButtonState();
                updateSupprimerButtonState();
                updateAfficherButtonState();
            	
            	
            }
            
            @FXML
            void modifier(ActionEvent event) {   
                int selectedID = table.getSelectionModel().getSelectedIndex();
                AgentIMM agentSelectionne = table.getSelectionModel().getSelectedItem();
                int index;
                if (!Main.listeAgents.isEmpty() && agentSelectionne != null) { // Vérifier si la liste bailleurs n'est pas vide
                    // Obtenir l'ID du client sélectionné
                    String idAgent = agentSelectionne.getId();
                    for (AgentIMM  a : Main.listeAgents) {
                        if (a.getId().equals(idAgent)) {   
                            index = Main.listeAgents.indexOf(a);                        

                            // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
                            if (txtNom.getText().equals(a.getNom()) &&
                            		txtprenom.getText().equals(a.getPrenom()) &&
                            		txttel.getText().equals(a.getTelephone()) &&
                                    txtadresse.getText().equals(a.getAdresse()) &&
                                    txtpassword.getText().equals(a.getPassword()) &&
                                    txtid.getText().equals(a.getId()))
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
        	                        a.setTelephone(txttel.getText());
        	                        a.setId(txtid.getText());
        	                        a.setAdresse(txtadresse.getText());
        	                        a.setPassword(txtpassword.getText());
        	
        	                       Main.listeAgents.set(index, a);
        	                       table.getItems().set(selectedID,a);
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
	
	
	
}
