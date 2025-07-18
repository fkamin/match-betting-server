package home.match_betting_server.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import home.match_betting_server.PostgreSQLTestContainer;
import home.match_betting_server.admin.dto.requests.CreateUserRequest;
import home.match_betting_server.admin.dto.responses.NewAccountResponse;
import home.match_betting_server.bets.domain.BetRepository;
import home.match_betting_server.matches.domain.MatchRepository;
import home.match_betting_server.phase_user_stats.domain.PhaseUserStatsRepository;
import home.match_betting_server.phases.domain.PhaseRepository;
import home.match_betting_server.users.domain.Role;
import home.match_betting_server.users.domain.User;
import home.match_betting_server.users.domain.UserRepository;
import home.match_betting_server.users.dto.responses.UserNewAccountResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserCrudControllerTest {
    private static final String BASE_URL = "/api/v1_1";
    @Autowired
    private BetRepository betRepository;
    @Autowired
    private PhaseRepository phaseRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private PhaseUserStatsRepository phaseUserStatsRepository;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> container = PostgreSQLTestContainer.getInstance();
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    private static final Logger log = LoggerFactory.getLogger(UserCrudControllerTest.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void cleanUp() {
        betRepository.deleteAll();
        matchRepository.deleteAll();
        phaseUserStatsRepository.deleteAll();
        phaseRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Nested
    class UserCreationTests {
        private static final String userCreationBaseUrl = BASE_URL + "/admin/users";

        @Test
        void shouldCreateUser() throws Exception {
            // given
            CreateUserRequest userRequestData = new CreateUserRequest("tomek");

            // when
            MvcResult mvcResult = mockMvc.perform(
                            post(userCreationBaseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(userRequestData))
                    )
                    .andExpect(status().isOk())
                    .andReturn();

            // then
            String responseJson = mvcResult.getResponse().getContentAsString();
            UserNewAccountResponse createdUser = objectMapper.readValue(responseJson, UserNewAccountResponse.class);

            Assertions.assertThat(createdUser.getId()).isEqualTo(1);
            Assertions.assertThat(createdUser.getName()).isNull();
            Assertions.assertThat(createdUser.getLogin()).isEqualTo("tomek");
            Assertions.assertThat(createdUser.getPassword()).isNotEmpty();
        }

        @Test
        void shouldNotCreateUserWithTheSameName() throws Exception {
            // given
            thereIsUser();
            CreateUserRequest userRequestData = new CreateUserRequest("tomek");

            // when then
            mockMvc.perform(
                            post(userCreationBaseUrl)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(userRequestData))
                    )
                    .andExpect(status().isConflict());
        }
    }

    @Nested
    class UserGettersTests {
        private static final String userCreationBaseUrl = BASE_URL + "/admin/users";
    }

    public User thereIsUser() {
        return userRepository.save(new User("tomek", "tomek", Role.USER));
    }
}
