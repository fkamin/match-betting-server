package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class UserWithThatNameAlreadyExistsException extends ApiException {
    public UserWithThatNameAlreadyExistsException() {
        super(ServiceErrorResponseCode.USER05, HttpStatus.CONFLICT);
    }
}
