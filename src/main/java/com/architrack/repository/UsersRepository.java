package com.architrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.architrack.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	@Query(value = "SELECT u FROM Users u WHERE u.username =:username")
	Users findByUsername(@Param("username")String username);
}
