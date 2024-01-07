package com.devansh.splitKro.model.group;

import com.devansh.splitKro.model.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
    private Long groupId;
    private String groupName;
    private Set<UserResponseDto> userSet = new HashSet<>();

    public GroupResponseDto(SplitKroGroup group) {
        this.groupId = group.getId();
        this.groupName = group.getGroupName();
        this.userSet = group.getUserSet().stream().map(UserResponseDto::new).collect(Collectors.toSet());
    }
}