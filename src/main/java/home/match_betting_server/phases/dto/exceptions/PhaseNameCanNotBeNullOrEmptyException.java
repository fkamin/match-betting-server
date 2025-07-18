package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class PhaseNameCanNotBeNullOrEmptyException extends ApiException {
    public PhaseNameCanNotBeNullOrEmptyException() {
        super(ServiceErrorResponseCode.PHASE09, HttpStatus.BAD_REQUEST);
    }
}
