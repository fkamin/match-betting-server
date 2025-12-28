package home.match_betting_server.auth.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "blacklisted_tokens")
public class BlacklistedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(name = "expiration_date", nullable = false)
    private Instant expirationDate;

    public BlacklistedToken(String token, Instant expirationDate) {
        this.token = token;
        this.expirationDate = expirationDate;
    }

    protected BlacklistedToken() {}
}
