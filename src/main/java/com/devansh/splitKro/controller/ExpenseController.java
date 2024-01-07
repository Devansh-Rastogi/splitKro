package com.devansh.splitKro.controller;

import com.devansh.splitKro.model.exepense.ExpenseDto;
import com.devansh.splitKro.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
