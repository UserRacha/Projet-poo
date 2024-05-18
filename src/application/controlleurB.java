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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class controlleurB implements Initializable{

    @FXML
    private TableColumn<BienIM, String> actcolone;

    @FXML
    private Button ajoutbt;

    @FXML
    private Tab barsaisie;

    @FXML
    private Tab bartable;

    @FXML
    private Button detbt;

    @FXML
    private TableColumn<BienIM, String> etatcolone;

    @FXML
    private TableColumn<BienIM, String> loccolone;

    @FXML
    private Button modifbt;

    @FXML
    private Button nvbt;

    @FXML
    private TableColumn<BienIM, Double> prixcolone;

    @FXML
    private TableColumn<BienIM , String> propcolone;

    @FXML
    private TextField searchField;

    @FXML
    private Button suppbt;

    @FXML
    private TableColumn<BienIM , Double> surfcolone;

    @FXML
    private TableView<BienIM> table;

    @FXML
    private TableColumn<BienIM,String> trancolone;

    @FXML
    private ChoiceBox<String> txtact;
    private String [] a = { "ACTE_NOTARIE" ,   "ACTE_INDIVISION" , "PAPIER_TIMBRE" , "PROMESSE_DE_VENTE"};

    @FXML
    private TextArea txtdesc;

    @FXML
    private ChoiceBox<String> txtetat;
    private String [] e = { "OCCUPER" , "LIBRE" };

    @FXML
    private TextField txtidprop;

    @FXML
    private TextField txtloc;

    @FXML
    private TextField txtmontant;

    @FXML
    private TextField txtsurf;

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
    private ChoiceBox<String> txttran;
    private String[]  t = { "VENTE" , "LOCATION"};

    @FXML
    private TableColumn<BienIM,String> typecolone;

    private void updateAjouterButtonState() {
        // Vérifier si tous les champs sont remplis
        boolean champsRemplis = !txtidprop.getText().isEmpty() &&
                                !txtdesc.getText().isEmpty() &&
                                !txtmontant.getText().isEmpty() &&
                                !txtsurf.getText().isEmpty() &&
                                !txtloc.getText().isEmpty() ;

        // Activer ou désactiver le bouton Ajouter en fonction de l'état des champs
        ajoutbt.setDisable(!champsRemplis);
    }
    
    
    private void updateModifierButtonState() {
        BienIM bSelectionne = null;
        bSelectionne = table.getSelectionModel().getSelectedItem();
    	if (bSelectionne != null)
    	{
    		modifbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
            modifbt.setDisable(true);
        }
    }
    
    private void updateSupprimerButtonState() {
        BienIM bSelectionne = null;
        bSelectionne = table.getSelectionModel().getSelectedItem();
    	if (bSelectionne != null)
    	{
    		suppbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		suppbt.setDisable(true);
        }
    }
    
    private void updateAfficherButtonState() {
        BienIM bSelectionne = null;
        bSelectionne = table.getSelectionModel().getSelectedItem();
    	if (bSelectionne != null)
    	{
    		detbt.setDisable(false);	
    	}else {
            // Sinon, désactiver le bouton "Modifier"
    		detbt.setDisable(true);
        }
    }    
    
    public void selectplus() {
        // Récupérer l'élément sélectionné dans la TableView
        BienIM b = table.getSelectionModel().getSelectedItem();
        
        // Vérifier si un élément est sélectionné
        if (b != null) {
            
        	modifbt.setDisable(false);
        	detbt.setDisable(false);	
        	suppbt.setDisable(false);
            txtidprop.setText(b.getProprietaire());
            txtmontant.setText(Double.toString(b.getPrix()));
            txtsurf.setText(Double.toString(b.getSuperficie()));
            txtloc.setText(b.getLocalisation());
            txtdesc.setText(b.getDescription());
            // Effacer la sélection et sélectionner les valeurs correspondantes dans les ChoiceBox
            txttBien.setValue(null);
            txttBien.setValue(b.getType());	
            txtact.setValue(null);
            txtact.setValue(b.getTypeActe());
            txtetat.setValue(null);
            txtetat.setValue(b.getEtat());
            txttran.setValue(null);
            txttran.setValue(b.getTytran());

        } else {
            // Aucun élément sélectionné, gérer le cas en conséquence
            // dialogPane fenetre sera afficher 
        }
    }
    /*
    private boolean IdUnique(int id) {
        // Parcourir la liste des acheteurs et vérifier si l'ID existe déjà
    	for (BienIM a : Main.Biens) {
            if (a.getIdentifiantBien() == id ) {
                return false; // L'ID existe déjà
            }
        }
        return true;
    }
    */



    // Méthode pour initialiser le contrôleur avec l'ID du locataire
    public void initializeWithID(String ID,String tran) {
        
        if (ID == null || ID.isEmpty()) {
        	txtidprop.setEditable(true); // Rendre le champ modifiable si l'ID est null ou vide
        } else {
        	txtidprop.setText(ID);
        	txttran.setValue(tran);
        	// Rendre le champ non modifiable si l'ID est défini
        	txtidprop.setEditable(false); 
        	txtidprop.setDisable(true);    
        	txttran.setDisable(true); 
        }
    }
    
	private void addTextChangeListeners() {
		txtmontant.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtdesc.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtidprop.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtsurf.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	    txtloc.textProperty().addListener((obs, oldVal, newVal) -> updateAjouterButtonState());
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	    // Autres initialisations
        if (Main.Biens.isEmpty() == false)
        {
        	table.setItems(Main.Biens);
        }
	    prixcolone.setCellValueFactory(new PropertyValueFactory<BienIM, Double>("prix"));
	    surfcolone.setCellValueFactory(new PropertyValueFactory<BienIM, Double>("superficie"));
	    loccolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("localisation"));
	    trancolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("tytran"));
	    etatcolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("etat"));
	    propcolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("proprietaire"));
	    actcolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("typeActe"));
	    typecolone.setCellValueFactory(new PropertyValueFactory<BienIM, String>("type"));
	    
	    // Configurer la valeur factory pour le Spinner
	    txtetat.getItems().addAll(e);
	    txttBien.getItems().addAll(b);    
	    txtact.getItems().addAll(a);
	    txttran.getItems().addAll(t);
        txtetat.setValue("LIBRE");
        txttBien.setValue("MAISON");
        txttran.setValue("VENTE");
        txtact.setValue("ACTE_NOTARIE");
        	    
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
    
	
	private void filterTable(String searchText) {
        ObservableList<BienIM> filteredList = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            table.setItems(Main.Biens);
            return;
        }
        // Parcourir la liste des acheteurs et ajouter ceux qui correspondent au critère de recherche
        for (BienIM c : table.getItems()) {
            if (c.getEtat().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getLocalisation().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getType().toLowerCase().contains(searchText.toLowerCase()) ||
                c.getTytran().toLowerCase().contains(searchText.toLowerCase()))
            {
                filteredList.add(c);
            }
        }

        // Mettre à jour la TableView avec la liste filtrée
        table.setItems(filteredList);
    }
	
	   
    @FXML
    void afficher(ActionEvent event) {
        try {
            
            BienIM Selectionne = table.getSelectionModel().getSelectedItem();
            if (Selectionne != null) {             
                String idcient = Selectionne.getProprietaire();
                String tran = Selectionne.getTytran();
                if (tran.equals("LOCATION"))
                {
                	for(Locataire a : Main.locataires)
                	{
                		if (a.getNum_id().equals(idcient))
                		{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherdetailleBien.fxml"));
                            Parent root = (Parent) loader.load();
                            controlleurAffichBien controlleur = loader.getController(); // Obtenir le contrôleur
                            if(controlleur != null) {
                                controlleur.afficherinfobien(Selectionne , a);
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } else {
                                System.err.println("Erreur : le contrôleur est null");
                            }
                		}
                	}
                }
                else 
                {
                	for(Vendeur a : Main.vendeurs)
                	{
                		if (a.getNum_id().equals(idcient))
                		{
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherdetailleBien.fxml"));
                            Parent root = (Parent) loader.load();
                            controlleurAffichBien controlleur = loader.getController(); // Obtenir le contrôleur
                            if(controlleur != null) {
                                controlleur.afficherinfobien(Selectionne , a);
                                Stage stage = new Stage();
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } else {
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
        String loc = txtloc.getText();
        String desc  = txtdesc.getText();
        String id  = txtidprop.getText();
        String mont =txtmontant.getText();
        String surf = txtsurf.getText();
        String etat = txtetat.getValue();
        String bien = txttBien.getValue();
        String act = txtact.getValue();
        String tran = txttran.getValue();

        if (verification.isValidNumeroIdentite(id) && verification.isValidDouble(mont) && verification.isValidDouble(surf) && verification.idExicte(id,tran )) {
            // Vérifier si l'élément existe déjà dans la liste
            boolean existeDeja = false;
            for (BienIM b : Main.Biens) {
                if (b.getLocalisation().equals(loc) &&
                    b.getDescription().equals(desc) && 
                    b.getPrix() == Double.parseDouble(mont) &&
                    b.getSuperficie() == Double.parseDouble(surf)&&
                    b.getEtat().equals(etat) &&
                    b.getType().equals(bien) &&
                    b.getTypeActe().equals(act) &&
                    b.getTytran().equals(tran)) {
                    existeDeja = true;
                    break;
                }
            }

            if (!existeDeja) {
                // L'élément n'existe pas encore, donc ajoutez-le à la liste
                BienIM b = new BienIM(bien, Double.parseDouble(surf), Double.parseDouble(mont), loc, desc, act, tran, id, etat);
                Main.Biens.add(b);
                table.setItems(Main.Biens);
                ajoutbt.setDisable(true);
            } else {
                // Afficher un message d'erreur si l'élément existe déjà dans la liste
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("L'élément que vous essayez d'ajouter existe déjà dans la liste.");
                alert.showAndWait();
            }
        }
    }


    @FXML
    void effacer(ActionEvent event) {

        txtdesc.clear();
        txtidprop.clear();
        txtmontant.clear();
        txtsurf.clear();
        txtloc.clear();
        // Effacer la sélection 
        txtetat.setValue("LIBRE");
        txttBien.setValue("MAISON");
        txttran.setValue("VENTE");
        txtact.setValue("ACTE_NOTARIE"); 
        table.getSelectionModel().clearSelection();
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }
    @FXML
    void modifier(ActionEvent event) {
        int selectedID = table.getSelectionModel().getSelectedIndex();
        BienIM selectionne = table.getSelectionModel().getSelectedItem();
        int index;
        if (!Main.Biens.isEmpty() && selectionne != null) {
            int idBien = selectionne.getIdentifiantBien();
            for (BienIM bien : Main.Biens) {
                if (bien.getIdentifiantBien() == idBien) {
                    index = Main.Biens.indexOf(bien);

                    // Comparer les valeurs actuelles avec les nouvelles valeurs saisies
                    String loc = txtloc.getText();
                    String desc = txtdesc.getText();
                    String id = txtidprop.getText();
                    String mont = txtmontant.getText();
                    String surf = txtsurf.getText();

                    if (loc.equals(bien.getLocalisation()) &&
                        desc.equals(bien.getDescription()) &&
                        mont.equals(String.valueOf(bien.getPrix())) &&
                        surf.equals(String.valueOf(bien.getSuperficie())) &&
                        txtetat.getValue().equals(bien.getEtat()) &&
                        txttBien.getValue().equals(bien.getType()) &&
                        txtact.getValue().equals(bien.getTypeActe()) &&
                        txttran.getValue().equals(bien.getTytran())) {

                        // Aucune modification n'a été apportée
                        // Afficher une alerte
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Aucune modification n'a été apportée.");
                        alert.showAndWait();
                    } else {
                    	if ( verification.isValidNumeroIdentite(id)&& verification.isValidDouble(mont) && verification.isValidDouble(surf))
                    	{
                    	// Modifier l'élément et mettre à jour la liste et la table
	                        bien.setLocalisation(loc);
	                        bien.setDescription(desc);
	                        bien.setEtat(txtetat.getValue());
	                        bien.setPrix(Double.parseDouble(mont));
	                        bien.setSuperficie(Double.parseDouble(surf));
	                        bien.setProprietaire(id);
	                        bien.setType(txttBien.getValue());
	                        bien.setTypeActe(txtact.getValue());
	                        bien.setTytran(txttran.getValue());
	
	                        Main.Biens.set(index, bien);
	                        table.getItems().set(selectedID, bien);
	                        
	                        
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
        BienIM Selectionne = table.getSelectionModel().getSelectedItem();
       
        // Vérifier si un élément est sélectionné
        if (Selectionne != null) {
        	table.getItems().remove(selectedID);
        	Main.Biens.remove(Selectionne);
        }
        table.getSelectionModel().clearSelection();
        updateModifierButtonState();
        updateSupprimerButtonState();
        updateAfficherButtonState();
    }

  
}
