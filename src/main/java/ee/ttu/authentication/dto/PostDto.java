package ee.ttu.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long postId;

    @NotEmpty
    @Size(max = 128)
    private String title;

    @NotEmpty
    private String content;
}
