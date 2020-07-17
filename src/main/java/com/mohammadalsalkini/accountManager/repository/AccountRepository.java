package com.mohammadalsalkini.accountManager.repository;

import com.mohammadalsalkini.accountManager.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @project accountManager
 * @auther Mohammad Alsalkini
 * @ceated on 15.07.2020 - 10:11
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
