package com.velkei.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ChartData {

    String name;
    int[] data;

}
