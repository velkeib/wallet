package com.velkei.wallet.dto;

import java.util.Date;

public interface ExpenseListInterface {
    int getAmount();
    String getDescription();
    Date getDateOfCreation();
    String getUserName();
    String getFirstName();
    String getLastName();
}
