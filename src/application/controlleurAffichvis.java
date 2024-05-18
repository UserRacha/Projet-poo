package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class controlleurAffichvis {

    @FXML
    private Button okbt;

    @FXML
    private Label setbien;

    @FXML
    private Label setdate;

    @FXML
    private Label setemailagent;

    @FXML
    private Label setlocalisation;

    @FXML
    private Label setnom;

    @FXML
    private Label setnomagent;

    @FXML
    private Label setprenom;

    @FXML
    private Label setprenomagent;

    @FXML
    private Label settel;

    @FXML
    private Label settelagent;

    public void afficherinfobien(BienIM b, Client c , Visite v /*, agent a*/)
    {
    	
    	setnom.setText(c.getNom());
    	settel.setText(c.getNumtelM());
    	setprenom.setText(c.getPrenom());
    	
        setbien.setText(b.getType());
        setlocalisation.setText(b.getLocalisation());        
        setdate.setText(v.getDate().toString());
        /*
        setprenomagent.setText(a.getNom());
        setnomagent.setText(a.getPrenom());
        settelagent.setText(a.getTel());
        setemailagent.setText(a.getEmail());
    	*/	
    }
    
    @FXML
    void exitok(ActionEvent event) {
        Stage stage = (Stage) okbt.getScene().getWindow();
        stage.close();
    }

}

