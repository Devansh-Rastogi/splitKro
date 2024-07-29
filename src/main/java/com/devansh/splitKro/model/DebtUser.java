package com.devansh.splitKro.model;

import com.devansh.splitKro.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class DebtUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal amount;
    @ManyToMany(mappedBy = "groupMembers")
    @JsonIgnore
    private List<Split> splits = new ArrayList<>();
}
