package com.velkei.wallet.service;

import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;
import com.velkei.wallet.repository.UserGroupRepository;
import com.velkei.wallet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public UserGroup addGroupUser(long groupId, String userName){

        UserGroup userGroup = userGroupRepository.findById(groupId).get();

        userGroup.getGroupUsers().add(userRepository.findByUserName(userName));

        return userGroupRepository.save(userGroup);
    }
}
