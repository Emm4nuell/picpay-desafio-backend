package br.com.picpay.adapters.input.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseAccountPage {
    private List<ResponseAccount> listAccount;
    private String sort;
    private int size;
    private int pages;
    private Long totalElements;
    private int totalPages;
    private String direction;
}
