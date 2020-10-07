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
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long groupId;

    @Column
    private String groupName;

    @Column
    private List<User> groupUsers;
}
