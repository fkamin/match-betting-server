package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class PhaseWithThatNameAlreadyExistsException extends ApiException {
    public PhaseWithThatNameAlreadyExistsException() {
        super(ServiceErrorResponseCode.PHASE01, HttpStatus.CONFLICT);
    }
}
