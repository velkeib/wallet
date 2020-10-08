package com.velkei.wallet.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupId;

    @Column
    private String groupName;

    @ManyToMany
    @JoinColumn(name = "group_users")
    private List<User> groupUsers;

    @OneToOne
    @JoinColumn(name = "group_owner")
    private User groupOwner;
}
