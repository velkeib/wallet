package com.velkei.wallet.service;

import com.velkei.wallet.entity.UserGroup;
import com.velkei.wallet.entity.User;

public interface GroupService {

    public UserGroup createGroup(User user, String groupName);
}
