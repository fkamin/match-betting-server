package home.match_betting_server.matches.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class MatchAlreadyExistsException extends ApiException {
    public MatchAlreadyExistsException() {
        super(ServiceErrorResponseCode.MATCH05, HttpStatus.CONFLICT);
    }
}
