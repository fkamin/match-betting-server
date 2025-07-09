package home.match_betting_server.users;

import home.match_betting_server.users.domain.UserFacade;
import home.match_betting_server.users.dto.requests.NewPasswordRequest;
import home.match_betting_server.users.dto.requests.NewUserNameRequest;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/users")
public class UserCrudController {
    private final UserFacade userFacade;

    public UserCrudController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping
    public List<UserDetailedResponse> getAllUsers() {
        return userFacade.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDetailedResponse getUser(@PathVariable Long userId) {
        return userFacade.getUserDetails(userId);
    }

    @PutMapping("/{userId}/change-name")
    public UserDetailedResponse changeUserName(@PathVariable Long userId, @RequestBody NewUserNameRequest newUserNameRequest) {
        return userFacade.changeUserName(userId, newUserNameRequest);
    }

    @PutMapping("/{userId}/change-password")
    public UserDetailedResponse changeUserName(@PathVariable Long userId, @RequestBody NewPasswordRequest newPasswordRequest) {
        return userFacade.changeUserPassword(userId, newPasswordRequest);
    }
}
