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
	private List<HashSet<Argument>> listeSolutionsPreferee ;
	private int cptSolAdmissible =0;
	private int cptSolPreferee = 0;
	private VerifSolution vf;
	
	public RechercheSolution(Graphe grapheArg) {
		
		this.grapheArg = grapheArg;
		listeCombinaisonsArguments = new ArrayList<ArrayList<Argument>>();
		listeSolutionsAdmissible = new ArrayList<HashSet<Argument>>();
		listeSolutionsPreferee = new ArrayList<HashSet<Argument>>();
		vf = new VerifSolution(null, grapheArg,true);
	}
	
	public void construireListeSolutionAdmissible() {
		HashSet<Argument> ensSolutionPotentielle ;
		
		for (List<Argument> solutionPotentielle : listeCombinaisonsArguments) {
			ensSolutionPotentielle = new HashSet<Argument>(solutionPotentielle);
			vf.setSolutionPotentielle(ensSolutionPotentielle);

			if (vf.verifSolutionAdmissible()) {
				listeSolutionsAdmissible.add(ensSolutionPotentielle);
			}
		}
	}
	public void construireListeSolutionPrefere() {
		

		for (HashSet<Argument> solutionPotentiellePreferee : listeSolutionsAdmissible) {

			vf.setSolutionPotentielle(solutionPotentiellePreferee);

			if (vf.verifSolutionPrefere(listeSolutionsAdmissible)) {
				listeSolutionsPreferee.add(solutionPotentiellePreferee);
			}
		}
	}
	public Set<Argument> getAdmissible() {

		if (cptSolAdmissible == listeSolutionsAdmissible.size()) {
			cptSolAdmissible = 0;
		}
		
		return listeSolutionsAdmissible.get(cptSolAdmissible++);
		
	}
	public Set<Argument> getPreferee() {

		if (cptSolPreferee == listeSolutionsPreferee.size()) {
			cptSolPreferee = 0;
		}

		return listeSolutionsPreferee.get(cptSolPreferee++);
		
	}
	
	public void construireListeCombinaisons() {
		ArrayList<Argument> listArguments = new ArrayList<>();
		ArrayList<Argument> listeCombinaison = new ArrayList<>();
		ArrayList<ArrayList<Argument>> listeSousCombinaisonsArguments ;
		
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			listArguments.add(argument);
		}
		for (int tailleCombinaison = 1; tailleCombinaison <= listArguments.size(); tailleCombinaison++) {
			listeSousCombinaisonsArguments = new ArrayList<ArrayList<Argument>>() ;
			Combinaison.combinaisonUtil(listArguments, listeCombinaison,listeSousCombinaisonsArguments, 0, listArguments.size()-1, 0, tailleCombinaison,0);
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
	public void afficheListeSolutionPreferee() {
		int cpt=1;
		for (HashSet<Argument> solutionAdmissible : listeSolutionsPreferee) {
			System.out.println(cpt+") "+ solutionAdmissible);
			cpt++;
		}
	}
	
}
