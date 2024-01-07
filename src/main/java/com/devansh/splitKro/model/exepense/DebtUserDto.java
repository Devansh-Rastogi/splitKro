package com.devansh.splitKro.model.exepense;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DebtUserDto {
    private Long userId;
    private BigDecimal share;
}