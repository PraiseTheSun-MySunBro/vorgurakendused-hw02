package ee.ttu.authentication.controller;

import com.google.gson.Gson;
import ee.ttu.authentication.dto.UserCredentialDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountService accountService;

    @Autowired
    public AuthController(AccountService accountService, Gson gson) {
        this.accountService = accountService;
    }

    @PutMapping(value = "/signup", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody UserCredentialDto userCredentialDto) {
        Account account = accountService.registerAccount(userCredentialDto);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
