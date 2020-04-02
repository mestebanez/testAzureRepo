package net.dovale.okta.secure_rest_api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value
            = { RuntimeException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        ex.printStackTrace();
        return new ResponseEntity(bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT);
    }
    

}
