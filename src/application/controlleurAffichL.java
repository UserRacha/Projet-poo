package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichL {

    @FXML
    private Button okbt;

    @FXML
    private Label setadresse;

    @FXML
    private Label setdure;

    @FXML
    private Label setid;

    @FXML
    private Label setnom;

    @FXML
    private Label setprenom;

    @FXML
    private Label settel;

    public void afficherinfobien(Locataire a)
    {
    	setnom.setText(a.getNom());
    	settel.setText(a.getNumtelM());
    	setprenom.setText(a.getPrenom());
    	setid.setText(a.getNum_id());
    	setadresse.setText(a.getAdresse());
    	setdure.setText(Integer.toString(a.getDureloc_prevu()));	
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();

            
    }

}
