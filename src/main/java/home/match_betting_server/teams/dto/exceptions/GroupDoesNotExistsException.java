package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class GroupDoesNotExistsException extends ApiException {
    public GroupDoesNotExistsException() {
        super(ServiceErrorResponseCode.GROUP02, HttpStatus.NOT_FOUND);
    }
}
