package com.devansh.splitKro.model;

import com.devansh.splitKro.model.group.SplitKroGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private SplitKroGroup group;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<Split> splitList = new ArrayList<>();
}
