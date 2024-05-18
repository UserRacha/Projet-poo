package application;

import java.time.LocalDate;
import java.time.ZoneId;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ContolleurContrat {

	   @FXML
	    private AnchorPane Location;

	    @FXML
	    private Label Nombail;

	    @FXML
	    private Label Nombail1;

	    @FXML
	    private Label Nomven;

	    @FXML
	    private Label Nomven1;

	    @FXML
	    private AnchorPane Vente;

	    @FXML
	    private Label achcord;

	    @FXML
	    private Label adrbien;

	    @FXML
	    private Label coordbail;

	    @FXML
	    private Label coordloc;

	    @FXML
	    private Label datefin;

	    @FXML
	    private Label dateprevu;

	    @FXML
	    private Label dure;

	    @FXML
	    private Label local;

	    @FXML
	    private Label monach;

	    @FXML
	    private Label monach1;

	    @FXML
	    private Label nomloc;

	    @FXML
	    private Label nomloc1;

	    @FXML
	    private Label preach;
	    @FXML
	    private Label preach1;

	    @FXML
	    private Label prebail;

	    @FXML
	    private Label preloc;

	    @FXML
	    private Label preven;
	    @FXML
	    private Label preven1;
	    @FXML
	    private Label prix;

	    @FXML
	    private Label prix1;

	    @FXML
	    private Label prix2;

	    @FXML
	    private Label surf;

	    @FXML
	    private Label typedebien;

	    @FXML
	    private Label typepay;

	    @FXML
	    private Label vencord;
    
    public static Bailleur trouverBailleurParId(String id) {
        for (Bailleur bailleur : Main.bailleurs) {
            if (bailleur.getNum_id().equals(id)) {
                return bailleur;
            }
        }
        return null; // Si aucun bailleur trouvé avec l'ID donné
    }

    // Méthode pour trouver un achteur par ID
    public static Acheteur trouverAchteurParId(String id) {
        for (Acheteur achteur : Main.achteurs) {
            if (achteur.getNum_id().equals(id)) {
                return achteur;
            }
        }
        return null; // Si aucun achteur trouvé avec l'ID donné
    }

    // Méthode pour trouver un locataire par ID
    public static Locataire trouverLocataireParId(String id) {
        for (Locataire locataire : Main.locataires) {
            if (locataire.getNum_id().equals(id)) {
                return locataire;
            }
        }
        return null; // Si aucun locataire trouvé avec l'ID donné
    }

    // Méthode pour trouver un vendeur par ID
    public static Vendeur trouverVendeurParId(String id) {
        for (Vendeur vendeur : Main.vendeurs) {
            if (vendeur.getNum_id().equals(id)) {
                return vendeur;
            }
        }
        return null; // Si aucun vendeur trouvé avec l'ID donné
    }
    
    public static BienIM trouverBienParId(int id) {
        for (BienIM bien : Main.Biens) {
            if (bien.getIdentifiantBien()== id) {
                return bien;
            }
        }
        return null; // Si aucun bien trouvé avec l'ID donné
    }

    public void forme_a_afficher(Transaction t)
    {
    	BienIM b = trouverBienParId(t.getId_bien());
    	if (t.getTypetran().equals("VENTE"))
    	{
    		Vendeur v = trouverVendeurParId(t.getMembre1());
    		Acheteur a = trouverAchteurParId(t.getMembre2());
    		
    		Nomven.setText(v.getNom());
    		Nomven1.setText(v.getNom());
    		achcord.setText(a.getNum_id());
    		local.setText(b.getLocalisation());
    		monach.setText(a.getNom());
    		monach1.setText(a.getNom());
    		preach.setText(a.getPrenom());
    		preach1.setText(a.getPrenom());
    		preven.setText(v.getPrenom());
    		preven1.setText(v.getPrenom());
    		prix2.setText(Double.toString(b.getPrix()));
    		surf.setText(Double.toString(b.getSuperficie()));
    		typedebien.setText(b.getType());
    		typepay.setText(a.getTypePay());
    		vencord.setText(v.getNum_id()); 		
    		
    		Vente.setVisible(true);
    		Location.setVisible(false);
    	}
    	else
    	{
    		Locataire l = trouverLocataireParId(t.getMembre1());
    		Bailleur bl = trouverBailleurParId(t.getMembre2());
    		Nombail.setText(bl.getNom());
    		prebail.setText(bl.getPrenom());
    		Nombail1.setText(bl.getNom());
    		adrbien.setText(b.getLocalisation());
    		coordbail.setText(bl.getNum_id());
    		coordloc.setText(l.getNum_id());
    		dateprevu.setText(t.getDate().toString());
    		dure.setText(Integer.toString(bl.getDure_loc())); 
            LocalDate dateDebut = t.getDate();
            LocalDate dateFin = dateDebut.plusMonths(bl.getDure_loc());
            datefin.setText(dateFin.toString());
            nomloc.setText(l.getNom());
            nomloc1.setText(l.getNom());
            preloc.setText(l.getPrenom());
            prix.setText(Double.toString(b.getPrix()));
            prix1.setText(Double.toString(b.getPrix()));
         		
    		Vente.setVisible(false);
    		Location.setVisible(true);
    	}
    }
    
    
}
