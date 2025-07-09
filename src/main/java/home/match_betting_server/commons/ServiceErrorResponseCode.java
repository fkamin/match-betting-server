package home.match_betting_server.commons;

import lombok.Getter;

@Getter
public enum ServiceErrorResponseCode {
    USER01("User with that login already exists exception"),
    USER02("User does not exists exception"),
    USER03("New password must be different then current"),
    USER04("New password and new password again must be the same"),
    USER05("User with that name already exists exception");

    private final String message;

    ServiceErrorResponseCode(String message) {
        this.message = message;
    }

}
