package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ControlleurLog implements Initializable{

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;
	 @FXML
	    private AnchorPane log;

	    @FXML
	    private AnchorPane pagechoix;


	    
	    
	    @FXML
	    void Statistique(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistique.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }
	    
	    @FXML
	    void Gestiobvendeur(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendeur.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestionbailleur(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bailleur.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestionbien(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Bien.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestionlocataire(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Locataire.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestiontransaction(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Transaction.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestionvisite(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Visite.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void gestionacheteur(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Acheteur.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }

	    @FXML
	    void Gestionagent(ActionEvent event) {
	    	 try {
		            FXMLLoader loader = new FXMLLoader(getClass().getResource("Agent.fxml"));
		            Parent root = (Parent) loader.load();
		                Stage stage = new Stage();
		                Scene scene = new Scene(root);
		                stage.setScene(scene);
		                stage.show();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	    }
	
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        // Charger et afficher l'image agence.jpg dans image1
        Image agenceImage = new Image(getClass().getResourceAsStream("/application/agence.jpeg"));
        image1.setImage(agenceImage);
        // Définit la taille de l'image
        image1.setFitWidth(200); 
        image1.setFitHeight(136); 

        // Charger et afficher l'image agence2.jpg dans image2
        Image agence2Image = new Image(getClass().getResourceAsStream("/application/agence2.jpg"));
        image2.setImage(agence2Image);
        // Définit la taille de l'image
        image2.setFitWidth(200); // Définit la largeur souhaitée de l'image
        image2.setFitHeight(140);
		
	}

}
