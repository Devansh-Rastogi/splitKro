package com.devansh.splitKro.model.group;

import com.devansh.splitKro.model.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Data
public class UserUserRelationship {
    @EmbeddedId
    private UserUserRelationshipId id = new UserUserRelationshipId();
    @ManyToOne
    @MapsId("user1Id")
    @JoinColumn(name = "user1_id", insertable = false, updatable = false)
    private User user1;

    @ManyToOne
    @MapsId("user2Id")
    @JoinColumn(name = "user2_id", insertable = false, updatable = false)
    private User user2;

    private BigDecimal amount;

    @ManyToOne
    @MapsId("groupId")
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private SplitKroGroup group;

    public UserUserRelationship(){
        this.amount = BigDecimal.valueOf(0);
    }
}
