package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class GroupWithThatNameAlreadyExistsException extends ApiException {
    public GroupWithThatNameAlreadyExistsException() {
        super(ServiceErrorResponseCode.GROUP01, HttpStatus.CONFLICT);
    }
}
