package com.devansh.splitKro.service.group;

import com.devansh.splitKro.model.group.GroupRequestDto;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.model.user.UserRequestDto;

public interface GroupService {
    GroupResponseDto createGroup(GroupRequestDto groupRequestDto) throws Exception;
    void addMemberToGroup(Long groupId, UserRequestDto user);
    void removeMemberFromGroup(Long groupId, UserRequestDto user);
    GroupResponseDto getAllGroupsOfUser(UserRequestDto userRequestDto);
    GroupResponseDto getGroupById(Long groupId);
    GroupResponseDto updateGroup(Long groupId, GroupRequestDto updatedGroupRequestDto);
    void deleteGroup(Long groupId);
}
