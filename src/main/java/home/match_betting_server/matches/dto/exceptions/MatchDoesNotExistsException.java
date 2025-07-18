package home.match_betting_server.matches.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class MatchDoesNotExistsException extends ApiException {
    public MatchDoesNotExistsException() {
        super(ServiceErrorResponseCode.MATCH01, HttpStatus.NOT_FOUND);
    }
}
