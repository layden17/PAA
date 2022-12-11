package ProjetDebat.graphique;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ProjetDebat.debat.Debat;
import ProjetDebat.graphe.Argument;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import up.mi.jgm.td1corrige.ExceptionMenu;

public class PaneMenu extends Pane{
	
	private Label lab;
	private Button choiceButton;
	private List<RadioButton> listButton;
	private ToggleGroup group ;
	private int cptButton=0;
	private Debat debat;
	private AlertSolution alertSolution;
	private boolean savePossible = false;
	private String cheminFichier;
	private Set<Argument> derniereSolution;
	private int cptArg;
	private AlertError alertError;
	private TextInputDialog alertText;
	

	public PaneMenu(double screenWidth,double screenHeight,ControllerDebat controller) {
		
		this.debat = controller.getDataDebat();
		alertSolution = new AlertSolution(debat,"Affichage Solution");
		derniereSolution = new HashSet<Argument>();
		group = new ToggleGroup();
		lab = new Label("Que souhaitez vous faire");
		choiceButton = new Button("Ok");
		listButton = new ArrayList<RadioButton>();
		listButton.add(new RadioButton("1) Chercher une solution admissible"));
		listButton.add(new RadioButton("2) Chercher une solution préférée"));
		listButton.add(new RadioButton("3) Sauvegarder la solution"));
		listButton.add(new RadioButton("4) Fin"));
		
		for (RadioButton radioButton : listButton) {
			radioButton.setToggleGroup(group);
			radioButton.setPrefSize(400, 30);
			radioButton.setFont(new Font("Calibri", 20));
			radioButton.relocate(200, 400 + 50*cptButton++);
			this.getChildren().add(radioButton);
		}
		
		lab.setPrefSize(500, 60);
		lab.setFont(new Font("Calibri", 30));
		lab.setTextFill(Color.web("#0076a3"));
		lab.relocate(screenWidth/2-150, 100);
		
		choiceButton.setPrefSize(120, 50);
		choiceButton.setFont(new Font("Calibri", 35));
		choiceButton.relocate(screenWidth/2-60, screenHeight*0.85);
		alertText = new TextInputDialog();
		alertText.setHeaderText("Chemin du fichier à sauvegarder ?");
		alertText.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);

			
		
		
		choiceButton.setOnAction( (event)->{
			
			if (listButton.get(0).isSelected()) {
				derniereSolution = alertSolution.setSolutionAdmissible(derniereSolution);
				alertSolution.showAlert();
				listButton.get(0).setSelected(false);
				savePossible=true;
			}
			if (listButton.get(1).isSelected()) {
				derniereSolution = alertSolution.setSolutionPrefere(derniereSolution);
				alertSolution.showAlert();
				listButton.get(1).setSelected(false);
				savePossible = true;
			}
			if (listButton.get(2).isSelected()) {
				
				try { 
					if (!savePossible) {
						throw new ExceptionMenu("\nVeuillez d'abord chercher une solution admissible ou préféree.\n");
					}
					alertText.showAndWait();
					cheminFichier = alertText.getEditor().getText() ;
					File fichierSauvegarde = new File(cheminFichier);
					try (BufferedWriter bw = new BufferedWriter(new FileWriter(fichierSauvegarde))) {
						
						for (Argument argument : derniereSolution) {
							if (cptArg>0) {
								bw.write(",");
							}
							bw.write(argument.toString().substring(9, argument.toString().length()));
							cptArg++;
						}
						
					}catch (FileNotFoundException e) {
						System.out.print("Fichier non trouvé");
					}
					catch (IOException e) {
						System.out.print("Erreur IOException");
					}
					listButton.get(2).setSelected(false);
				}
				catch (ExceptionMenu e) {
					alertError = new AlertError("Erreur save Solution", e);	
					alertError.showAlert();
				}
			
			}
			if(listButton.get(3).isSelected()) {
				System.exit(0);
			}
				
		});
	
		
		this.getChildren().addAll(choiceButton,lab);	
	}

}

