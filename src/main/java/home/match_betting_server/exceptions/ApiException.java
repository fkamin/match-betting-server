package home.match_betting_server.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {
    public ApiException(Integer code, String message) {
        super(HttpStatusCode.valueOf(code), message);
    }
}
