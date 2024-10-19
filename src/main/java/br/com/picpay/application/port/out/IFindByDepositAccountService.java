package br.com.picpay.application.port.out;

import br.com.picpay.application.domain.model.AccountDomain;

import java.util.Optional;

public interface IFindByDepositAccountService {
    Optional<AccountDomain> execute(String cpf);
}
