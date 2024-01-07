package com.devansh.splitKro.model;

import com.devansh.splitKro.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Split {
    @Id
    @Column(name = "split_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "split_users",
            joinColumns = @JoinColumn(name = "split_id"),
            inverseJoinColumns = @JoinColumn(name = "debt_user_id")
    )
    private List<DebtUser> groupMembers;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;
}
