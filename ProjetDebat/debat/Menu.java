package ProjetDebat.debat;
import java.util.Set;

import ProjetDebat.graphe.Argument;



public class Menu {

	
	
	
	public static void menuSolution(Set<Argument> solutionPotentielle) {
		System.out.println("\n\nLa solution potentielle est : \t"+solutionPotentielle+"\n");
		System.out.println("\nQuelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1 Ajouter un argument");
		System.out.println("2 Retirer un argument");
		System.out.println("3 Vérifier la solution");
		System.out.println("4 Fin");
		
	}
	
	public static void menuContradiction() {
		System.out.println("Quelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1 Ajouter une contradiction");
		System.out.println("2 Quitter");
	}

}
