package ProjetDebat.debat;
import ProjetDebat.graphe.*;

import java.util.ArrayList;
import java.util.Collections;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import ProjetDebat.debat.Menu;


import up.mi.jgm.td1corrige.ExceptionMenu;



public class Debat {

	/**
	 * Classe qui permet la construction d'un débat
	 */
	
	private List<Argument> listArguments ;
	private Graphe grapheArg ;
	private Set<Argument> solutionPotentielle ;
	private int nbArg=0;
	
	// Dans le cas ou on veut rentrer d'autres noms pour les args
	/**
	 * Construit un débat en fonction d'une liste passée en paramètre
	 * Version 1:	permet de choisir vos propres noms pour les arguments
	 * 
	 * @param listArgument est une liste regroupant l'ensemble des arguments utlisés dans ce débat
	 */
	public Debat(List<Argument> listArguments) {
		
		this.listArguments = listArguments;
		grapheArg = new Graphe(listArguments);
		solutionPotentielle = new HashSet<Argument>();
	}
	
	
	public Debat(Graphe grapheArg) {
		
		this.listArguments = new ArrayList<Argument>(grapheArg.getGraphe().keySet());
		Collections.sort(listArguments);
		this.grapheArg = grapheArg;
		nbArg = listArguments.size();
		solutionPotentielle = new HashSet<Argument>();
	}
	
	// Dans le cas ou on initialise A1,A2,AN automatiquement
	/**
	 * Construit un débat en fonction d'un nombre en paramètre
	 * Version 2:	le nom des arguments est choisi automatiquement A1,A2...,AN
	 * 
	 * @param nbArg est un nombre correspondant à l'ensemble des arguments utlisés dans ce débat
	 */
	public Debat(int nbArg) {
		
		this.nbArg=nbArg;
		listArguments = new ArrayList<Argument>();
		grapheArg = new Graphe();
		solutionPotentielle = new HashSet<Argument>();
		for (int i = 0; i < nbArg; i++) {
			listArguments.add(new Argument("A"+(i+1)));
			grapheArg.ajouterNoeud(listArguments.get(i));
		}
	 
		
	}
	
	/** Demande à utlisateur d'entrer nombre d'arguments
	 * 
	 * @return nbArg est un nombre correspondant à l'ensemble des arguments utlisés dans ce débat
	 */
	public static int demanderNombreArgument() {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Entrez le nombre d'arguments : \n--> : ");
		int nbArg = 0;
		boolean condition = true;
		do {
			try {
				nbArg = sc.nextInt();
				if (nbArg<=0) {
					throw new ExceptionDebat("\nEntrez un nombre d'arguments supérieur ou égale à 0.\n--> : ");
				}
				condition = false;
				
			}catch (ExceptionDebat e) {
				sc.nextLine();
				System.out.println(e.getMessage());
			}
			catch (InputMismatchException e) {
				System.out.print("Erreur, entrez un entier\n--> :");
				sc.nextLine();
			}
		} while (condition);
		
		return nbArg;
	}
	
	/**
	 * Affichage du 1er menu permettant de construire le débat
	 */
	
	public void affichageMenuContradiction() {
		
		Scanner sc = new Scanner(System.in);
		
		int choix =0 ;

			do {
				try {
					
			
					Menu.menuContradiction();
					System.out.print("\n--> : ");
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
					System.out.println(e.getMessage());
				}
				  catch (InputMismatchException e) {
					System.out.println("Erreur, veuillez entrer un entier\n");
					sc.nextLine();
				}
				
				
			
			} while (choix != 2);
				
		
	}

	/**
	 * Permet à l'utilisateur de choisir d'insérer une contraction dans le débat 
	 */
	private void ajouterContradiction(Scanner sc) {

		int choix = -1;
		boolean sortie = false;
		Argument A1;
		Argument A2;
		
		do {
			try {
				
				System.out.println("\nEntrez l'argument qui va contredire :\n");
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

	/**
	 *	Permet l'affichage du graphe associé au débat
	 */
	public void afficheGraphe() {
		System.out.println(grapheArg.toString());
	}
	
	/**
	 *	Dresse la liste des arguments
	 *@param listArguments liste des arguments du débat 
	 */
	public void afficheArguments(List <Argument> listArguments) {
		
		int cpt = 1;
		for (Argument arg : listArguments) {
			System.out.println("("+cpt +") "+ arg);
			cpt++;
		}
	}
	
	/**
	 *	Menu qui va permettre de construire la solution admissible du débat et de vérifier si celle-ci est correcte
	 */
	public void affichageMenuSolution() {
		Scanner sc = new Scanner(System.in);
		VerifSolution verif = new VerifSolution(solutionPotentielle, grapheArg);
		int choix =0 ;

			do {
				try {
					
			
					Menu.menuSolution(solutionPotentielle);
					System.out.print("\n--> : ");
					choix = sc.nextInt();
				
					if (choix<1 || choix>4) {
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
							if (verif.verifSolution()) {
								System.out.println("\nLa solution est admissible.");
							}
							break;
						
						case 4:
							System.out.println("\nLa solution potentielle est : "+solutionPotentielle);
							if (verif.verifSolution()) {
								System.out.println("\nLa solution est admissible.");
							}
							System.out.println("\nFin du programme");
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


	public void affichageMenuRechercheSolution() {
		Scanner sc = new Scanner(System.in);
		RechercheSolution rs = new RechercheSolution(grapheArg);
		rs.construireListeCombinaisons();
		rs.construireListeSolutionAdmissible();
		
		int choix =0 ;

			do {
				try {
					
			
					Menu.menuRechercheSolution();
					System.out.print("\n--> : ");
					choix = sc.nextInt();
				
					if (choix<1 || choix>4) {
						throw new ExceptionMenu("Veuillez entrer un choix correct\n");
					}

				
					switch (choix) {
						case 1:
							System.out.println("\nUne solution admissible est :\n");
							System.out.println(rs.getAdmissible());
							System.out.println("\nLa liste des solutions admissible est :\n");
							rs.afficheListeSolutionAdmissible();
							break;
						case 2:
							System.out.println("e");
							break;
						
						case 3:
							System.out.println("\n\n Vérification de la solution potentielle :\n\n\t" +solutionPotentielle+"\n");
							
							break;
						
						case 4:
							System.exit(0);
							
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


	/**
	 * permet d'ajouter un argument dans la collection solutionPotentielle si elle n'y figure pas 
	 * 
	 * @param sc un objet de type scanner pour recevoir les input de l'utilisateur
	 */
	
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
	
	public List<Argument> getListArguments() {
		return listArguments;
	}

	/**
	 * permet à l'utlisateur de choisir de retirer un argument de la solution potentielle
	 * 
	 * @param sc un objet de type scanner pour recevoir les input de l'utilisateur
	 */
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
	
	
	
}
