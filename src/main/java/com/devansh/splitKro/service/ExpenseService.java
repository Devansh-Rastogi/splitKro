package com.devansh.splitKro.service;

import com.devansh.splitKro.factory.SplitTypeFactory;
import com.devansh.splitKro.model.DebtUser;
import com.devansh.splitKro.model.Expense;
import com.devansh.splitKro.model.Split;
import com.devansh.splitKro.model.exepense.ExpenseDto;
import com.devansh.splitKro.model.exepense.ExpenseResponseDto;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.repository.ExpenseRepository;
import com.devansh.splitKro.repository.GroupRepository;
import com.devansh.splitKro.repository.SplitRepository;
import com.devansh.splitKro.repository.UserUserRelationshipRepository;
import com.devansh.splitKro.service.split.SplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private SplitTypeFactory splitTypeFactory;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserUserRelationshipRepository userUserRelationshipRepository;
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private SplitRepository splitRepository;

    @Transactional()
    public ExpenseResponseDto createExpense(ExpenseDto expenseDto){
        SplitService splitService = splitTypeFactory.getSplitService(expenseDto.getSplitTypeEnum());
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());
        expense.setGroup(groupRepository.findById(expenseDto.getGroupId()).get());
        List<Split> splitList = splitService.createSplits(expenseDto.getSplitDetails(), new GroupResponseDto(expense.getGroup()), expense);
        expense.setSplitList(splitList);
        expense = expenseRepository.save(expense);
        for (Split split : splitList) {
            for (DebtUser groupMember : split.getGroupMembers()) {
                userUserRelationshipRepository.updateAmount(split.getPayer().getId(), groupMember.getUser().getId(), expense.getGroup().getId(), groupMember.getAmount());
            }
        }
        return new ExpenseResponseDto();
    }

    public Expense getExpense(Long expenseId) {
        return expenseRepository.findById(expenseId).get();
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }
}
