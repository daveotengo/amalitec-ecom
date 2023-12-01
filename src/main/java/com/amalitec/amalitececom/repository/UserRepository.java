package com.amalitec.amalitececom.repository;

import com.amalitec.amalitececom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);

}
