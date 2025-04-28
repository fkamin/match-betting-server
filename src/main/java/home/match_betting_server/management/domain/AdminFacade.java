package home.match_betting_server.management.domain;

import home.match_betting_server.auth.domain.PasswordGenerator;
import home.match_betting_server.auth.domain.PasswordService;
import home.match_betting_server.management.dto.requests.CreateUserRequest;
import home.match_betting_server.management.dto.responses.NewAccountResponse;
import home.match_betting_server.management.dto.responses.UserGeneralResponse;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserAlreadyExistsException;
import home.match_betting_server.users.dto.exceptions.UserNotFoundException;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class AdminFacade {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public AdminFacade(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public NewAccountResponse generateNewAccount(CreateUserRequest createUserRequest) {
        if (userRepository.existsByLogin(createUserRequest.login)) throw new UserAlreadyExistsException();

        String generatedPassword = PasswordGenerator.generateRandomPassword();
        User originalUser = new User(createUserRequest.login, generatedPassword);
        User userForDatabase = new User(createUserRequest.login, passwordService.hashPassword(generatedPassword));

        userRepository.save(userForDatabase);

        return originalUser.toNewAccountResponse();
    }

    public List<UserGeneralResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::toGeneralResponse).toList();
    }

    public ResponseEntity<String> deleteUserById(Long userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userToDelete);

        return ResponseEntity.ok().build();
    }
}
