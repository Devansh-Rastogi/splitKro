package com.devansh.splitKro.repository;

import com.devansh.splitKro.model.group.UserUserRelationship;
import com.devansh.splitKro.model.group.UserUserRelationshipId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UserUserRelationshipRepository extends JpaRepository<UserUserRelationship, UserUserRelationshipId> {

    @Query("SELECT u FROM UserUserRelationship u WHERE u.id.user1Id = :user1Id AND u.id.user2Id = :user2Id AND u.id.groupId = :groupId")
    Optional<UserUserRelationship> findByUserIdsAndGroupId(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id, @Param("groupId") Long groupId);

    @Modifying
    @Query("UPDATE UserUserRelationship u SET u.amount = u.amount + :amount WHERE u.id.user1Id = :user1Id AND u.id.user2Id = :user2Id AND u.id.groupId = :groupId")
    void updateAmount(@Param("user1Id") Long user1Id, @Param("user2Id") Long user2Id, @Param("groupId") Long groupId, @Param("amount") BigDecimal amount);
}
