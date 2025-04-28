package home.match_betting_server.auth.domain;

import home.match_betting_server.users.dto.exceptions.NewPasswordMustBeDifferentException;
import home.match_betting_server.users.dto.exceptions.PasswordAndPasswordAgainMustBeTheSameException;
import home.match_betting_server.users.dto.requests.NewPasswordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PasswordService {
    private final Logger logger = LoggerFactory.getLogger(PasswordService.class);

    private final BCryptPasswordEncoder passwordEncoder;

    public PasswordService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }

    public boolean validateNewPassword(NewPasswordRequest newPasswordRequest, String currentPassword) {
        logger.info(newPasswordRequest.password);
        logger.info(newPasswordRequest.passwordAgain);
        logger.info(hashPassword(newPasswordRequest.password));
        logger.info(currentPassword);

        if (checkPassword(newPasswordRequest.password, currentPassword)) throw new NewPasswordMustBeDifferentException();
        if (!Objects.equals(newPasswordRequest.password, newPasswordRequest.passwordAgain)) throw new PasswordAndPasswordAgainMustBeTheSameException();

        return true;
    }
}