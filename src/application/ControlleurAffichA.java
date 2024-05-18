package application;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ControlleurAffichA {

	@FXML
    private Button okbt;

    @FXML
    private Label setadresse;

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

    @FXML
    private Label settypepay1;

    
    public void afficherinfobien(Acheteur a)
    {
    	setnom.setText(a.getNom());
    	settel.setText(a.getNumtelM());
    	setprenom.setText(a.getPrenom());
    	setid.setText(a.getNum_id());
    	setadresse.setText(a.getAdresse());
    	setmontant1.setText(Double.toString(a.getMontMax()));
    	setlocalisation1.setText(a.getLocalBD());
    	setpref.setText(a.getPrefenrence());
    	setsuperficie1.setText(Double.toString(a.getSuperficieBD()));
    	settypebien1.setText(a.getTypeBienD());
    	settypepay1.setText(a.getTypePay());	
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        
    	Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();
            
    }
	
}
