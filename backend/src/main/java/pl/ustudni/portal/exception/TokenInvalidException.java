package pl.ustudni.portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class TokenInvalidException extends RuntimeException {
    public TokenInvalidException() {
    }

    public TokenInvalidException(String message) {
        super(message);
    }
}
