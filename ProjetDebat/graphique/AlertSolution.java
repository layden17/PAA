package ProjetDebat.graphique;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ProjetDebat.debat.Debat;
import ProjetDebat.graphe.Argument;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertSolution  {

	private Alert alert;
	private Debat debat;
	
	public AlertSolution(Debat debat,String title) {
		this.debat = debat;
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
	}

	public void showAlert() {
		alert.showAndWait();
	}
	public void setSolutionAdmissible(Set<Argument> derniereSolution) {
		alert.setHeaderText("Une solution admissible est :");
		derniereSolution = debat.getRs().getAdmissible();
		alert.setContentText(derniereSolution.toString());
	}
	public void setSolutionPrefere(Set<Argument> derniereSolution) {
		alert.setHeaderText("Une solution préféree est :");
		derniereSolution = debat.getRs().getAdmissible();
		alert.setContentText(debat.getRs().getPreferee().toString());
	}

}

