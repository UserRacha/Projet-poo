package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichB {

    @FXML
    private Button okbt;

    @FXML
    private Label setadresse;

    @FXML
    private Label setdure;

    @FXML
    private Label setid;

    @FXML
    private Label setlocalisation1;

    @FXML
    private Label setmontant1;

    @FXML
    private Label setnom;

    @FXML
    private Label setpref;

    @FXML
    private Label setprenom;

    @FXML
    private Label setsuperficie1;

    @FXML
    private Label settel;

    @FXML
    private Label settypebien1;

    
    
    public void afficherinfobien(Bailleur a)
    {
    	setnom.setText(a.getNom());
    	settel.setText(a.getNumtelM());
    	setprenom.setText(a.getPrenom());
    	setid.setText(a.getNum_id());
    	setadresse.setText(a.getAdresse());
    	setmontant1.setText(Double.toString(a.getMontant_max()));
    	setlocalisation1.setText(a.getLocalisation());
    	setpref.setText(a.getPreference());
    	setsuperficie1.setText(Double.toString(a.getSuperficie()));
    	settypebien1.setText(a.getType_b());
    	setdure.setText(Double.toString(a.getDure_loc()));	
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();

            
    }
	

}
