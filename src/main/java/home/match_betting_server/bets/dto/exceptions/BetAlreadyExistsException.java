package home.match_betting_server.bets.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class BetAlreadyExistsException extends ApiException {
    public BetAlreadyExistsException() {
        super(ServiceErrorResponseCode.BET01, HttpStatus.CONFLICT);
    }
}
