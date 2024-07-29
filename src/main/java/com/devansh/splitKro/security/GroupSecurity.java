package com.devansh.splitKro.security;

import com.devansh.splitKro.model.Expense;
import com.devansh.splitKro.model.group.SplitKroGroup;
import com.devansh.splitKro.model.user.User;
import com.devansh.splitKro.repository.ExpenseRepository;
import com.devansh.splitKro.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("groupSecurity")
public class GroupSecurity {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    public boolean checkUserMembership(Authentication authentication, Long groupId){
        Optional<SplitKroGroup> splitKroGroup = groupRepository.findById(groupId);
        User loggedInUser = (User) authentication.getPrincipal();
        if(splitKroGroup.isEmpty())
            return false;
        return splitKroGroup.get().getUserSet().stream().anyMatch(user -> loggedInUser.getId().equals(user.getId()));
    }

    public boolean checkUserMembershipForExpense(Authentication authentication, Long expenseId){
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if(expenseOptional.isEmpty())
            return false;
        User loggedInUser = (User) authentication.getPrincipal();
        return expenseOptional.get().getGroup().getUserSet().stream().anyMatch(user -> loggedInUser.getId().equals(user.getId()));
    }
}
