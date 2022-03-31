package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPlayer {
    private String username;
    private String password_change;
    private String password_repeat;
    private String email;
    private String name;
    private String surname;
    private String currency_code;
}
