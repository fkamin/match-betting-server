package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class InvalidCredentialsException extends ApiException {
    public InvalidCredentialsException() {
        super(HttpStatus.UNAUTHORIZED.value(), "Invalid login or password");
    }
}
