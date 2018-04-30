package ee.ttu.authentication.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@Component
@ConfigurationProperties(prefix = "cors")
public class CORSProperty {
    @NotEmpty
    private String[] origins;
    @NotEmpty
    private String[] methods;
    @NotEmpty
    private String[] headers;
}
