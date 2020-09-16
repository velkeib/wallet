package com.velkei.wallet.dto;

import com.velkei.wallet.entity.User;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;

@Value
@AllArgsConstructor
public class ChartPageResponse {

    ArrayList<ChartData> chartData;
    ArrayList<User> users;

}
