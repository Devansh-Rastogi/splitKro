package com.devansh.splitKro.model.exepense;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SplitDetails{
    private List<PayerDto> payerDtos;
    private List<DebtUserDto> debtUserDtos;
}
