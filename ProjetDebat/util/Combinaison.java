package ProjetDebat.util;


import java.util.ArrayList;

import ProjetDebat.graphe.Argument;

public class Combinaison {


	public static void combinaisonUtil(ArrayList<Argument> list, ArrayList<Argument> listCombinaison,ArrayList<ArrayList<Argument>> lc , int debut,
								int fin, int index, int taille,int cpt)
	{
		if (index == taille){	
			
			lc.add(new ArrayList<Argument>());
			while (lc.get(cpt).size()!=0) {
				cpt++;
				
			}
			for (int j=0; j<taille; j++) {

				lc.get(cpt).add(listCombinaison.get(j));

			}
			return;
		}


		for (int i=debut; i<=fin && fin-i+1 >= taille-index; i++)
		{
			listCombinaison.add(index,list.get(i));
			
			if (index ==taille) {
				cpt+=1;
			}
			combinaisonUtil(list, listCombinaison,lc, i+1, fin, index+1, taille,cpt);
			
		}
	}
}


