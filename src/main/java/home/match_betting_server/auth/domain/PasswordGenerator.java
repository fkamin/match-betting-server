package home.match_betting_server.auth.domain;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_!";
    private static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }
}