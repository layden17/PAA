package ProjetDebat;

import ProjetDebat.debat.*;
import ProjetDebat.file.*;

public class TestDebat {

	public static void main(String[] args) {
		
//		List <Argument> listArgs = new ArrayList<Argument>();
//		
//		Argument A = new Argument("a");
//		Argument B = new Argument("b");
//		Argument C = new Argument("c");
//		Argument D = new Argument("d");
//		Argument E = new Argument("e");
//
//		listArgs.add(A);listArgs.add(B);listArgs.add(C);listArgs.add(D);listArgs.add(E);
		
//		Graphe grapheArg =  new Graphe(listArgs);
//		grapheArg.ajouterContradiction(A, B);
//		grapheArg.ajouterContradiction(A, E);
//		grapheArg.ajouterContradiction(B, A);
//		grapheArg.ajouterContradiction(B, C);
//		grapheArg.ajouterContradiction(C, B);
//		grapheArg.ajouterContradiction(C, D);
//		grapheArg.ajouterContradiction(D, C);
//		grapheArg.ajouterContradiction(E, D);
		
		if (args.length>0) {
			System.out.println("Le graphe a été généré à partir du fichier entré en paramètre :\n");
			System.out.println(args[0]+"\n");
			FichierDebat fd = new FichierDebat(args[0]);
			fd.lireFichier();
			//System.out.println(fd.getContenu());
			fd.creerGrapheFichier();
			Debat debat = new Debat(fd.getGrapheArg());
			debat.afficheGraphe();
			debat.affichageMenuSolution();
			
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
