package home.match_betting_server.matches.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class TeamsMustBeDifferentException extends ApiException {
    public TeamsMustBeDifferentException() {
        super(ServiceErrorResponseCode.MATCH03, HttpStatus.BAD_REQUEST);
    }
}
