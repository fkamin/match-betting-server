package home.match_betting_server.phase_user_stats.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class UserDoesNotJoinedThatPhaseException extends ApiException {
    public UserDoesNotJoinedThatPhaseException() {
        super(ServiceErrorResponseCode.PHASE05, HttpStatus.CONFLICT);
    }
}
