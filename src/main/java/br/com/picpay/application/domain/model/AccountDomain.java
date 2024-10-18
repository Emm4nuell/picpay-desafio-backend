package br.com.picpay.application.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class AccountDomain {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private BigDecimal balance;
    private String accounttype;
    private String agency;
    private String account;
}
