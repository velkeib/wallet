package com.velkei.wallet.service;

import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;

import java.util.List;
import java.util.Optional;

public interface GroupService {

    public List<UserGroup> createGroup(User user, String groupName);

    public UserGroup addGroupUser(long groupId, String email);

    public List<UserGroup> getGroups(User user);
}
