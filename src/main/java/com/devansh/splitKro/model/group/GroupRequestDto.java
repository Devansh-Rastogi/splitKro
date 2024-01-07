package com.devansh.splitKro.model.group;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRequestDto {
    private String groupName;
    private List<Long> userIds;
}
