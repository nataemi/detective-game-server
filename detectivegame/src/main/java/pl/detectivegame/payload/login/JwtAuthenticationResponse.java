package pl.detectivegame.payload.login;

import lombok.Data;
import pl.detectivegame.model.DAO.User;

import java.util.Optional;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    private User user;

    public JwtAuthenticationResponse(String accessToken, Optional<User> user) {
        this.accessToken = accessToken;
        this.user = user.get();
    }
}
