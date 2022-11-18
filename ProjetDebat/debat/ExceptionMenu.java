package ProjetDebat.debat;

public class ExceptionMenu extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionMenu(String msg) {
		this.msg = msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
