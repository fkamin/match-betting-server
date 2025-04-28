package home.match_betting_server.management;

import home.match_betting_server.management.domain.AdminFacade;
import home.match_betting_server.management.dto.requests.CreateUserRequest;
import home.match_betting_server.management.dto.responses.UserGeneralResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminManagementController {
    private final AdminFacade adminFacade;

    public AdminManagementController(AdminFacade adminFacade) {
        this.adminFacade = adminFacade;
    }

    // USERS
    @PostMapping("/users")
    public UserSimplifiedResponse generateNewAccount(@RequestBody CreateUserRequest createUserRequest) {
        return adminFacade.generateNewAccount(createUserRequest);
    }

    @GetMapping("/users")
    public List<UserGeneralResponse> getAllUsers() {
        return adminFacade.getAllUsers();
    }
}
