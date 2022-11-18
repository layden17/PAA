package ProjetDebat.file;

public class ExceptionFileDebat extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg ;
	
	public ExceptionFileDebat(String msg) {
		this.msg = msg;
	}
	

	public String getMessage() {
		return msg;
	}
}
