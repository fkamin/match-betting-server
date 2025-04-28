package home.match_betting_server.management.domain;

import home.match_betting_server.management.dto.requests.CreateUserRequest;
import home.match_betting_server.management.dto.responses.UserGeneralResponse;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.exceptions.UserAlreadyExistsException;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;

import java.util.List;

public class AdminFacade {
    private final UserRepository userRepository;

    public AdminFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserSimplifiedResponse generateNewAccount(CreateUserRequest createUserRequest) {
        // ONLY FOR DEVELOPMENT
        if (userRepository.existsByLogin(createUserRequest.login)) throw new UserAlreadyExistsException();
        User user = new User(createUserRequest.login, "test123");
        userRepository.save(user);

        return user.toSimplifiedResponse();
    }

    public List<UserGeneralResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::toGeneralResponse).toList();
    }
}
