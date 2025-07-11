package home.match_betting_server.phase_user_stats.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class NotAllowedOperationException extends ApiException {
    public NotAllowedOperationException() {
        super(ServiceErrorResponseCode.PHASE04, HttpStatus.CONFLICT);
    }
}
