package pl.detectivegame.payload.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSummaryResponse {
    private Long id;
    private String username;
    private String name;
}
