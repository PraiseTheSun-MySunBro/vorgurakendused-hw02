package ee.ttu.authentication.service;

import ee.ttu.authentication.configuration.PasswordEncoder;
import ee.ttu.authentication.dto.UserCredentialDto;
import ee.ttu.authentication.model.Account;
import ee.ttu.authentication.repository.auth.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        return optionalAccount.orElse(null);
    }

    public Account registerAccount(UserCredentialDto data) {
        return accountRepository.save(new Account(null, data.getUsername(), bCryptPasswordEncoder.encode(data.getPassword()), data.getEmail(),
                null, null, null));
    }
}
