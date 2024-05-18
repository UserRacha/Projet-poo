package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichV {

    @FXML
    private Button okbt;

    @FXML
    private Label setadresse;

    @FXML
    private Label setid;

    @FXML
    private Label setnom;

    @FXML
    private Label setpay;

    @FXML
    private Label setprenom;

    @FXML
    private Label settel;


    public void afficherinfobien(Vendeur a)
    {
    	setnom.setText(a.getNom());
    	settel.setText(a.getNumtelM());
    	setprenom.setText(a.getPrenom());
    	setid.setText(a.getNum_id());
    	setadresse.setText(a.getAdresse());
    	setpay.setText(a.getTypePay());	
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();

            
    }

}
