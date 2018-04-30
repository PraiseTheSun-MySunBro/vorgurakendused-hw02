package ee.ttu.authentication.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthenticationResponse implements Serializable {

    private String token;
}
