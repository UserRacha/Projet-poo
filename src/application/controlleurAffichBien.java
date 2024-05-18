package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichBien {

    @FXML
    private Button okbt;

    @FXML
    private Label setdesc;

    @FXML
    private Label setetat;

    @FXML
    private Label setlocalisation1;

    @FXML
    private Label setnom;

    @FXML
    private Label setprenom;

    @FXML
    private Label setprix;

    @FXML
    private Label setsuperficie1;

    @FXML
    private Label settel;

    @FXML
    private Label settran;

    @FXML
    private Label settypeact;

    @FXML
    private Label settypebien1;

    @FXML
    private Label settypepay13;

    public void afficherinfobien(BienIM b, Client c)
    {
    	
    	setnom.setText(c.getNom());
    	settel.setText(c.getNumtelM());
    	setprenom.setText(c.getPrenom());
    	
    	setprix.setText(Double.toString(b.getPrix()));
    	setlocalisation1.setText(b.getLocalisation());
    	setdesc.setText(b.getDescription());
    	setsuperficie1.setText(Double.toString(b.getSuperficie()));
    	settypebien1.setText(b.getType());
    	setetat.setText(b.getEtat());
    	settran.setText(b.getTytran());
    	settypeact.setText(b.getTypeActe());
    		
    }
    
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();
    }

}
