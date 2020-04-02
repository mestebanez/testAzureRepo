package net.dovale.okta.secure_rest_api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class HttpExceptionResponse extends Exception {

}
