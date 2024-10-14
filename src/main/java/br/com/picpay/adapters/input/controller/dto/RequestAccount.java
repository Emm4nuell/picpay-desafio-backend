package br.com.picpay.adapters.input.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RequestAccount {
    private String name;
    private String email;
    private String cpf;
    private String password;
}
