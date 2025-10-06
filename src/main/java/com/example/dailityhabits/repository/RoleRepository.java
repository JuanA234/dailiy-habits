package com.example.dailityhabits.repository;

import com.example.dailityhabits.entity.Role;
import com.example.dailityhabits.enumeration.RolesEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RolesEnum role);
}
