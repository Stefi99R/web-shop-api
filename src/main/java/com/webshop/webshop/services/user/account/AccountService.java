package com.webshop.webshop.services.user.account;

import com.webshop.webshop.domain.user.account.Account;
import com.webshop.webshop.repositories.AccountRepository;
import com.webshop.webshop.utils.exceptions.consts.ExceptionErrorCodeType;
import com.webshop.webshop.utils.exceptions.types.EntityAlreadyExistsException;
import com.webshop.webshop.utils.exceptions.types.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findOneByEmailOrElseThrowNotFound(String email) {
        return accountRepository.findOneByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(ExceptionErrorCodeType.EMAIL_NOT_FOUND,
                        "Account with email " + email + " not found"));
    }

    public void findOneByEmailAndThrowIfExists (String email) {
        if (accountRepository.findOneByEmail(email).isPresent()) {
            throw new EntityAlreadyExistsException(ExceptionErrorCodeType.EMAIL_ALREADY_EXISTS,
                    "Account with email " + email + " already exists");
        }
    }

}
