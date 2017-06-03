package com.fbatracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fbatracker.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	public UserAccount findUserAccountByUsername(String username);
}
