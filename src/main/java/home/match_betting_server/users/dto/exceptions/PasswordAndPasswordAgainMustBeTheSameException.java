package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class PasswordAndPasswordAgainMustBeTheSameException extends ApiException {
    public PasswordAndPasswordAgainMustBeTheSameException() {
        super(ServiceErrorResponseCode.USER04, HttpStatus.BAD_REQUEST);
    }
}
