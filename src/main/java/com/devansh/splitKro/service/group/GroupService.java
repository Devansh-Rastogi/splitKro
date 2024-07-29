package com.devansh.splitKro.service.group;

import com.devansh.splitKro.model.group.GroupRequestDto;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.model.group.UserUserRelationship;
import com.devansh.splitKro.model.user.UserRequestDto;

import java.util.List;
import java.util.Set;

public interface GroupService {
    GroupResponseDto createGroup(GroupRequestDto groupRequestDto) throws Exception;
    GroupResponseDto addMemberToGroup(Long groupId, Set<Long> userIds);
    void removeMemberFromGroup(Long groupId, UserRequestDto user);
    GroupResponseDto getAllGroupsOfUser(UserRequestDto userRequestDto);
    GroupResponseDto getGroupById(Long groupId);
    GroupResponseDto updateGroup(Long groupId, GroupRequestDto updatedGroupRequestDto);
    void deleteGroup(Long groupId);

    List<UserUserRelationship> getDebtsRelation(Long groupId);
}
