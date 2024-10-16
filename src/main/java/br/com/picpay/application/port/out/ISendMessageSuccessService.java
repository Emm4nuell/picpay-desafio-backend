package br.com.picpay.application.port.out;

import br.com.picpay.application.domain.model.AccountDomain;

public interface ISendMessageSuccessService {
    void execute(AccountDomain domain);
}
