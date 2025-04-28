package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException() {
        super(ServiceErrorResponseCode.USER02, HttpStatus.NOT_FOUND);
    }
}
