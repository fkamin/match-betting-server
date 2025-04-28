package home.match_betting_server.users.domain;

public class UserFacade {
    private final UserRepository userRepository;

    public UserFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
