package ProjetDebat;


import java.util.Scanner;

import ProjetDebat.graphique.*;
import javafx.application.Application;
import ProjetDebat.debat.*;
import ProjetDebat.file.*;

public class TestDebat {

	public static void main(String[] args) {
		
		System.out.print("Interface Graphique ? (Y/N)\n--> :");
		Scanner scanner = new Scanner(System.in);
		//char choix = scanner.next().charAt(0);
		char choix = 'Y';
		boolean affichageGraphique = false;
		
		if (args.length>0) {
			System.out.println("Le graphe a été généré à partir du fichier entré en paramètre :\n");
			System.out.println(args[0]+"\n");
			FichierDebat fd = new FichierDebat(args[0]);
			fd.lireFichier();
			fd.creerGrapheFichier();
			Debat debat = new Debat(fd.getGrapheArg());
			//debat.afficheGraphe();
			if (choix == 'Y') {
				affichageGraphique = true;
			}
			debat.affichageMenuRechercheSolution(affichageGraphique);
			
			if (choix == 'Y') {
				DebatGraphique.main(args, debat);

			}
			
		}
		else {
			int nbArg = Debat.demanderNombreArgument();
			System.out.println("Vous avez indiqué qu'il y'a "  + nbArg+ " arguments à entrer.\n");	
			Debat debat = new Debat(nbArg);
			System.out.println("Le graphe est :\n");
			debat.afficheGraphe();
			
			debat.affichageMenuContradiction();
			debat.affichageMenuSolution();
		}

	}

}
