package com.devansh.splitKro.service.split;

import com.devansh.splitKro.enums.SplitTypeEnum;
import com.devansh.splitKro.model.Expense;
import com.devansh.splitKro.model.Split;
import com.devansh.splitKro.model.exepense.SplitDetails;
import com.devansh.splitKro.model.group.GroupResponseDto;

import java.util.List;

public interface SplitService {

    SplitTypeEnum getType();

    List<Split> createSplits(List<SplitDetails> splitDetails, GroupResponseDto group, Expense expense);
}
