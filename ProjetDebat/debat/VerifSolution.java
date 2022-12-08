package ProjetDebat.debat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.net.ssl.CertPathTrustManagerParameters;

import ProjetDebat.graphe.Argument;
import ProjetDebat.graphe.Graphe;

/**
 * Classe permettant la vérification d'une solution potentielle
 * Contient en paramètre tous les ensembles nécessaires à cette vérification
 */

public class VerifSolution {

	private Set <Argument> argContreditsParSp ;
	private Set<Argument> solutionPotentielle ;
	private Graphe grapheArg ;
	private boolean supressStdout ;


	

	public VerifSolution(Set<Argument> solutionPotentielle, Graphe grapheArg) {
		
		
	    this.solutionPotentielle = solutionPotentielle ;
		this.grapheArg = grapheArg ;
		argContreditsParSp = new HashSet<Argument>();
		supressStdout = false;
	}
	
	public VerifSolution(Set<Argument> solutionPotentielle, Graphe grapheArg,boolean supressStdout) {
		
		
	    this.solutionPotentielle = solutionPotentielle ;
		this.grapheArg = grapheArg ;
		argContreditsParSp = new HashSet<Argument>();
		this.supressStdout = supressStdout;
	}

	/**
	 * Vérifie si solution potentielle est une solution admissible
	 * 
	 * @return true si la solution vérifie les 2 conditions, faux sinon
	 */
	
	public boolean verifSolutionAdmissible() {
		
	
		
		// 1ere condition : pas de contradiction interne
		boolean pasContradictionInterne;
		pasContradictionInterne = verifContradictionInterne();
		
		if (pasContradictionInterne == false) {
			return false;
		}
		// 2e condition : chaque argument contredit de E est défendu par un élement de E
		//Donc si arg de solutionPotentielle est dans allArgContredits, l'argument qui le contredit doit être dans argContreditParSp 
		boolean argDefendus;
		argDefendus = verifArgDefendu();
		
		return argDefendus;
	}
	
	
	
	private boolean verifContradictionInterne() {
		
		for (Argument arg : solutionPotentielle) {
			if (grapheArg.getGraphe().get(arg)!=null) {
				for (Argument argContredit : grapheArg.getGraphe().get(arg)) {
					if (solutionPotentielle.contains(argContredit)) {
						if (!supressStdout) {
							System.out.println("\nLa solution n'est pas admissible car contradiction"
									+ " interne :\n\n\t"+arg+" contredit :\t"+argContredit+"\n\t");
						}
						return false;
					}
					argContreditsParSp.add(argContredit); // on construit ensemblecontredits pour la 2e condition dans le cas ou la 1ere condition est vraie
				}
			}
			
		}
		return true;

	}
	
	
	private boolean verifArgDefendu() {
		Set <Argument> allArgumentsContredits = new HashSet<Argument>();
		Set <Argument> allArgumentsExceptSolutionPotentielle = new HashSet<Argument>();
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			if (!solutionPotentielle.contains(argument)) {
				allArgumentsExceptSolutionPotentielle.add(argument);
			}
		}
		
		for (Argument arg : grapheArg.getGraphe().keySet()) {		// On construit l'ensemble de tous les arguments contredits 	
				if (grapheArg.getGraphe().get(arg)!=null) {
					for (Argument arg2 : grapheArg.getGraphe().get(arg)) {  // (c-a-d l'ensemble des valeurs du graphe)	
						allArgumentsContredits.add(arg2);
					}
				}
		}
		
		for (Argument arg : solutionPotentielle) {
			if (allArgumentsContredits.contains(arg)) { // c-a-d si l'argument est contredit par un autre 
				for (Argument argExceptSp : allArgumentsExceptSolutionPotentielle) {
					if (grapheArg.getGraphe().get(argExceptSp)!=null) {
						if (grapheArg.getGraphe().get(argExceptSp).contains(arg)) {
							if (!argContreditsParSp.contains(argExceptSp)) {	//un argument se défend en contredisant l'argument qui le contredit
								if (!supressStdout) {
									System.out.println("La solution potentielle n'est pas admissible car aucun argument "
											+ "ne défend l'"+arg+" contredit par "+argExceptSp);
								}
								
								return false;
							}
						}
					}
					
				}
														
			}
		}
		return true;
	}
	
	
	
	public void setSolutionPotentielle(Set<Argument> solutionPotentielle) {
		this.solutionPotentielle = solutionPotentielle;
	}

	public boolean verifSolutionPrefere(List<HashSet<Argument>> listeSolutionAdmissible) {

		List<HashSet<Argument>> listeSolutionAdmissibleExceptSolPot = new ArrayList<HashSet<Argument>>(listeSolutionAdmissible);
		listeSolutionAdmissibleExceptSolPot.remove(solutionPotentielle);
		int cpt = 0;
		
		for (HashSet<Argument> solutionAdmissible : listeSolutionAdmissibleExceptSolPot) {
			cpt =0;
			for (Argument argument : solutionPotentielle) {
				if (solutionAdmissible.contains(argument)) {
					cpt ++ ;
				}
				if (cpt == solutionPotentielle.size()) {
					return false ;
				}
			}
		}
		return true ;
		
	}
	
}
