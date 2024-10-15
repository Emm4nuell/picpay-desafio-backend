package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.ICreateAccountUseCase;
import br.com.picpay.application.port.out.ICreateAccountService;
import br.com.picpay.application.util.validator.AccountValidator;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateAccountUseCase implements ICreateAccountUseCase {

    private final ICreateAccountService iCreateAccountService;
    private final AccountValidator accountValidator;

    @Override
    public AccountDomain execute(AccountDomain domain) {
        accountValidator.validate(domain);
        return iCreateAccountService.execute(domain);
    }
}
