package com.example.mess.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    private Integer unitsConsumed;
    private Integer unitsOff;
    private Integer guestUnits;
    private Integer messExpense;
    private Double unitPrice;
    private Integer totalMembers;
    private Double spentAmount;
    private Double remainingAmount;
    private Integer paidAmount;

}
