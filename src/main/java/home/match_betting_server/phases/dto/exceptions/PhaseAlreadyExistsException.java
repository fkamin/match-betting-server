package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class PhaseAlreadyExistsException extends ApiException {
    public PhaseAlreadyExistsException() {
        super(ServiceErrorResponseCode.PHASE01, HttpStatus.CONFLICT);
    }
}
