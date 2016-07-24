package ipicture.service.post.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import ipicture.service.post.model.JsonObject;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public JsonObject jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
    	JsonObject json = new JsonObject();
    	json.setMessage(e.getMessage());
        return json;
    }

}
