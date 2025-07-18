package home.match_betting_server.users;

import home.match_betting_server.users.domain.UserFacade;
import home.match_betting_server.users.dto.requests.NewPasswordRequest;
import home.match_betting_server.users.dto.requests.NewUserNameRequest;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserFacade userFacade;

    @GetMapping
    public List<UserDetailedResponse> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDetailedResponse getUserDetails(@PathVariable Long userId) {
        return userFacade.getUserDetails(userId);
    }

    @PutMapping("/{userId}/change-name")
    public UserDetailedResponse changeName(@PathVariable Long userId, @RequestBody NewUserNameRequest newUserNameRequest) {
        return userFacade.changeName(userId, newUserNameRequest);
    }

    @PutMapping("/{userId}/change-password")
    public UserDetailedResponse changePassword(@PathVariable Long userId, @RequestBody NewPasswordRequest newPasswordRequest) {
        return userFacade.changePassword(userId, newPasswordRequest);
    }
}
