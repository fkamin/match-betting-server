package home.match_betting_server.commons;

public enum ServiceErrorResponseCode {
    USER01("User with that login already exists exception"),
    USER02("User does not exists exception"),
    USER03("New password must be different then current"),
    USER04("New password and new password again must be the same");

    private final String message;

    ServiceErrorResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
