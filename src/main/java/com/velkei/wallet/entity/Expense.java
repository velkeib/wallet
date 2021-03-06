package com.velkei.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private int amount;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User userName;

    @Column
    private Calendar date;

    @ManyToOne
    @JoinColumn(name = "user_group")
    private UserGroup userGroup;

}
