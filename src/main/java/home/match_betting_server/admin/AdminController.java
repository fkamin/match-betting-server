package home.match_betting_server.admin;

import home.match_betting_server.admin.domain.AdminFacade;
import home.match_betting_server.admin.dto.requests.CreateUserRequest;
import home.match_betting_server.users.dto.responses.UserDetailedResponse;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import home.match_betting_server.users.dto.responses.UserSimplifiedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1_1/admin")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final AdminFacade adminFacade;

    @PostMapping("/users")
    public UserNewAccountResponse generateNewAccount(@RequestBody CreateUserRequest createUserRequest) {
        return adminFacade.generateNewAccount(createUserRequest);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long userId) {
        return adminFacade.deleteUserById(userId);
    }
}
