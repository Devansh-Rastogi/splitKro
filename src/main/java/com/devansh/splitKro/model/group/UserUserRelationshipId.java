package com.devansh.splitKro.model.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUserRelationshipId implements Serializable {
    private Long user1Id;
    private Long user2Id;
    private Long groupId;
}
