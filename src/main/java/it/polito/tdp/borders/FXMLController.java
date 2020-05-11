
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    private ChoiceBox<Country> chStati;

    @FXML
    private Button btnTrova;
    
    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	try {
    		
    		int anno = Integer.parseInt(txtAnno.getText());
    		model.createGraph(anno);
    		
    		txtResult.setText("Elenco stati: "+model.elencoStatiConfini()+"\n");
    		txtResult.appendText("Componenti connesse: "+model.componentiConnesse());
    		
    	} catch(NumberFormatException e) {
    		txtResult.setText("Anno non corretto!");
    	} catch(Exception e) {
    		txtResult.setText("Errore!");
    	}
    	
    	chStati.getItems().addAll(model.getVerticiAttivi());
    	btnTrova.setDisable(false);
    }
    
    @FXML
    void doTrovaVicini(ActionEvent event) {
    	Country cTemp = chStati.getValue();
    	if(cTemp == null) {
    		txtResult.setText("Paese non selezionato");
    		return;
    	} else {
    		txtResult.setText(model.getVicini(cTemp).toString()+"\n\n");
    		txtResult.appendText(model.getVicini1(cTemp).toString()+"\n\n");
    		txtResult.appendText(model.getVicini2(cTemp).toString());
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert chStati != null : "fx:id=\"chStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTrova != null : "fx:id=\"btnTrova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        btnTrova.setDisable(true);
    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
