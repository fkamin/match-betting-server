package home.match_betting_server.admin.domain;

import home.match_betting_server.auth.domain.PasswordGenerator;
import home.match_betting_server.auth.domain.PasswordService;
import home.match_betting_server.admin.dto.requests.CreateUserRequest;
import home.match_betting_server.users.domain.Role;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserAlreadyExistsException;
import home.match_betting_server.users.dto.exceptions.UserDoesNotExistsException;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import org.springframework.http.ResponseEntity;

public class AdminFacade {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public AdminFacade(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public UserNewAccountResponse generateNewAccount(CreateUserRequest createUserRequest) {
        if (userRepository.existsByLogin(createUserRequest.getLogin())) throw new UserAlreadyExistsException();

        String generatedPassword = PasswordGenerator.generateRandomPassword();

        User originalUser = new User(createUserRequest.getLogin(), generatedPassword);
        User userForDatabase = new User(createUserRequest.getLogin(), passwordService.hashPassword(generatedPassword), Role.USER);

        userRepository.save(userForDatabase);

        return originalUser.toNewAccountResponse(userForDatabase.getId());
    }

    public ResponseEntity<String> deleteUserById(Long userId) {
        User userToDelete = findUserById(userId);
        userRepository.delete(userToDelete);

        return ResponseEntity.ok().build();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
    }
}
