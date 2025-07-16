package home.match_betting_server.matches.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class TeamsMustBeFromTheSameGroupException extends ApiException {
    public TeamsMustBeFromTheSameGroupException() {
        super(ServiceErrorResponseCode.MATCH04, HttpStatus.BAD_REQUEST);
    }
}
