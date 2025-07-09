package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class TeamWithThatNameAlreadyExistsException extends ApiException {
    public TeamWithThatNameAlreadyExistsException() {
        super(ServiceErrorResponseCode.TEAM01, HttpStatus.CONFLICT);
    }
}
