package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichAgent {
    @FXML
    private Button okbt;

    @FXML
    private Label setadresse;


    @FXML
    private Label setid;

    @FXML
    private Label settel;

    @FXML
    private Label setnom;

    @FXML
    private Label setpassword;

    @FXML
    private Label setprenom;



    
    
    public void afficherinfobien(AgentIMM a)
    {
    	setnom.setText(a.getNom());
    	settel.setText(a.getTelephone());
    	setprenom.setText(a.getPrenom());
    	setid.setText(a.getId());
    	setadresse.setText(a.getAdresse());
    	setpassword.setText(a.getPassword());	
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();

            
    }
}
