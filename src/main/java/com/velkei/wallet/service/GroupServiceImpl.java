package com.velkei.wallet.service;

import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.UserGroupRepository;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserGroup createGroup(User user, String groupName) {

        UserGroup group = new UserGroup();

        group.setGroupOwner(user);
        group.setGroupName(groupName);

        return userGroupRepository.save(group);
    }

    public List<UserGroup> getGroups(User user){

        return userGroupRepository.getUserGroupByUser(user.getUserName());
    }

    public UserGroup addGroupUser(long groupId, String email){

        UserGroup userGroup = userGroupRepository.findById(groupId).get();

        List<User> groupUsers = userGroup.getGroupUsers();

        groupUsers.add(userRepository.findByUserName(email));

        userGroup.setGroupUsers(groupUsers);

        return userGroupRepository.save(userGroup);
    }
}
