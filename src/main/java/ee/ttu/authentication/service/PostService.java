package ee.ttu.authentication.service;

import ee.ttu.authentication.dto.PostAccountDto;
import ee.ttu.authentication.dto.PostDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.model.Post;
import ee.ttu.authentication.repository.auth.AccountRepository;
import ee.ttu.authentication.repository.auth.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public PostService(PostRepository postRepository, AccountRepository accountRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
    }

    public Post insert(PostDto postDto, Account account) {
        return postRepository.save(new Post(null, account, postDto.getTitle(), postDto.getContent(), null, null));
    }

    public Post update(PostDto postDto, Account account) {
        Optional<Post> postOptional = postRepository.findById(postDto.getPostId());
        if (!postOptional.isPresent()) return null;

        Post post = postOptional.get();
        //if (!post.getOwner().equals(account)) return null;

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    public List<PostAccountDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        List<PostAccountDto> postAccounts = new ArrayList<>();
        for (Post post : posts) {
            postAccounts.add(new PostAccountDto(post, post.getOwner()));
        }
        return postAccounts;
    }
}
