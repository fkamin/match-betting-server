package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class InvalidTeamNameException extends ApiException {
    public InvalidTeamNameException() {
        super(ServiceErrorResponseCode.TEAM03, HttpStatus.BAD_REQUEST);
    }
}
