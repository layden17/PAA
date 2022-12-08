package ProjetDebat.graphique;

import ProjetDebat.debat.Debat;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertError  {

	private Alert alert;
	private Debat debat;
	
	public AlertError(String title, Exception error) {
		this.debat = debat;
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText("Erreur sauvegarde fichier.");
		alert.setContentText(error.getMessage());
	}

	public void showAlert() {
		alert.showAndWait();
	}


}

