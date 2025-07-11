package home.match_betting_server.phase_user_stats.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class UserAlreadyJoinThatPhaseException extends ApiException {
    public UserAlreadyJoinThatPhaseException() {
        super(ServiceErrorResponseCode.PHASE03, HttpStatus.CONFLICT);
    }
}
