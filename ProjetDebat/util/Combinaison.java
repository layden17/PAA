package ProjetDebat.util;


import java.util.ArrayList;

import ProjetDebat.graphe.Argument;

public class Combinaison {


	public static void combinaisonUtil(ArrayList<Argument> list, ArrayList<Argument> data,ArrayList<ArrayList<Argument>> lc , int start,
								int end, int index, int r,int cpt)
	{
		// Current combination is ready to be printed, print it
		if (index == r){	
			
			lc.add(new ArrayList<Argument>());
			while (lc.get(cpt).size()!=0) {
				cpt++;
				
			}
			for (int j=0; j<r; j++) {

				lc.get(cpt).add(data.get(j));

			}
			return;
		}


		for (int i=start; i<=end && end-i+1 >= r-index; i++)
		{
			data.add(index,list.get(i));
			
			if (index ==r) {
				cpt+=1;
			}
			combinaisonUtil(list, data,lc, i+1, end, index+1, r,cpt);
			
		}
	}
}


