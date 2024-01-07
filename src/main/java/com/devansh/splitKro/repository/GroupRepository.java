package com.devansh.splitKro.repository;

import com.devansh.splitKro.model.group.SplitKroGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<SplitKroGroup, Long> {
}
