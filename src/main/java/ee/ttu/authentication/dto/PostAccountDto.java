package ee.ttu.authentication.dto;

import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostAccountDto {
    private Post post;
    private Account account;
}
