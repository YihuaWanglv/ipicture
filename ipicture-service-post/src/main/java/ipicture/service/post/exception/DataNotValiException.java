package ipicture.service.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No such Order")
public class DataNotValiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6159283683068495736L;
	
	public DataNotValiException() {
		super();
	}
	
	public DataNotValiException(String msg) {
		super(msg);
	}

}
