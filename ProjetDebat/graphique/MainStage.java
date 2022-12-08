package ProjetDebat.graphique;

import javafx.scene.paint.*;
import ProjetDebat.debat.Debat;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Application{
	
	private double screenWidth;
	private double screenHeight;
	private Scene scene;
	private Pane paneMenu;
	
	private static ControllerDebat controller;

	public void start(Stage stage) throws Exception {
	

		stage.setTitle("Affichage Menu");
		screenWidth = 1368;
		screenHeight = 768;

		paneMenu = new PaneMenu(screenWidth,screenHeight,controller);

	    
		this.scene = new Scene(paneMenu,screenWidth,screenHeight);
		stage.initStyle(StageStyle.DECORATED);
		paneMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
		
		scene.setFill(new RadialGradient(
		        0, 0, 0, 0, 1, true,                  
		        CycleMethod.NO_CYCLE,                 
		        new Stop(0, Color.web("#81c483")),    
		        new Stop(1, Color.web("#fcc200")))
		);
		stage.setScene(scene);
		stage.sizeToScene();
		stage.show();
	    
	}

	public void setController(ControllerDebat controller){
		MainStage.controller = controller;
	}
}
