package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class InvalidPhaseStatusException extends ApiException {
    public InvalidPhaseStatusException() {
        super(ServiceErrorResponseCode.PHASE06, HttpStatus.BAD_REQUEST);
    }
}
