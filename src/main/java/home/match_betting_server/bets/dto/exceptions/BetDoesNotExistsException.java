package home.match_betting_server.bets.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class BetDoesNotExistsException extends ApiException {
    public BetDoesNotExistsException() {
        super(ServiceErrorResponseCode.BET02, HttpStatus.CONFLICT);
    }
}
