package com.didispace.repository;

import com.didispace.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
