package ProjetDebat.graphique;

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
	public Set<Argument> setSolutionAdmissible(Set<Argument> derniereSolution) {
		alert.setHeaderText("Une solution admissible est :");
		derniereSolution = debat.getRs().getAdmissible();
		System.out.println("la derniere sol est : "+derniereSolution);
		alert.setContentText(derniereSolution.toString());
		return derniereSolution;
	}
	public Set<Argument> setSolutionPrefere(Set<Argument> derniereSolution) {
		System.out.println("la derniere sol est : "+derniereSolution);
		alert.setHeaderText("Une solution préféree est :");
		derniereSolution = debat.getRs().getPreferee();
		alert.setContentText(derniereSolution.toString());
		return derniereSolution;
	}

}

