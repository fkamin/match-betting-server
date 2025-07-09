package home.match_betting_server.users.domain;

import home.match_betting_server.auth.domain.PasswordService;
import home.match_betting_server.users.dto.exceptions.UserNotFoundException;
import home.match_betting_server.users.dto.exceptions.UserWithThatNameAlreadyExistsException;
import home.match_betting_server.users.dto.requests.NewPasswordRequest;
import home.match_betting_server.users.dto.requests.NewUserNameRequest;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;

import java.util.List;

public class UserFacade {
    private final UserRepository userRepository;
    private final PasswordService passwordService;

    public UserFacade(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public List<UserDetailedResponse> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDetailedResponse).toList();
    }

    public UserDetailedResponse getUserDetails(Long userId) {
        return findUserById(userId).toDetailedResponse();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public UserDetailedResponse changeName(Long userId, NewUserNameRequest newUserNameRequest) {
        User userToModify = findUserById(userId);

        //TODO dodać walidacje
        if (userRepository.existsByName(newUserNameRequest.name) && newUserNameRequest.name != null)
            throw new UserWithThatNameAlreadyExistsException();
        userToModify.setName(newUserNameRequest.name);

        return userRepository.save(userToModify).toDetailedResponse();
    }

    public UserDetailedResponse changePassword(Long userId, NewPasswordRequest newPasswordRequest) {
        User userToModify = findUserById(userId);

        if (passwordService.validateNewPassword(newPasswordRequest, userToModify.getPassword())) {
            userToModify.setPassword(passwordService.hashPassword(newPasswordRequest.password));
        }

        return userRepository.save(userToModify).toDetailedResponse();
    }
}
