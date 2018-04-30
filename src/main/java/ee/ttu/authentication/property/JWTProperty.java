package ee.ttu.authentication.property;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Component
@Validated
@ConfigurationProperties(prefix = "jwt")
public class JWTProperty {
    @NotNull
    private Long expirationTime;
    @NotEmpty
    private String header;
    @NotEmpty
    private String tokenPrefix;
    @NotEmpty
    @Length(min = 32)
    private String secret;

    public void setExpirationTime(Long value) {
        this.expirationTime = value * 1000;
    }
}
