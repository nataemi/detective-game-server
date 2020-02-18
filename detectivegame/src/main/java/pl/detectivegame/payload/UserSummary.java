package pl.detectivegame.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserSummary {
    private Long id;
    private String username;
    private String name;
}
