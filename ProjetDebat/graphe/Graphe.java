package ProjetDebat.graphe;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;



public class Graphe {
	
	/**
	 * Classe qui represente un graphe à l'aide d'une HashMap
	 *
	 */
	
	private Map <Argument,ArrayList<Argument>> graphe;

	/**
	 * Initialise un graphe vide
	 */
	public Graphe() {
		graphe = new HashMap<>();
	}

	/**
	 * Initialise un graphe & y ajoute chaque argument de la liste passé en paramètre
	 *
	 *@param listArguments contient l'ensemble des arguments du débat
	 */
	public Graphe(List<Argument>listArguments) {
		
		graphe = new HashMap<>();
		for (Argument arg : listArguments) {
			ajouterNoeud(arg);
		}
	}
	
	/**
	 * ajoute dans le graphe l'argument passé en paramètre
	 *
	 *@param arg est l'argument que l'on veut insérer dans le graphe
	 */
	public void ajouterNoeud(Argument arg) {
		graphe.put(arg, null);
	}
	
	/**
	 * ajoute dans le graphe un arc permettant de signifier que 
	 * l'argument A1 contredit A2
	 * (si existe Ø : 	va créer une liste de contraction pour A1 et y insérer A2
	 * 	si existe : 	ajouter A2 dans la liste de contradiction de A1)
	 *
	 *@param  A1 est l'argument qui va contredire A2
	 *@param  A2 est l'argument qui est contredit par A1
	 */
	public void ajouterArc(Argument A1,Argument A2) {

		if (graphe.get(A1)==null) {
			ArrayList<Argument> list = new  ArrayList<Argument>();
			list.add(A2);
			graphe.put(A1, list);
		} 
		else {
			graphe.get(A1).add(A2);
		}
	}
	
	public Argument trouverArgNom(String nomArg) {
		
		for (Argument arg : graphe.keySet()) {
			if (arg.getNom().equals(nomArg)) {
				return arg ;
			}
		}
		System.out.println("L'argument : "+nomArg +"n'a pas été trouvé.");
		return null;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Argument arg : graphe.keySet()) {
			sb.append("\n"+ "L'"+arg.toString()+", contredit les arguments suivants : \n");
			
			if (graphe.get(arg)!=null) {
				for (Argument ctr : graphe.get(arg)) {
					sb.append("\n\t "+ctr+"\n");
				}
			}
		}
		return sb.toString();
		
	}

	public Map<Argument, ArrayList<Argument>> getGraphe() {
		return graphe;
	}


	
}	


