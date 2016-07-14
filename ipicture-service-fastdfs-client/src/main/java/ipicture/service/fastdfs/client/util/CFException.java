package ipicture.service.fastdfs.client.util;

public class CFException extends RuntimeException{
	
	private static final long serialVersionUID = 3296727086288162806L;

	public CFException(){}
	
	public CFException(String message) {
		super(message);
	}
	
	public CFException(String message, Throwable rootCause) {
	    super(message, rootCause);
	}
	
	public CFException(Throwable rootCause) {
		super(rootCause);
	}

}
