package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class GroupNameCanNotBeNullOrEmptyException extends ApiException {
    public GroupNameCanNotBeNullOrEmptyException() {
        super(ServiceErrorResponseCode.GROUP03, HttpStatus.BAD_REQUEST);
    }
}
