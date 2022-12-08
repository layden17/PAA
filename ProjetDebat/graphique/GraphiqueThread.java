package ProjetDebat.graphique;

import ProjetDebat.debat.Debat;

public class GraphiqueThread extends Thread {

	private String[] args;
	private Debat debat;
	
	public GraphiqueThread(String[] args,Debat debat) {
		this.args=args;
		this.debat=debat;
	}
	
	public void run() {
		DebatGraphique.main(args,debat);
	}
	
}
