package home.match_betting_server.matches.dto.exceptions;

import home.match_betting_server.commons.ApiException;
import home.match_betting_server.commons.ServiceErrorResponseCode;
import org.springframework.http.HttpStatus;

public class MatchIsFinishedException extends ApiException {
    public MatchIsFinishedException() {
        super(ServiceErrorResponseCode.MATCH02, HttpStatus.BAD_REQUEST);
    }
}
