package ProjetDebat.graphique;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import ProjetDebat.debat.Debat;

public class ControllerDebat implements Initializable {

    @FXML
    private Debat dataDebat;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDataDebat(Debat debat) {
        this.dataDebat = debat;
    }

	public Debat getDataDebat() {
		return dataDebat;
	}
    

}
