package com.devansh.splitKro.model.exepense;

import com.devansh.splitKro.enums.SplitTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ExpenseDto {
    private String description;
    private BigDecimal amount;
    private Long groupId;
    private SplitTypeEnum splitTypeEnum;
    private List<SplitDetails> splitDetails;
}