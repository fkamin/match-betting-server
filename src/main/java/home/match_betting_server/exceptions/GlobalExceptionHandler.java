package home.match_betting_server.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException apiException) {
        ErrorResponse errorResponse = new ErrorResponse(apiException.getStatusCode().value(), apiException.getReason());

        return ResponseEntity
                .status(apiException.getStatusCode())
                .body(errorResponse);
    }
}
