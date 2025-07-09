package home.match_betting_server.teams.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class GroupNotFoundException extends ApiException {
    public GroupNotFoundException() {
        super(ServiceErrorResponseCode.GROUP02, HttpStatus.NOT_FOUND);
    }
}
