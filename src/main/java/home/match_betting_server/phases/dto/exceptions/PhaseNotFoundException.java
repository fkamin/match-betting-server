package home.match_betting_server.phases.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class PhaseNotFoundException extends ApiException {
    public PhaseNotFoundException() {
        super(ServiceErrorResponseCode.PHASE02, HttpStatus.NOT_FOUND);
    }
}
