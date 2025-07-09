package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class TeamNotFoundException extends ApiException {
    public TeamNotFoundException() {
        super(ServiceErrorResponseCode.TEAM02, HttpStatus.NOT_FOUND);
    }
}
