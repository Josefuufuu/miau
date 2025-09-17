package com.example.miau.service;

import com.example.miau.domain.UserAccount;

import java.util.List;
import java.util.Set;

public interface UserAccountService {

    UserAccount createUser(UserAccount userAccount, Set<Long> roleIds);

    UserAccount updateUser(Long id, UserAccount userAccount, Set<Long> roleIds);

    void deleteUser(Long id);

    UserAccount getUser(Long id);

    List<UserAccount> getAllUsers();
}
