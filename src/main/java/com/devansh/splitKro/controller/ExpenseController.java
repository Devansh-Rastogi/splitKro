package com.devansh.splitKro.controller;

import com.devansh.splitKro.model.exepense.ExpenseDto;
import com.devansh.splitKro.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping
    public void createExpense(@RequestBody ExpenseDto expenseDto){
        try{
            expenseService.createExpense(expenseDto);
        }catch (Exception e){
            e.getMessage();
        }
    }

    @GetMapping("/{expenseId}")
    @PreAuthorize("@groupSecurity.checkUserMembershipForExpense(authentication, #expenseId)")
    public void getExpense(@PathVariable Long expenseId){
        try{
            expenseService.getExpense(expenseId);
        }catch (Exception e){
            e.getMessage();
        }
    }

    @DeleteMapping("/{expenseId}")
    @PreAuthorize("@groupSecurity.checkUserMembershipForExpense(authentication, #expenseId)")
    public void deleteExpense(@PathVariable Long expenseId){
        try{
            expenseService.deleteExpense(expenseId);
        }catch (Exception e){
            e.getMessage();
        }
    }
}
