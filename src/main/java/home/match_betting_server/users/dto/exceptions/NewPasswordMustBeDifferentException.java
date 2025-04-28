package home.match_betting_server.users.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class NewPasswordMustBeDifferentException extends ApiException {
    public NewPasswordMustBeDifferentException() {
        super(ServiceErrorResponseCode.USER03, HttpStatus.BAD_REQUEST);
    }
}
