package com.devansh.splitKro.service.split.impl;

import com.devansh.splitKro.enums.SplitTypeEnum;
import com.devansh.splitKro.model.DebtUser;
import com.devansh.splitKro.model.Expense;
import com.devansh.splitKro.model.Split;
import com.devansh.splitKro.model.exepense.DebtUserDto;
import com.devansh.splitKro.model.exepense.PayerDto;
import com.devansh.splitKro.model.exepense.SplitDetails;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.model.user.UserResponseDto;
import com.devansh.splitKro.repository.UserRepository;
import com.devansh.splitKro.service.split.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class EquallySplitService implements SplitService {
    private static final SplitTypeEnum SPLIT_TYPE = SplitTypeEnum.EQUALLY;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SplitTypeEnum getType(){
        return SPLIT_TYPE;
    }

    @Override
    public List<Split> createSplits(List<SplitDetails> splitDetails, GroupResponseDto group, Expense expense) {
        List<Split> splitList = new ArrayList<>();
        for (SplitDetails splitDetail : splitDetails) {
            for (PayerDto payerDto : splitDetail.getPayerDtos()) {
                Split split = new Split();
                UserResponseDto user = group.getUserSet().stream().filter(u -> u.getId().equals(payerDto.getUserId())).findFirst().get();
                split.setPayer(userRepository.findById(user.getId()).get());
                split.setAmount(payerDto.getAmount());
                split.setGroupMembers(createDebtUser(payerDto, splitDetail.getDebtUserDtos()));
                split.setExpense(expense);
                split.setSplitType(SPLIT_TYPE);
                splitList.add(split);
            }
        }
        return splitList;
    }

    private List<DebtUser> createDebtUser(PayerDto payerDto, List<DebtUserDto> debtUserDtos) {
        List<DebtUser> debtUsers = new ArrayList<>();
        for (DebtUserDto debtUserDto : debtUserDtos) {
            DebtUser debtUser = new DebtUser();
            debtUser.setUser(userRepository.findById(debtUserDto.getUserId()).get());
            debtUser.setAmount(payerDto.getAmount().divide(BigDecimal.valueOf(debtUserDtos.size())));
            debtUsers.add(debtUser);
        }
        return debtUsers;
    }
}
