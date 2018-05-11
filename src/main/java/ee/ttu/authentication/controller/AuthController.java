package ee.ttu.authentication.controller;

import com.google.gson.Gson;
import ee.ttu.authentication.dto.UserCredentialDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.property.JWTProperty;
import ee.ttu.authentication.security.JWTTokenUtil;
import ee.ttu.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;
    private final JWTTokenUtil jwtTokenUtil;
    private final JWTProperty jwtProperty;

    @Autowired
    public AuthController(AccountService accountService, Gson gson, JWTTokenUtil jwtTokenUtil, JWTProperty jwtProperty) {
        this.accountService = accountService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.jwtProperty = jwtProperty;
    }

    @PutMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody UserCredentialDto userCredentialDto) {
        Account account = accountService.registerAccount(userCredentialDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<?> user(HttpServletRequest request) {
        return new ResponseEntity<>(getAccountByRequest(request), HttpStatus.OK);
    }

    private Account getAccountByRequest(HttpServletRequest request) {
        String username = jwtTokenUtil.getUsernameFromToken(getTokenFromHeader(request));
        return (Account) accountService.loadUserByUsername(username);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        return request.getHeader(jwtProperty.getHeader()).substring(jwtProperty.getTokenPrefix().length() + 1);
    }
}
