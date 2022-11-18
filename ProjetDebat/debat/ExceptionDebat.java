package ProjetDebat.debat;

public class ExceptionDebat extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionDebat(String msg) {
		this.msg = msg;
	}
	

	public String getMessage() {
		return msg;
	}
}
