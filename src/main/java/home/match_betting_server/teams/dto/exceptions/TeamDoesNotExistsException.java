package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class TeamDoesNotExistsException extends ApiException {
    public TeamDoesNotExistsException() {
        super(ServiceErrorResponseCode.TEAM02, HttpStatus.NOT_FOUND);
    }
}
