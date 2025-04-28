package home.match_betting_server.commons;

public enum ServiceErrorResponseCode {
    USER01("User already exists exception");

    private final String message;

    ServiceErrorResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
