package ProjetDebat.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import ProjetDebat.graphe.Argument;
import ProjetDebat.graphe.*;

public class FichierDebat {

	private String contenu ;
	private String nameFile;
	private Graphe grapheArg;
	private String caracSpeciaux = "(),";
	
	public FichierDebat(String nameFile) {
		this.nameFile=nameFile;
		grapheArg = new Graphe();
	}
	
	public void lireFichier() {
		
		StringBuffer lecture = new StringBuffer();
		String ligne;
		

		try ( BufferedReader br = new BufferedReader(new FileReader(nameFile))){
			
			while((ligne = br.readLine()) != null ) {
				ligne = ligne.replace(" ", "");  //enlever les espaces de la ligne
				lecture.append(ligne + "\n");
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.print("Fichier non trouvé");
		}
		catch (IOException e) {
			System.out.print("Erreur IOException");
		}
		contenu = lecture.toString();
	}
	
	public void creerGrapheFichier() {
		
		Scanner scanner = new Scanner(contenu);
		boolean lireArgument = true;
		boolean lireContradiction = false;
		Argument arg ;
		Argument C1;
		Argument C2;
		StringBuffer nomArg1 = new StringBuffer();
		StringBuffer nomArg2 = new StringBuffer();
		char c = 0 ;
		int cpt = 9;
		
		while (scanner.hasNextLine()) {
			
			String line = scanner.nextLine();
			
			if (line.length()==0) { // Dans le cas où une ligne a été sautée dans le fichier
				continue;
			}
			if(lireArgument) {
				cpt = 9;
				nomArg1.delete(0, nomArg1.length());
				
				
					if (!line.substring(0, 9).equals("argument(")) {
						
						try {
							if (!(line.substring(0,14).equals("contradiction("))) {
								throw new ExceptionFileDebat("Erreur Fichier dans la lecture d'un argument, ligne non reconnue."
										+ "\nFin du programme.");
							}
						}catch (ExceptionFileDebat e) {
							System.out.println(e.getMessage());
						}catch (IndexOutOfBoundsException e) {}
						
						
						lireArgument = false;
						lireContradiction=true;
					}
				
				else {
					c = line.charAt(cpt);
					while (c != ')') {
						nomArg1.append(c);
						cpt++;
						c = line.charAt(cpt);		
					}
					
					try {
						if (nomArg1.length()==0) {
							throw new ExceptionFileDebat("Erreur Fichier création argument,"
									+ " le nom d'un argument est vide"+"\nFin du programme");
						}
						if (nomArg1.toString().equals("argument")) {
							throw new ExceptionFileDebat("Erreur Fichier création argument,"
									+ "un argument ne peut pas être appelé \"argument\""+"\nFin du programme");
						}
						if (nomArg1.toString().equals("contradiction")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création contradiction,"
									+ "un argument ne peut pas être appelé \"contradiction\""+"\nFin du programme");
						}
						if ( nomArg1.toString().contains(",")||nomArg1.toString().contains("(")||nomArg1.toString().contains(")")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création argument,"
									+ "un argument ne peut pas contenir un caractère spécial : \" (), \""+"\nFin du programme");
						}
						
					} catch (ExceptionFileDebat e) {
						System.out.println(e.getMessage());
						System.exit(0);
					}
					System.out.println("Création de l'argument "+nomArg1);
					arg = new Argument(nomArg1.toString());
					ajouterArg(arg);
				}
				
			}
			
			if (lireContradiction) {

				cpt = 14;
				nomArg1.delete(0, nomArg1.length());
				nomArg2.delete(0, nomArg2.length());
				try {
					if (line.substring(0, 9).equals("argument(")) {
						throw new ExceptionFileDebat("Erreur Fichier dans la création des contradictions, pas d'argument après qu'une "
								+ "contradiction ait été entré, "
								+ "veuillez corriger votre fichier.\n\nFin du programme");

					}
					
					if (!(line.substring(0,14).equals("contradiction("))) {
						throw new ExceptionFileDebat("Erreur Fichier dans la création des contradictions, ligne non correcte, "
								+ "veuillez corriger votre fichier.\n\nFin du programme");
					}
					
					c = line.charAt(cpt);
					while (c != ',') {
						nomArg1.append(c);
						cpt++;
						c = line.charAt(cpt);		
					}

					cpt = 14 + nomArg1.length()+1 ;
					c = line.charAt(cpt);
					while (c != ')') {
						nomArg2.append(c);
						cpt++;
						c = line.charAt(cpt);		
					}
					
					
					try {

						if (nomArg1.toString().equals("argument") || nomArg2.toString().equals("argument")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création argument,"
									+ "un argument ne peut pas être appelé \"argument\""+"\nFin du programme");
						}
						if (nomArg1.toString().equals("contradiction") || nomArg2.toString().equals("contradiction")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création contradiction,"
									+ "un argument ne peut pas être appelé \"contradiction\""+"\nFin du programme");
						}
						
						if ( nomArg1.toString().contains(",")||nomArg1.toString().contains("(")||nomArg1.toString().contains(")")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création argument,"
									+ "un argument ne peut pas contenir un caractère spécial : \" (), \""+"\nFin du programme");
						}
						if ( nomArg2.toString().contains(",")||nomArg2.toString().contains("(")||nomArg2.toString().contains(")")) {
							throw new ExceptionFileDebat("\"Erreur Fichier création argument,"
									+ "un argument ne peut pas contenir un caractère spécial : \" (), \""+"\nFin du programme");
						}
						
					} catch (ExceptionFileDebat e) {
						System.out.println(e.getMessage());
						System.exit(0);
					}
					
					System.out.println("Création de la contradiction : "+ nomArg1 + " contredit "+nomArg2);
					
					C1 = grapheArg.trouverArgNom(nomArg1.toString());
					C2 = grapheArg.trouverArgNom(nomArg2.toString());
					try {
						if (C1 == null) {
							throw new ExceptionFileDebat("Erreur Fichier création contradiction, l'argument : "
									+nomArg1+" n'a pas été créé, corrigez votre fichier."
									+ "\nFin du programme");							
						}

						if (C2 == null) {
							throw new ExceptionFileDebat("Erreur Fichier création contradiction, l'argument : "
									+nomArg2+" n'a pas été créé,, corrigez votre fichier."
									+ "\nFin du programme");
							
						}
					} catch (ExceptionFileDebat e) {
						System.out.println(e.getMessage());
						System.exit(0);
					}

					
					ajouterContradiction(C1,C2);
				
				} catch (ExceptionFileDebat e) {
					System.out.println(e.getMessage());
					System.exit(0);
				}
				
			}
			
		  
		}
		scanner.close();
		
	}
	
	
	public void ajouterArg(Argument A) {
		grapheArg.ajouterNoeud(A);
		//System.out.println(grapheArg.toString());
		
	}
	
	public void ajouterContradiction(Argument A1, Argument A2) {
		try {
			if (!(grapheArg.getGraphe().keySet().contains(A1)) || !(grapheArg.getGraphe().keySet().contains(A2)) ) {
				throw new ExceptionFileDebat("Erreur Fichier dans création de contradictions, une contradiction a été entrée"
						+ "avec un argument qui n'a pas été créé.\nFin du programme");
			}
		} catch (ExceptionFileDebat e) {
			System.out.println(e.getMessage());
		}

		grapheArg.ajouterArc(A1, A2);
	}
	
	
	
	public String getContenu() {
		return contenu;
	}


	public Graphe getGrapheArg() {
		return grapheArg;
	}
	
	
	
}