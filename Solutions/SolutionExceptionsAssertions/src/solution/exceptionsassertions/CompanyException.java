package solution.exceptionsassertions;

public class CompanyException extends Exception {

	private static final long serialVersionUID = 1L;

	private String id;
	
	public CompanyException(String message) {
		super(message);
	}

	public CompanyException(String message, String id) {
		super(message);
		this.id = id;
	}

	public CompanyException(String message, Throwable cause, String id) {
		super(message, cause);
		this.id = id;
	}

	public String getId() {
		return id;
	}
}