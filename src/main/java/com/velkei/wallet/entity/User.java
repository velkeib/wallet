package com.velkei.wallet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @Column
    private String userName ;

    @Column
    private String password ;

    @Column
    private String firstName;

    @Column
    private String lastName ;


}
