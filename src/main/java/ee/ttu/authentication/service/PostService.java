package ee.ttu.authentication.service;

import ee.ttu.authentication.dto.PostDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.model.Post;
import ee.ttu.authentication.repository.auth.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
