package home.match_betting_server.auth.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class InvalidLoginOrPasswordException extends ApiException {
    public InvalidLoginOrPasswordException() {
        super(ServiceErrorResponseCode.AUTH01, HttpStatus.UNAUTHORIZED);
    }
}
