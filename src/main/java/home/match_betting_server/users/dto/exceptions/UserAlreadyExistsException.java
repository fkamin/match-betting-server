package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ServiceErrorResponseCode;
import home.match_betting_server.commons.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ApiException {
    public UserAlreadyExistsException() {
        super(ServiceErrorResponseCode.USER01, HttpStatus.CONFLICT);
    }
}
