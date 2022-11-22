package ProjetDebat.debat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ProjetDebat.graphe.Argument;
import ProjetDebat.graphe.Graphe;
import ProjetDebat.util.*;

public class RechercheSolution {

	private Graphe grapheArg;
	private ArrayList<ArrayList<Argument>> listeCombinaisonsArguments ;
	private List<HashSet<Argument>> listeSolutionsAdmissible ;
	private int cptSolAdmissible =0;
	
	public RechercheSolution(Graphe grapheArg) {
		
		this.grapheArg = grapheArg;
		listeCombinaisonsArguments = new ArrayList<ArrayList<Argument>>();
		listeSolutionsAdmissible = new ArrayList<HashSet<Argument>>();
	}
	
	public void construireListeSolutionAdmissible() {
		VerifSolution vf;
		HashSet<Argument> ensSolutionPotentielle ;
		
		for (List<Argument> solutionPotentielle : listeCombinaisonsArguments) {
			ensSolutionPotentielle = new HashSet<Argument>(solutionPotentielle);
			vf = new VerifSolution(ensSolutionPotentielle, grapheArg,true);

			if (vf.verifSolution()) {
				listeSolutionsAdmissible.add(ensSolutionPotentielle);
			}
		}

		
	}
	public Set<Argument> getAdmissible() {

		if (cptSolAdmissible == listeSolutionsAdmissible.size()) {
			cptSolAdmissible = 0;
		}
		
		return listeSolutionsAdmissible.get(cptSolAdmissible++);
		
	}
	
	public void construireListeCombinaisons() {
		ArrayList<Argument> listArguments = new ArrayList<>();
		ArrayList<Argument> data = new ArrayList<>();
		ArrayList<ArrayList<Argument>> listeSousCombinaisonsArguments ;
		
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			listArguments.add(argument);
		}
		for (int tailleCombinaison = 1; tailleCombinaison <= listArguments.size(); tailleCombinaison++) {
			listeSousCombinaisonsArguments = new ArrayList<ArrayList<Argument>>() ;
			Combinaison.combinaisonUtil(listArguments, data,listeSousCombinaisonsArguments, 0, listArguments.size()-1, 0, tailleCombinaison,0);
			listeCombinaisonsArguments.addAll(listeSousCombinaisonsArguments);
		}
		
	}

	public ArrayList<ArrayList<Argument>> getListeCombinaisonsArguments() {
		return listeCombinaisonsArguments;
	}
	
	public void afficheListeSolutionAdmissible() {
		int cpt=1;
		for (HashSet<Argument> solutionAdmissible : listeSolutionsAdmissible) {
			System.out.println(cpt+") "+ solutionAdmissible);
			cpt++;
		}
	}
	
}
