package br.com.picpay.application.port.in;

import br.com.picpay.application.domain.model.AccountDomain;

public interface IUpdateAccountUseCase {
    AccountDomain execute(Long id, AccountDomain domain);
}
