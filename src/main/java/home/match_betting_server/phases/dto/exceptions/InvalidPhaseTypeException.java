package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class InvalidPhaseTypeException extends ApiException {
    public InvalidPhaseTypeException() {
        super(ServiceErrorResponseCode.PHASE08, HttpStatus.BAD_REQUEST);
    }
}
