package com.picpaysimplificado.dtos;

import com.picpaysimplificado.model.user.User;

import java.math.BigDecimal;

public record TransactionsDto(BigDecimal amount, Long senderId, Long receiverId) {

}
