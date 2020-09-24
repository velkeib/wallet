package com.velkei.wallet.dto;

import lombok.Value;

@Value
public class ExpenseDTO {

    private String name;

    private int amount;

    private String description;

    private String date;

}
