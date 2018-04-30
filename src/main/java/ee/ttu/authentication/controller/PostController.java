package ee.ttu.authentication.controller;

import com.google.gson.Gson;
import ee.ttu.authentication.dto.PostDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.model.Post;
import ee.ttu.authentication.property.JWTProperty;
import ee.ttu.authentication.security.JWTTokenUtil;
import ee.ttu.authentication.service.AccountService;
import ee.ttu.authentication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final JWTProperty jwtProperty;
    private final JWTTokenUtil jwtTokenUtil;
    private final AccountService accountService;
    private final PostService postService;

    @Autowired
    public PostController(JWTProperty jwtProperty, JWTTokenUtil jwtTokenUtil, AccountService accountService, PostService postService, Gson gson) {
        this.jwtProperty = jwtProperty;
        this.jwtTokenUtil = jwtTokenUtil;
        this.accountService = accountService;
        this.postService = postService;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> create(@Valid @RequestBody PostDto postDto, HttpServletRequest request) {
        Account account = getAccountByRequest(request);
        Post post = postService.insert(postDto, account);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> read() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@Valid @RequestBody PostDto postDto, HttpServletRequest request) {
        Account account = getAccountByRequest(request);
        Post post = postService.update(postDto, account);

        if (post == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping(produces = "application/json")
    public ResponseEntity<?> remove() {
        return null;
    }

    private Account getAccountByRequest(HttpServletRequest request) {
        String username = jwtTokenUtil.getUsernameFromToken(getTokenFromHeader(request));
        return (Account) accountService.loadUserByUsername(username);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(jwtProperty.getHeader()).substring(jwtProperty.getTokenPrefix().length() + 1);
    }
}
