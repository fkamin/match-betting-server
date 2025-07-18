package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class UserDoesNotExistsException extends ApiException {
    public UserDoesNotExistsException() {
        super(ServiceErrorResponseCode.USER02, HttpStatus.NOT_FOUND);
    }
}
