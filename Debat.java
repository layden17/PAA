package ProjetDebat;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


import up.mi.jgm.td1corrige.ExceptionMenu;
import ProjetDebat.graphe.*;


public class Debat {

	
	private List<Argument> listArguments ;
	private Graphe grapheArg ;
	private Set<Argument> solutionPotentielle = new HashSet<Argument>();
	private int nbArg=0;
	
	// Dans le cas ou on veut rentrer d'autres noms pour les args
	public Debat(List<Argument> listArguments) {
		
		this.listArguments = listArguments;
		grapheArg = new Graphe(listArguments);
	}
	
	// Dans le cas ou on initialise A1,A2,AN automatiquement
	public Debat(int nbArg) {
		
		this.nbArg=nbArg;
		listArguments = new ArrayList<Argument>();
		grapheArg = new Graphe();
		for (int i = 0; i < nbArg; i++) {
			listArguments.add(new Argument("A"+(i+1)));
			grapheArg.ajouterNoeud(listArguments.get(i));
		}
	 
		
	}
	
	public static int demanderNombreArgument() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez le nombre d'arguments : \n--> : ");
		int nbArg = 0;
		boolean condition = true;
		do {
			try {
				nbArg = sc.nextInt();
				condition = false;
				
			} catch (InputMismatchException e) {
				System.out.print("Erreur, entrez un entier\n--> :");
				sc.nextLine();
			}
		} while (condition);
		
		return nbArg;
	}
	
	public void affichageMenuContradiction() {
		
		Scanner sc = new Scanner(System.in);
		
		int choix =0 ;

			do {
				try {
					
			
					menuContradiction();
					System.out.println("\n--> : ");
					choix = sc.nextInt();
					sc.nextLine();

					
				
					if (choix  != 1 && choix!=2) {
						throw new ExceptionMenu("Veuillez entrer un choix correct\n");
					}

				
					switch (choix) {
						case 1:
							ajouterContradiction(sc);
							break;
						case 2:
							break;
						}
					
				} catch (ExceptionMenu e) {
					sc.nextLine();
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier\n");
					sc.nextLine();
				}
				
				
			
			} while (choix != 2);
				//sc.close();
		
	}

	private static void menuContradiction() {
		System.out.println("Quelle operation souhaitez-vous effectuer ?\n");
		System.out.println("1 Ajouter une contradiction");
		System.out.println("2 Quitter");
	}

	private void ajouterContradiction(Scanner sc) {

		int choix = -1;
		boolean sortie = false;
		Argument A1;
		Argument A2;
		
		do {
			try {
				
				System.out.print("\nEntrez l'argument qui va contredire :\n");
				afficheArguments(this.listArguments);
				System.out.print("\n\n--> : ");
				choix = sc.nextInt();

				A1 = listArguments.get(choix-1);
				
				System.out.print("\nEntrez l'argument qui est contredit :\n");
				afficheArguments(this.listArguments);
				System.out.print("\n\n--> : ");
				choix = sc.nextInt();
				A2 = listArguments.get(choix-1);
				if (grapheArg.getGraphe().get(A1)!=null) {
					if (grapheArg.getGraphe().get(A1).contains(A2) ) {
						sortie = true;
						throw new ExceptionDebat("\nErreur, cette contradiction a déjà été entrée");
					}
				}
				
				if(A2==A1) {
					throw new ExceptionDebat("\nUn argument ne peut pas se contredire lui même,recommencez.\n");
				}
				else {
					grapheArg.ajouterArc(A1, A2);
					sortie = true;
				}
				
			} catch (ExceptionDebat e) {
				sc.nextLine();
				System.out.println(e.getMessage());
			}
			catch (IllegalArgumentException e) {
				sc.nextLine();
				System.out.println("\nVeuillez entrer un entier.\n");
			}
		} while (!sortie);
		afficheGraphe();
	}
	
	public void afficheGraphe() {
		System.out.println(grapheArg.toString());
	}
	public void afficheArguments(List <Argument> listArguments) {
		
		int cpt = 1;
		for (Argument arg : listArguments) {
			System.out.println("("+cpt +") "+ arg);
			cpt++;
		}
	}
	
	public void affichageMenuSolution() {
		Scanner sc = new Scanner(System.in);
		
		int choix =0 ;

			do {
				try {
					
			
					menuSolution();
					System.out.print("\n--> : ");
					choix = sc.nextInt();
				
					if (choix<1 && choix>4) {
						throw new ExceptionMenu("Veuillez entrer un choix correct\n");
					}

				
					switch (choix) {
						case 1:
							ajouterArgument(sc);
							break;
						case 2:
							retirerArgument(sc);
							break;
						
						case 3:
							System.out.println("\n\n Vérification de la solution potentielle :\n\n\t" +solutionPotentielle+"\n");
							if (verifSolution()) {
								System.out.println("\nLa solution est admissible.");
							}
							break;
						
						case 4:
							break;
						}
					
				} catch (ExceptionMenu e) {
					sc.nextLine();
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier\n");
					sc.nextLine();
				}
				
			
			} while (choix != 4);
				//sc.close();
	}

	private void menuSolution() {
		System.out.println("\n\nLa solution potentielle est : \t"+solutionPotentielle+"\n");
		System.out.println("Quelle operation souhaitez-vous effectuer ?");
		System.out.println("1 Ajouter un argument");
		System.out.println("2 Retirer un argument");
		System.out.println("3 Vérifier la solution");
		System.out.println("4 Fin");
		
	}
	
	public void ajouterArgument(Scanner sc) {
		if (solutionPotentielle.size()<nbArg) {

			int choix = 0;
			Argument A;
			boolean sortie = false;
			do {
				try {
					
					System.out.print("\nChoisissez l'argument à ajouter à la solution :\n");
					afficheArguments(this.listArguments);
					System.out.print("\n\n--> : ");
					choix = sc.nextInt();
					sc.nextLine();
					A = listArguments.get(choix-1);
					if (solutionPotentielle.contains(A)) {
						System.out.println("\nL'argument est déjà dans la solution proposée.");
						break;
					}
					solutionPotentielle.add(A);
					sortie = true;
					
				}
				catch (IllegalArgumentException e) {
					sc.nextLine();
					System.out.println("\nVeuillez entrer un entier.\n");
				}
			} while (!sortie);
		} else {
			System.out.println("\nImpossible d'ajouter un autre argument car l'ensemble de la solution potentielle est complet");
		}
	}
	
	public void retirerArgument(Scanner sc) {
		if (solutionPotentielle.size()>0) {
			
		
			int choix = 0;
			Argument A = null;
			boolean sortie = false;
			do {
				try {
					
					int cpt =1;
					System.out.print("\nChoisissez l'argument à retirer de la solution :\n\n");
					List<Argument> listArgSp = new ArrayList<Argument>();
					
					for (Argument argument : solutionPotentielle) {
						listArgSp.add(argument);
					}
					Collections.sort(listArgSp);
					afficheArguments(listArgSp);
					System.out.print("\n\n--> : ");
					choix = sc.nextInt();
					sc.nextLine();
					A = listArgSp.get(choix-1);
					if (choix > solutionPotentielle.size() || choix<=0 ) {
						throw new ExceptionDebat("\nEntrez un choix correct.\n");
					}
					
					solutionPotentielle.remove(A);
					sortie = true;
					
				}
				catch (ExceptionDebat e) {
					System.out.println(e.getMessage());
				}
				catch (IllegalArgumentException e) {
					sc.nextLine();
					System.out.println("\nVeuillez entrer un entier.\n");
				}
				
			} while (!sortie);
		}
		else {
			System.out.println("\nImpossible de retirer car la solution potentielle est vide");
		}

	
	}
	
	public boolean verifSolution() {
		
		Set <Argument> argContreditsParSp = new HashSet<Argument>();
		
		// 1ere condition : pas de contradiction interne
		for (Argument arg : solutionPotentielle) {
			if (grapheArg.getGraphe()!=null) {
				for (Argument argContredit : grapheArg.getGraphe().get(arg)) {
					if (solutionPotentielle.contains(argContredit)) {
						System.out.println("\nLa solution n'est pas admissible car contradiction interne :\n\n\t"+arg+" contredit :\t"+argContredit+"\n\t");
						return false;
					}
					argContreditsParSp.add(argContredit); // on construit ensemblecontredits pour la 2e condition dans le cas ou la 1ere condition est vraie
				}
			}
			
		}
		
		
		// 2e condition : chaque argument contredit de E est défendu par un élement de E
		//Donc si arg de solutionPotentielle est dans allArgContredits, l'argument qui le contredit doit être dans argContreditParSp 
		
		Set <Argument> allArgumentsContredits = new HashSet<Argument>();
		Set <Argument> allArgumentsExceptSolutionPotentielle = new HashSet<Argument>();
		
		for (Argument argument : grapheArg.getGraphe().keySet()) {
			if (!solutionPotentielle.contains(argument)) {
				allArgumentsExceptSolutionPotentielle.add(argument);
			}
		}
		
		for (Argument arg : grapheArg.getGraphe().keySet()) {		// On construit l'ensemble de tous les arguments contredits 	
			if (grapheArg.getGraphe()!=null) {
				for (Argument arg2 : grapheArg.getGraphe().get(arg)) {  // (c-a-d l'ensemble des valeurs du graphe)	
					allArgumentsContredits.add(arg2);
				}
			}
		}
		
		Set <Argument> ensembleContradicteurs = new HashSet<Argument>();
		for (Argument arg : solutionPotentielle) {
			if (allArgumentsContredits.contains(arg)) { // c-a-d si l'argument est contredit par un autre 
				for (Argument argExceptSp : allArgumentsExceptSolutionPotentielle) {
					if (grapheArg.getGraphe()!=null) {
						if (grapheArg.getGraphe().get(argExceptSp).contains(arg)) {
							if (!argContreditsParSp.contains(argExceptSp)) {	//un argument se défend en contredisant l'argument qui le contredit
								System.out.println("La solution n'est pas admissible car aucun argument de la solution"
										+ "potentielle ne défend : "+arg+"contre "+argExceptSp);
								return false;
							}
						}
					}
					
				}
														
			}
		}
		
		
		return true;
	}
}
