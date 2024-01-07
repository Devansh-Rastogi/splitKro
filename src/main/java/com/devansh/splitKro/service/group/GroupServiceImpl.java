package com.devansh.splitKro.service.group;

import com.devansh.splitKro.exception.GroupNotFoundException;
import com.devansh.splitKro.exception.UserNotFoundException;
import com.devansh.splitKro.model.group.GroupRequestDto;
import com.devansh.splitKro.model.group.GroupResponseDto;
import com.devansh.splitKro.model.group.SplitKroGroup;
import com.devansh.splitKro.model.group.UserUserRelationship;
import com.devansh.splitKro.model.user.User;
import com.devansh.splitKro.model.user.UserRequestDto;
import com.devansh.splitKro.model.user.UserResponseDto;
import com.devansh.splitKro.repository.GroupRepository;
import com.devansh.splitKro.repository.UserRepository;
import com.devansh.splitKro.repository.UserUserRelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserUserRelationshipRepository userUserRelationshipRepository;

    @Override
    @Transactional
    public GroupResponseDto createGroup(GroupRequestDto groupRequestDto) throws Exception {
        SplitKroGroup splitKroGroup = new SplitKroGroup();
        splitKroGroup.setGroupName(groupRequestDto.getGroupName());
        splitKroGroup.setUserSet(fetchUserFromIds(groupRequestDto.getUserIds()));
//        splitKroGroup.setUserUserRelationShips();
        splitKroGroup = groupRepository.save(splitKroGroup);
        List<UserUserRelationship> userUserRelationships = createUserUserRelationShip(splitKroGroup.getUserSet(), splitKroGroup);
        try{
            userUserRelationshipRepository.saveAll(userUserRelationships);
        }catch (Exception e){
            System.out.println("asd");
            throw new Exception("Not possible to create");
        }
        splitKroGroup.setUserUserRelationShips(userUserRelationships);
        groupRepository.save(splitKroGroup);
        GroupResponseDto groupResponseDto = new GroupResponseDto();
        groupResponseDto.setGroupId(splitKroGroup.getId());
        groupResponseDto.setUserSet(splitKroGroup.getUserSet().stream().map(UserResponseDto::new).collect(Collectors.toSet()));
        groupResponseDto.setGroupName(splitKroGroup.getGroupName());
        return groupResponseDto;
    }

    private List<UserUserRelationship> createUserUserRelationShip(Set<User> userSet, SplitKroGroup splitKroGroup) {
        List<UserUserRelationship> userUserRelationships = new ArrayList<>();
        for (User user : userSet) {
            for(User user1: userSet){
                UserUserRelationship userUserRelationship = new UserUserRelationship();
                userUserRelationship.setUser1(user);
                userUserRelationship.setUser2(user1);
                userUserRelationship.setGroup(splitKroGroup);
                userUserRelationships.add(userUserRelationship);
            }
        }
        return userUserRelationships;
    }

    private Set<User> fetchUserFromIds(List<Long> userIds) throws UserNotFoundException {
        return userIds.stream().map(userRepository::findById)
                .map(userOptional -> userOptional.orElseThrow(() -> new UserNotFoundException("No User found with the given id"))).collect(Collectors.toSet());
    }

    @Override
    public void addMemberToGroup(Long groupId, UserRequestDto user) {
        User newUser = userRepository.findById(user.getId()).orElseThrow(() -> new UserNotFoundException("No user found"));
        groupRepository.findById(groupId).ifPresentOrElse(splitKroGroup -> splitKroGroup.getUserSet().add(newUser), ()->{throw new GroupNotFoundException("No Group Found");});
    }

    @Override
    public void removeMemberFromGroup(Long groupId, UserRequestDto user) {

    }

    @Override
    public GroupResponseDto getAllGroupsOfUser(UserRequestDto userRequestDto) {
        return null;
    }

    @Override
    public GroupResponseDto getGroupById(Long groupId) {
        return null;
    }

    @Override
    public GroupResponseDto updateGroup(Long groupId, GroupRequestDto updatedGroupRequestDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteGroup(Long groupId) {
        groupRepository.deleteById(groupId);
    }
}
