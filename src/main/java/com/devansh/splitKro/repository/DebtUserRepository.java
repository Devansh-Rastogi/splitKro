package com.devansh.splitKro.repository;

import com.devansh.splitKro.model.DebtUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtUserRepository extends JpaRepository<DebtUser, Long> {
}
