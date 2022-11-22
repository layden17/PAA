package ProjetDebat;

import ProjetDebat.debat.*;
import ProjetDebat.file.*;

public class TestDebat {

	public static void main(String[] args) {
		
		
		if (args.length>0) {
			System.out.println("Le graphe a été généré à partir du fichier entré en paramètre :\n");
			System.out.println(args[0]+"\n");
			FichierDebat fd = new FichierDebat(args[0]);
			fd.lireFichier();
			//System.out.println(fd.getContenu());
			fd.creerGrapheFichier();
			Debat debat = new Debat(fd.getGrapheArg());
			debat.afficheGraphe();
			debat.affichageMenuRechercheSolution();
			
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
