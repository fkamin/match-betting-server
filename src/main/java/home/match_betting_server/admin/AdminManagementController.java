package home.match_betting_server.admin;

import home.match_betting_server.admin.domain.AdminFacade;
import home.match_betting_server.admin.dto.requests.CreateUserRequest;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/admin")
public class AdminManagementController {
    private final AdminFacade adminFacade;

    public AdminManagementController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    // USERS
    @PostMapping("/users")
    public UserNewAccountResponse generateNewAccount(@RequestBody CreateUserRequest createUserRequest) {
        return adminFacade.generateNewAccount(createUserRequest);
    }

    @GetMapping("/users")
    public List<UserSimplifiedResponse> getAllUsers() {
        return adminFacade.getAllUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDetailedResponse getUserDetailed(@PathVariable Long userId) {
        return adminFacade.getUserDetailed(userId);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return adminFacade.deleteUserById(userId);
    }
}
