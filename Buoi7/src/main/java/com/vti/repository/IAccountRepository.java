package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vti.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{

}
