package ProjetDebat.graphe;


public class Argument implements Comparable<Argument> {
	
	private static int numArg=0 ;
	
	private String arg;
	private int num=0;
	//private List<Argument> argContradiction ;
	
	public Argument(String arg) {
		
		//argContradiction =  new ArrayList<Argument>();
		this.arg=arg;
		num = numArg;
		numArg++;
	}

	public String toString() {
		return "Argument " + arg ;
	}

	@Override
	public int compareTo(Argument A) {
		
		return this.num - A.num;
	}

	public int getNum() {
		return num;
	}
	
	
	
	

	
}
