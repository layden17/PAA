package ProjetDebat.graphe;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Graphe {
	
	private Map <Argument,ArrayList<Argument>> graphe;
	
	public Graphe() {
		graphe = new HashMap<>();
	}

	public Graphe(List<Argument>listArguments) {
		
		graphe = new HashMap<>();
		for (Argument arg : listArguments) {
			ajouterNoeud(arg);
		}
	}
	
	public void ajouterNoeud(Argument arg) {
		graphe.put(arg, null);
	}
	
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
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Argument arg : graphe.keySet()) {
			sb.append("\n"+ arg.toString()+" Contredit : \n");
			
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


