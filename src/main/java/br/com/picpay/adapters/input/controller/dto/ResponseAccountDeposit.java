package br.com.picpay.adapters.input.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAccountDeposit {
    private String cpf;
    private String agency;
    private String account;
    private BigDecimal balance;
}
