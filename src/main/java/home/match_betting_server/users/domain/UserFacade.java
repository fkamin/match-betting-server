package home.match_betting_server.users.domain;

import home.match_betting_server.auth.domain.PasswordService;
import home.match_betting_server.users.dto.exceptions.UserAlreadyExistsException;
import home.match_betting_server.users.dto.exceptions.UserDoesNotExistsException;
import home.match_betting_server.users.dto.exceptions.UserNameCanNotBeNullOrEmptyException;
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

    public UserDetailedResponse changeName(Long userId, NewUserNameRequest newUserNameRequest) {
        User userToModify = findUserById(userId);

        validateUserNameUpdatingConditions(newUserNameRequest.getName());
        userToModify.setName(newUserNameRequest.getName());

        return userRepository.save(userToModify).toDetailedResponse();
    }

    public UserDetailedResponse changePassword(Long userId, NewPasswordRequest newPasswordRequest) {
        User userToModify = findUserById(userId);

        if (passwordService.validateNewPassword(newPasswordRequest, userToModify.getPassword())) {
            userToModify.setPassword(passwordService.hashPassword(newPasswordRequest.password));
        }

        return userRepository.save(userToModify).toDetailedResponse();
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserDoesNotExistsException::new);
    }

    private void validateUserNameUpdatingConditions(String name) {
        validateUserExistence(name);
        validateUserName(name);
    }

    private void validateUserExistence(String name) {
        if (userRepository.existsByName(name)) throw new UserAlreadyExistsException();
    }

    private void validateUserName(String name) {
        if (name.isEmpty() || name == null) throw new UserNameCanNotBeNullOrEmptyException();
    }


}
