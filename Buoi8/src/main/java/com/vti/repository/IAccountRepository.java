package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti.entity.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Integer>{
	
	Account findById(int id);
	
	@Query("SELECT ac FROM Account ac WHERE ac.username =:usernmameParameter")
	Account getAccountByUsername(@Param("usernmameParameter") String username);
	
}
