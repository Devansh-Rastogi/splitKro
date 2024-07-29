package com.devansh.splitKro.controller;

import com.devansh.splitKro.model.group.GroupRequestDto;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.model.group.UserUserRelationship;
import com.devansh.splitKro.service.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Create a new group
    @PostMapping
    public ResponseEntity<GroupResponseDto> createGroup(@RequestBody GroupRequestDto groupRequestDto) {
        try {
            GroupResponseDto newGroup = groupService.createGroup(groupRequestDto);
            return new ResponseEntity<>(newGroup, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get group details by ID
    @GetMapping("/{groupId}")
    @PreAuthorize("@groupSecurity.checkUserMembership(authentication, #groupId)")
    public ResponseEntity<GroupResponseDto> getGroupById(@PathVariable Long groupId) {
        try {
            GroupResponseDto group = groupService.getGroupById(groupId);
            return group != null
                    ? new ResponseEntity<>(group, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update group details
    @PutMapping("/{groupId}")
    @PreAuthorize("@groupSecurity.checkUserMembership(authentication, #groupId)")
    public ResponseEntity<GroupResponseDto> updateGroup(@PathVariable Long groupId,
                                                        @RequestBody GroupRequestDto updatedGroupRequestDto) {
        try {
            GroupResponseDto updatedGroup = groupService.updateGroup(groupId, updatedGroupRequestDto);
            return updatedGroup != null
                    ? new ResponseEntity<>(updatedGroup, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete a group
    @DeleteMapping("/{groupId}")
    @PreAuthorize("@groupSecurity.checkUserMembership(authentication, #groupId)")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long groupId) {
        try {
            groupService.deleteGroup(groupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{groupId}/addMembers")
    @PreAuthorize("@groupSecurity.checkUserMembership(authentication, #groupId)")
    public ResponseEntity<GroupResponseDto> addMemberToGroup(@PathVariable Long groupId, @RequestParam Set<Long> userIds){
        try{
            GroupResponseDto groupResponseDto = groupService.addMemberToGroup(groupId, userIds);
            return new ResponseEntity<>(groupResponseDto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{groupId}/getDebtsRelation")
    @PreAuthorize("@groupSecurity.checkUserMembership(authentication, #groupId)")
    public ResponseEntity<List<UserUserRelationship>> getDebtsRelation(@PathVariable Long groupId){
        try{
            return new ResponseEntity<>(groupService.getDebtsRelation(groupId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
