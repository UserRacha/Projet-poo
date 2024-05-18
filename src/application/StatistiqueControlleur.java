package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.BorderPane;

public class StatistiqueControlleur implements Initializable{
    private static final String S = "SATISFAIT";
	private static final String A = "ANNULER";
	private static final String E = "EN ATTENTE";
	int satisfait = 0;
    int annule = 0;
    int enAttente = 0 ;
    double [] benficie = new double[12];

    @FXML
    private BorderPane borderPane;
	
    @FXML
    private LineChart<String , Number>chart;

    @FXML
    private CategoryAxis Yaxe;

    @FXML
    private NumberAxis Xaxe;

    public void calculerTransaction() {
      
        for (Transaction t : Main.Transactions) {
            LocalDate date = t.getDate();
            int mois = date.getMonthValue() - 1; // le mois commence à partir de 0
        	benficie[mois] = benficie[mois]  + t.getBénéfice();
            
        }

    }


    
    @FXML
    void Linechart(ActionEvent event) {
        Yaxe.setLabel("Mois");
        Xaxe.setLabel("Transactions");

        // Clear the existing data in the chart
        chart.getData().clear();

        XYChart.Series<String , Number> series = new XYChart.Series<>();
        String[] mois = {
       		    "Janvier",
       		    "Février",
       		    "Mars",
       		    "Avril",
       		    "Mai",
       		    "Juin",
       		    "Juillet",
       		    "Août",
       		    "Septembre",
       		    "Octobre",
       		    "Novembre",
       		    "Décembre"
       		};
         for (int i =0; i<12 ; i++)
         {
             series.getData().add(new XYChart.Data<>(mois[i], benficie[i]));

         }
         
       // Ajout des séries de données au LineChart
       chart.getData().add(series );
       
       // Set the style of the series after adding them to the chart
       series.getNode().setStyle("-fx-line-color: red;");  
        borderPane.setCenter(chart);
    }

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		calculerNombre();
		calculerTransaction();
        Yaxe.setLabel("Mois");
        Xaxe.setLabel("Transactions");

        XYChart.Series<String , Number> series = new XYChart.Series<>();
         String[] mois = {
        		    "Janvier",
        		    "Février",
        		    "Mars",
        		    "Avril",
        		    "Mai",
        		    "Juin",
        		    "Juillet",
        		    "Août",
        		    "Septembre",
        		    "Octobre",
        		    "Novembre",
        		    "Décembre"
        		};
          for (int i =0; i<12 ; i++)
          {
              series.getData().add(new XYChart.Data<>(mois[i], benficie[i]));

          }
          
        // Ajout des séries de données au LineChart
        chart.getData().add(series );
        
        // Set the style of the series after adding them to the chart
        series.getNode().setStyle("-fx-line-color: red;");    
        borderPane.setCenter(chart);
			
	}
	
	
	    // Méthode pour calculer le nombre de locataires dans chaque état
	    public void calculerNombre() {
	    	
	        satisfait = 0;
	        annule = 0;
	        enAttente = 0;
	        for (Locataire locataire : Main.locataires) {
	            switch (locataire.getEtat()) {
	                case S:
	                    satisfait++;
	                    break;
	                case A:
	                    annule++;
	                    break;
	                case E:
	                    enAttente++;
	                    break;
	            }
	        }
	        
	        for (Bailleur bailleur : Main.bailleurs) {
	            switch (bailleur.getEtat()) {
                case S:
                    satisfait++;
                    break;
                case A:
                    annule++;
                    break;
                case E:
                    enAttente++;
                    break;
	            }
	        }        
	     
	    }

	
    @FXML
    private void peichart(ActionEvent event) {
    	calculerNombre();
    	
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
            new PieChart.Data("Satisfait", satisfait),
            new PieChart.Data("Annuler", annule),
            new PieChart.Data("En attente", enAttente)
    );

    PieChart pieChart = new PieChart(pieChartData); //Creating a Pie chart      


    pieChart.setTitle("Client"); //Setting the title of the Pie chart
    pieChart.setClockwise(true); //setting the direction to arrange the data 
    pieChart.setLabelLineLength(50); //Setting the length of the label line 
    pieChart.setLabelsVisible(true); //Setting the labels of the pie chart visible
    pieChart.setLegendVisible(false);
    pieChart.setStartAngle(180);

    
    borderPane.setCenter(pieChart)    ;
    }
}