package br.com.picpay.application.usecase;

import br.com.picpay.application.domain.exception.NullValueException;
import br.com.picpay.application.domain.model.AccountDomain;
import br.com.picpay.application.port.in.ICreateAccountUseCase;
import br.com.picpay.application.port.out.ICreateAccountService;
import br.com.picpay.infrastructure.config.Usecase;
import lombok.RequiredArgsConstructor;

@Usecase
@RequiredArgsConstructor
public class CreateAccountAccountUseCase implements ICreateAccountUseCase {

    private final ICreateAccountService iCreateAccountService;

    @Override
    public AccountDomain execute(AccountDomain domain) {
        if (domain == null){
            throw new NullValueException("O valor fornecido ao criar uma conta nao pode ser nulo");
        }
        return iCreateAccountService.execute(domain);
    }
}
