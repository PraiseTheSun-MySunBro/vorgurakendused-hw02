package ee.ttu.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialDto {
    @NotEmpty
    private String username;
    @NotEmpty
    @Size(max = 255)
    private String email;
    @NotEmpty
    @Size(max = 72)
    private String password;
}
