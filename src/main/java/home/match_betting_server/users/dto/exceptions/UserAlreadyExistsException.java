package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT.value(), "Username already exists");
    }
}
