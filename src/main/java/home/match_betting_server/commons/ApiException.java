package home.match_betting_server.commons;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class ApiException extends ResponseStatusException {
    private final HttpStatusCode httpStatusCode;
    private final ServiceErrorResponseCode errorCode;

    public ApiException(ServiceErrorResponseCode errorCode, HttpStatusCode httpStatusCode) {
        super(httpStatusCode, errorCode.getMessage());
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }

    public HttpStatusCode getHttpStatusCode() {
        return httpStatusCode;
    }

    public ServiceErrorResponseCode getErrorCode() {
        return errorCode;
    }

}


