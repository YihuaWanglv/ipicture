package ipicture.service.post.exception;

public class BaseException extends Exception {

	private static final long serialVersionUID = -4914973966812851077L;
	
	private int code = 1;
	
	public BaseException() {
		super();
	}
	
	public BaseException(String msg) {
		super(msg);
	}
	
	public BaseException(int code, String msg) {
		
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
